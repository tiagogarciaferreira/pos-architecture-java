package br.edu.infnet.tiago.shared.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import static br.edu.infnet.tiago.shared.constants.GlobalConstants.EMPTY;
import static br.edu.infnet.tiago.shared.utils.StringUtils.containsPackage;
import static java.util.Locale.US;

@RequiredArgsConstructor
@Component
public class MessageProvider {

    private final MessageSource messageSource;

    public String getErrorMessage(String message, String key) {
        return containsPackage(message) ? getMessage(key) : message.replaceAll("\"", EMPTY);
    }

    public String getMessage(String key) {
        return messageSource.getMessage(key, null, EMPTY, US);
    }
}