package com.example.subclub.controllers;

import com.example.subclub.dto.TitleDTO;
import com.example.subclub.services.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
public class TitleController {
    private final MediaService mediaService;

    @PostMapping("/create-title")
    public ResponseEntity<?> createTitle(@RequestBody TitleDTO request) {
        return mediaService.saveTitle(request);
    }

    @GetMapping("/films")
    public Collection<TitleDTO> filmsData() {
        return mediaService.findAllTitlesByType("Films");
    }

    @GetMapping("/series")
    public Collection<TitleDTO> seriesData() {
        return mediaService.findAllTitlesByType("Series");
    }

    @GetMapping("/short-films")
    public Collection<TitleDTO> shortFilmsData() {
        return mediaService.findAllTitlesByType("Short_films");
    }
}
