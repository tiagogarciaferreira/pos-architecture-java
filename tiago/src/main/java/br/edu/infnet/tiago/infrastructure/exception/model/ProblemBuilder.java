package br.edu.infnet.tiago.infrastructure.exception.model;

import br.edu.infnet.tiago.shared.utils.ListUtils;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@With
@AllArgsConstructor
@NoArgsConstructor
public class ProblemBuilder {

    private String message;

    private HttpStatus status;

    private WebRequest request;

    private BindingResult bindingResult;

    public Problem build() {
        return new Problem()
                .withTitle(status.getReasonPhrase())
                .withDetail(message)
                .withStatus(status.value())
                .withInstance(((ServletWebRequest) request).getRequest().getRequestURI())
                .withViolations(buildViolations(bindingResult));
    }

    private List<Violation> buildViolations(BindingResult errors) {

        List<Violation> violations = new ArrayList<>();
        if (isNull(errors) || ListUtils.isNullOrEmpty(errors.getAllErrors())) return violations;

        violations = errors.getAllErrors()
                .stream()
                .map(error -> {
                    String name = error instanceof FieldError ? ((FieldError) error).getField() : error.getObjectName();
                    return new Violation(name, error.getDefaultMessage());
                })
                .toList();
        return violations;
    }
}