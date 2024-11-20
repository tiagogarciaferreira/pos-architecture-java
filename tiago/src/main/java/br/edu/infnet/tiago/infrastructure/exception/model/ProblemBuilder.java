package br.edu.infnet.tiago.infrastructure.exception.model;

import br.edu.infnet.tiago.shared.utils.ListUtils;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.method.ParameterValidationResult;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

import static br.edu.infnet.tiago.shared.utils.StringUtils.nullToEmpty;
import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;

@With
@AllArgsConstructor
@NoArgsConstructor
public class ProblemBuilder {

    private String message;

    private String type;

    private HttpStatus status;

    private WebRequest request;

    private BindingResult bindingResult;

    private List<ParameterValidationResult> validationResults;

    public Problem build() {
        return new Problem()
                .withTitle(status.getReasonPhrase())
                .withDetail(nullToEmpty(message))
                .withType(nullToEmpty(type))
                .withStatus(status.value())
                .withInstance(((ServletWebRequest) request).getRequest().getRequestURI())
                .withViolations(buildViolations(bindingResult, validationResults));
    }

    private List<Violation> buildViolations(BindingResult bindErrors, List<ParameterValidationResult> validationErrors) {

        List<Violation> violations = new ArrayList<>();

        if (!isNull(bindErrors) && !ListUtils.isNullOrEmpty(bindErrors.getAllErrors())) {
            violations = bindErrors.getAllErrors()
                    .stream()
                    .map(error -> {
                        String name = error instanceof FieldError ? ((FieldError) error).getField() : error.getObjectName();
                        return new Violation(name, error.getDefaultMessage());
                    })
                    .collect(toList());
        }
        if (!ListUtils.isNullOrEmpty(validationErrors)) {
            for (ParameterValidationResult validationResult : validationErrors) {
                for (MessageSourceResolvable sourceResolvable : validationResult.getResolvableErrors()) {
                    if (!isNull(sourceResolvable.getArguments())) {
                        for (Object argument : sourceResolvable.getArguments()) {
                            if (argument instanceof DefaultMessageSourceResolvable messageSourceResolvable) {
                                violations.add(new Violation(messageSourceResolvable.getCode(), sourceResolvable.getDefaultMessage()));
                            }
                        }
                    }
                }
            }
        }
        return violations;
    }
}