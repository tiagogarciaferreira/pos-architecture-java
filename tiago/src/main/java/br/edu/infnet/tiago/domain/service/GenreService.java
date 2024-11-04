package br.edu.infnet.tiago.domain.service;


import br.edu.infnet.tiago.domain.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;
}
