package com.example.subclub.untils;

import com.example.subclub.dto.TitleDTO;
import com.example.subclub.entity.Titles;
import com.example.subclub.repository.MediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class TitleMapper implements IMapper<TitleDTO, Titles> {

    private final MediaRepository mediaRepository;
    @Override
    public TitleDTO toDto(Titles entity) {
        return new TitleDTO()
                .setTitle(entity.getTitle())
                .setDescription(entity.getDescription())
                .setMediaType(entity.getMediaType().toString());
    }

    @Override
    public List<TitleDTO> toDtoList(List<Titles> entityList) {
        return entityList.stream().map(this::toDto).toList();
    }

    @Override
    public Titles toEntity(TitleDTO dto) {
        return mediaRepository.findByTitle(dto.getTitle()).orElseThrow(() -> new NoSuchElementException(
                String.format("Title %s not found", dto.getTitle()))
        );
    }

    @Override
    public List<Titles> toEntityList(List<TitleDTO> dtoList) {
        return dtoList.stream().map(this::toEntity).toList();
    }
}
