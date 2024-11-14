package br.edu.infnet.tiago.shared.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;

public class StringUtils {

    private static final String PACKAGE_PATTERN = "\\b([a-zA-Z_][a-zA-Z0-9_]*\\.)+[a-zA-Z_][a-zA-Z0-9_]*\\b";

    public static boolean isNullOrEmpty(String str) {
        return isNull(str) || str.isBlank();
    }

    public static boolean containsPackage(String text) {
        if (isNullOrEmpty(text)) return false;
        Pattern pattern = Pattern.compile(PACKAGE_PATTERN);
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }
}