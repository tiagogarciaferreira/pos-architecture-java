package br.edu.infnet.tiago.shared.utils;

import static java.util.Objects.isNull;

public class StringUtils {

    public static boolean isNullOrEmpty(String str) {
        return isNull(str) || str.isBlank();
    }

    public static String nullToEmpty(String str) {
        return isNullOrEmpty(str) ? "" : str.trim();
    }
}