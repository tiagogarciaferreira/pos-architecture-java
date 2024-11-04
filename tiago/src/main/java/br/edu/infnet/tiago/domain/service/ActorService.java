package br.edu.infnet.tiago.domain.service;


import br.edu.infnet.tiago.domain.repository.ActorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActorService {

    private final ActorRepository actorRepository;
}