package br.edu.infnet.tiago.application.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MapperFactory {

    private final ModelMapper modelMapper;

    public <S, D> D mapToNewInstance(S source, Class<D> destinationClass) {
        return modelMapper.map(source, destinationClass);
    }

    public <S, D> void mapToExistingInstance(S source, D destination) {
        modelMapper.map(source, destination);
    }
}