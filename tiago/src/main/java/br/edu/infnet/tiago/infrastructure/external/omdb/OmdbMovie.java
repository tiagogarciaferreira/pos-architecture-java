package br.edu.infnet.tiago.infrastructure.external.omdb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static br.edu.infnet.tiago.shared.constants.GlobalConstants.EMPTY;
import static br.edu.infnet.tiago.shared.constants.GlobalConstants.NOT_AVAILABLE;
import static br.edu.infnet.tiago.shared.utils.StringUtils.isNullOrEmpty;
import static br.edu.infnet.tiago.shared.utils.StringUtils.nullToEmpty;
import static java.lang.Double.parseDouble;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import static java.time.LocalDate.parse;
import static java.time.format.DateTimeFormatter.ofPattern;
import static java.util.Locale.ENGLISH;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OmdbMovie {

    @JsonProperty("Poster")
    private String poster;

    private LocalDate releaseDate;

    private int durationMinutes;

    private Double boxOfficeDollars;

    private Float imdbRating;

    @JsonSetter("Released")
    public void setRelease(String release) {
        release = nullToEmpty(release).replaceAll(NOT_AVAILABLE, EMPTY);
        this.releaseDate = isNullOrEmpty(release) ? null : parse(release, ofPattern("dd MMM yyyy", ENGLISH));
    }

    @JsonSetter("Runtime")
    public void setDuration(String runtime) {
        runtime = nullToEmpty(runtime).replaceAll(NOT_AVAILABLE, EMPTY);
        this.durationMinutes = isNullOrEmpty(runtime) ? 0 : parseInt(runtime.replaceAll("[^0-9]", EMPTY));
    }

    @JsonSetter("BoxOffice")
    public void setBoxOffice(String boxOffice) {
        boxOffice = nullToEmpty(boxOffice).replaceAll(NOT_AVAILABLE, EMPTY);
        this.boxOfficeDollars = isNullOrEmpty(boxOffice) ? 0 : parseDouble(boxOffice.replaceAll("[^0-9]", EMPTY));
    }

    @JsonSetter("imdbRating")
    public void setRating(String rating) {
        rating = nullToEmpty(rating).replaceAll(NOT_AVAILABLE, EMPTY);
        this.imdbRating = isNullOrEmpty(rating) ? 0 : parseFloat(rating);
    }
}