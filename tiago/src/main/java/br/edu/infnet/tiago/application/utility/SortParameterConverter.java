package br.edu.infnet.tiago.application.utility;

import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

import static br.edu.infnet.tiago.application.constants.SortConstants.ASCENDING_SORT_DIRECTION;
import static br.edu.infnet.tiago.application.constants.SortConstants.DEFAULT_SORT_PARAM;
import static br.edu.infnet.tiago.shared.constants.GlobalConstants.COMMA;
import static java.util.Arrays.stream;
import static java.util.Objects.isNull;

public class SortParameterConverter {

    public static Sort toSort(String[] sortParams) {

        List<String> params = stream(isNull(sortParams) || sortParams.length == 0 ? new String[]{DEFAULT_SORT_PARAM} : sortParams).toList();
        if (params.stream().noneMatch(param -> param.contains(COMMA))) params = List.of(String.join(COMMA, params));
        List<Sort.Order> orders = new ArrayList<>(sortParams.length);

        for (String param : params) {
            String[] parts = param.split(COMMA);
            String direction = parts.length > 1 ? parts[1] : ASCENDING_SORT_DIRECTION;
            Sort.Order order = direction.equalsIgnoreCase(ASCENDING_SORT_DIRECTION) ? Sort.Order.asc(parts[0]) : Sort.Order.desc(parts[0]);
            orders.add(order);
        }
        return Sort.by(orders);
    }
}