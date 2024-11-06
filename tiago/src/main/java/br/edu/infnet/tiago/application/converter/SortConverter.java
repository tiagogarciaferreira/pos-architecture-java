package br.edu.infnet.tiago.application.converter;

import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public class SortConverter {

    public static Sort toSort(String[] sortParams) {

        sortParams = isNull(sortParams) || sortParams.length == 0 ? new String[]{"id,desc"} : sortParams;
        List<Sort.Order> orders = new ArrayList<>(sortParams.length);

        for (String param : sortParams) {
            String[] parts = param.split(",");
            String direction = parts.length > 1 ? parts[1] : "asc";
            Sort.Order order = direction.equalsIgnoreCase("asc") ? Sort.Order.asc(parts[0]) : Sort.Order.desc(parts[0]);
            orders.add(order);
        }
        return Sort.by(orders);
    }
}