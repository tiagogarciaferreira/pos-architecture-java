package br.edu.infnet.tiago.domain.service;


import br.edu.infnet.tiago.domain.repository.DirectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DirectorService {

    private final DirectorRepository directorRepository;
}
