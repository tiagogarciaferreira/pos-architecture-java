package br.edu.infnet.tiago.application.dto.filter;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudioFilterDTO {

    @Size(max = 100, message = "Studio IDs must contain no more than 100 elements")
    private List<Long> ids;

    @Size(max = 50, message = "Name must be at most 50 characters")
    private String name;
}