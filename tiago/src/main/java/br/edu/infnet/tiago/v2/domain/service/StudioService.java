package br.edu.infnet.tiago.v2.domain.service;


import br.edu.infnet.tiago.v2.domain.model.Studio;
import br.edu.infnet.tiago.v2.domain.repository.StudioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StudioService {

    private final StudioRepository studioRepository;

    @Transactional
    public Studio create(Studio studio) {
        return studioRepository.save(studio);
    }

    @Transactional(readOnly = true)
    public Page<Studio> search(Specification<Studio> specification, Pageable pageable) {
        return studioRepository.findAll(specification, pageable);
    }

    @Transactional(readOnly = true)
    public Studio getById(Long studioId) {
        return studioRepository.findById(studioId).get();
    }

    @Transactional
    public Studio update(Studio studio) {
        return studioRepository.save(studio);
    }

    @Transactional
    public void delete(Long studioId) {
        studioRepository.deleteById(studioId);
    }
}