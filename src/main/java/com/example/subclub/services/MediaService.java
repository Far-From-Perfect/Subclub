package com.example.subclub.services;

import com.example.subclub.dto.ApplicationError;
import com.example.subclub.dto.TitleDTO;
import com.example.subclub.entity.MediaType;
import com.example.subclub.entity.Titles;
import com.example.subclub.repository.MediaRepository;
import com.example.subclub.repository.TypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MediaService {

    private final MediaRepository mediaRepository;
    private final TypeRepository typeRepository;
    private final JdbcTemplate jdbcTemplate;

    public Optional<Titles> findByTitle(String title) {
        return mediaRepository.findByTitle(title);
    }

    public List<Titles> loadTitlesByMediaType(String mediaType) {
        return jdbcTemplate.queryForList("SELECT * FROM titles WHERE media_type_id=?", Titles.class, mediaType);
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
