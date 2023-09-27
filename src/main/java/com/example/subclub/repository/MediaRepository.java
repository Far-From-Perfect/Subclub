package com.example.subclub.repository;

import com.example.subclub.entity.Titles;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MediaRepository extends CrudRepository<Titles, Long> {
    Optional<Titles> findByTitle(String title);
}
