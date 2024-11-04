package br.edu.infnet.tiago.domain.service;


import br.edu.infnet.tiago.domain.repository.StudioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudioService {

    private final StudioRepository studioRepository;
}
