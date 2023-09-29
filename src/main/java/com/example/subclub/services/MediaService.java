package com.example.subclub.services;

import com.example.subclub.dto.ApplicationError;
import com.example.subclub.dto.TitleDTO;
import com.example.subclub.entity.Titles;
import com.example.subclub.repository.MediaRepository;
import com.example.subclub.repository.TypeRepository;
import com.example.subclub.untils.TitleMapper;
import com.example.subclub.untils.TitleRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MediaService {

    private final MediaRepository mediaRepository;
    private final TypeRepository typeRepository;
    private final JdbcTemplate jdbcTemplate;
    private final TitleMapper titleMapper;
    private final TitleRowMapper titleRowMapper;

    public Optional<Titles> findByTitle(String title) {
        return mediaRepository.findByTitle(title);
    }

    public List<Titles> loadTitlesByMediaType(String mediaType) {
        return jdbcTemplate.query("""
                SELECT t.id, t.title, t.description, media_type.name
                FROM titles as t 
                    left join content_type
                        on t.id=content_type.title_id
                    left join media_type
                        on content_type.media_type_id=media_type.id
                WHERE media_type.name=?;
                """, titleRowMapper, mediaType);
    }

    public List<TitleDTO> findAllTitlesByType(String mediaType) {
        List<TitleDTO> titleDTOList = new ArrayList<>();
        loadTitlesByMediaType(mediaType).stream().forEach(title -> titleDTOList.add(titleMapper.toDto(title)));
        return titleDTOList;
    }

    public ResponseEntity<?> saveTitle(TitleDTO titleDTO) {
        if (findByTitle(titleDTO.getTitle()).isPresent()) {
            return new ResponseEntity<>(new ApplicationError(HttpStatus.BAD_REQUEST.value(), "Title already exist"), HttpStatus.BAD_REQUEST);
        }

        Titles title = new Titles()
                .setTitle(titleDTO.getTitle())
                .setDescription(titleDTO.getDescription())
                .setMediaType(typeRepository.findByMediaTypename(titleDTO.getMediaType()).get());
        mediaRepository.save(title);
        log.info("Saved new title: %s ", title.getTitle());
        return ResponseEntity.ok(titleDTO.getTitle());
    }

}
