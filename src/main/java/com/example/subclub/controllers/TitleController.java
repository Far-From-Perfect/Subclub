package com.example.subclub.controllers;

import com.example.subclub.dto.TitleDTO;
import com.example.subclub.services.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TitleController {
    private final MediaService mediaService;

    @PostMapping("/create-title")
    public ResponseEntity<?> createTitle(@RequestBody TitleDTO request) {
        return mediaService.saveTitle(request);
    }
}
