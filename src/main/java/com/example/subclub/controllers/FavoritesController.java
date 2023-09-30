package com.example.subclub.controllers;

import com.example.subclub.dto.TitleDTO;
import com.example.subclub.services.FavoritesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/favorites")
@RequiredArgsConstructor
public class FavoritesController {

    private final FavoritesService favoritesService;

    @GetMapping("/{id}")
    public Collection<TitleDTO> getFavorites(@PathVariable("id") Long id) {
        return favoritesService.getFavorites(id);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> addToFavorites(@PathVariable("id") Long id, @RequestBody TitleDTO titleDTO) {
        return favoritesService.addToFavorites(id, titleDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTitleFromFavorites(@PathVariable("id") Long id, @RequestBody TitleDTO titleDTO) {
        return favoritesService.deleteTitleFromFavorites(id, titleDTO);
    }
}
