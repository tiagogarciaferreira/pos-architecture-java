package br.edu.infnet.tiago.infrastructure.exception.handler;

import br.edu.infnet.tiago.infrastructure.exception.custom.ConflictException;
import br.edu.infnet.tiago.infrastructure.exception.custom.InternalServerErrorException;
import br.edu.infnet.tiago.infrastructure.exception.custom.NotFoundException;
import br.edu.infnet.tiago.infrastructure.exception.model.Problem;
import br.edu.infnet.tiago.infrastructure.exception.model.ProblemBuilder;
import br.edu.infnet.tiago.infrastructure.exception.model.Violation;
import br.edu.infnet.tiago.shared.config.MessageProvider;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

import static br.edu.infnet.tiago.infrastructure.constants.ErrorConstants.*;
import static br.edu.infnet.tiago.shared.utils.ListUtils.defaultIfNull;
import static org.springframework.http.HttpStatus.*;

@SuppressWarnings("all")
@RequiredArgsConstructor
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageProvider messageProvider;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatusCode status, WebRequest request) {

        Problem problem = new ProblemBuilder()
                .withMessage(messageProvider.getErrorMessage(ex.getMessage(), ERROR_INVALID_INPUT))
                .withType(messageProvider.getMessage(TYPE_BAD_REQUEST))
                .withStatus(BAD_REQUEST)
                .withRequest(request)
                .withBindingResult(ex.getBindingResult())
                .build();

        log.error("MethodArgumentNotValidException - {}", ex.getMessage());
        return handleExceptionInternal(ex, problem, headers, BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
                                                                  HttpStatusCode status, WebRequest request) {

        Throwable rootCause = NestedExceptionUtils.getRootCause(ex);
        ProblemBuilder problemBuilder = new ProblemBuilder()
                .withMessage(ex.getMessage())
                .withType(messageProvider.getMessage(TYPE_BAD_REQUEST))
                .withStatus(BAD_REQUEST)
                .withRequest(request);
        List<Violation> violations = new ArrayList<>();

        if (rootCause instanceof PropertyBindingException exception) {

            violations = defaultIfNull(exception.getPath())
                    .stream()
                    .map(ref -> new Violation(ref.getFieldName(), ref.getDescription()))
                    .toList();

            problemBuilder = problemBuilder.withMessage(exception.getMessage());
        }

        Problem problem = problemBuilder.build().withViolations(violations);
        problem.setDetail(messageProvider.getErrorMessage(problem.getDetail(), ERROR_MALFORMED_REQUEST));

        log.error("HttpMessageNotReadableException - {}", ex.getMessage());
        return handleExceptionInternal(ex, problem, headers, BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
                                                                   HttpStatusCode status, WebRequest request) {
        Problem problem = new ProblemBuilder()
                .withMessage(messageProvider.getErrorMessage(ex.getMessage(), ERROR_RESOURCE_NOT_FOUND))
                .withStatus(NOT_FOUND)
                .withRequest(request)
                .build();
        log.error("NoHandlerFoundException - {}", ex.getMessage());
        return handleExceptionInternal(ex, problem, headers, NOT_FOUND, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                                         HttpHeaders headers, HttpStatusCode status,
                                                                         WebRequest request) {
        Problem problem = new ProblemBuilder()
                .withMessage(messageProvider.getErrorMessage(ex.getMessage(), ERROR_METHOD_NOT_ALLOWED))
                .withType(messageProvider.getMessage(TYPE_METHOD_NOT_ALLOWED))
                .withStatus(METHOD_NOT_ALLOWED)
                .withRequest(request)
                .build();
        log.error("HttpRequestMethodNotSupportedException - {}", ex.getMessage());
        return handleExceptionInternal(ex, problem, headers, METHOD_NOT_ALLOWED, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers,
                                                                     HttpStatusCode status, WebRequest request) {

        Problem problem = new ProblemBuilder()
                .withMessage(messageProvider.getErrorMessage(ex.getMessage(), ERROR_UNSUPPORTED_MEDIA_TYPE))
                .withType(messageProvider.getMessage(TYPE_UNSUPPORTED_MEDIA_TYPE))
                .withStatus(UNSUPPORTED_MEDIA_TYPE)
                .withRequest(request)
                .build();
        log.error("HttpMediaTypeNotSupportedException - {}", ex.getMessage());
        return handleExceptionInternal(ex, problem, headers, UNSUPPORTED_MEDIA_TYPE, request);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException ex, WebRequest request) {
        Problem problem = new ProblemBuilder()
                .withMessage(messageProvider.getErrorMessage(ex.getMessage(), ERROR_RESOURCE_NOT_FOUND))
                .withType(messageProvider.getMessage(TYPE_NOT_FOUND))
                .withStatus(NOT_FOUND)
                .withRequest(request)
                .build();
        log.error("NotFoundException - {}", ex.getMessage());
        return handleExceptionInternal(ex, problem, new HttpHeaders(), NOT_FOUND, request);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<?> handleConflictException(ConflictException ex, WebRequest request) {
        Problem problem = new ProblemBuilder()
                .withMessage(messageProvider.getErrorMessage(ex.getMessage(), ERROR_CONFLICT_DETECTED))
                .withType(messageProvider.getMessage(TYPE_CONFLICT))
                .withStatus(CONFLICT)
                .withRequest(request)
                .build();
        log.error("ConflictException - {}", ex.getMessage());
        return handleExceptionInternal(ex, problem, new HttpHeaders(), CONFLICT, request);
    }

    @ExceptionHandler({InternalServerErrorException.class, Exception.class})
    public ResponseEntity<?> handleInternalServerErrorException(Exception ex, WebRequest request) {
        Problem problem = new ProblemBuilder()
                .withMessage(messageProvider.getErrorMessage(ex.getMessage(), ERROR_INTERNAL_SERVER_ERROR))
                .withType(messageProvider.getMessage(TYPE_INTERNAL_SERVER_ERROR))
                .withStatus(INTERNAL_SERVER_ERROR)
                .withRequest(request).build();
        log.error("InternalServerErrorException - {}", ex.getMessage());
        return handleExceptionInternal(ex, problem, new HttpHeaders(), INTERNAL_SERVER_ERROR, request);
    }
}