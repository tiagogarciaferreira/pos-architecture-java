package br.edu.infnet.tiago.application.dto;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DirectorUpdateDTO {

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;

    @NotNull(message = "Date of Birth is required")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @NotNull(message = "Country ID cannot be null")
    @Min(value = 1, message = "Country ID must be greater than zero")
    private Long countryId;

    @ElementCollection
    @CollectionTable(name = "tb_director_awards", joinColumns = @JoinColumn(name = "director_id"))
    @Column(name = "award_name")
    @Size(max = 100, message = "The list of awards can contain a maximum of 100 items")
    private List<String> awards;
}