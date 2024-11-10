package br.edu.infnet.tiago.shared.utils;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Objects.isNull;

public class ListUtils {

    public static <T> List<T> defaultIfNull(List<T> list) {
        return isNull(list) ? emptyList() : list;
    }

    public static boolean isNullOrEmpty(List<?> list) {
        return isNull(list) || list.isEmpty();
    }

    public static <T> List<T> getValidValues(List<T> list) {
        return defaultIfNull(list)
                .stream()
                .filter(value -> value instanceof String ? !StringUtils.isNullOrEmpty((String) value) : !isNull(value))
                .distinct()
                .toList();
    }

    public static List<String> toLowerCase(List<String> list) {
        return getValidValues(list)
                .stream()
                .map(String::toLowerCase)
                .toList();
    }
}