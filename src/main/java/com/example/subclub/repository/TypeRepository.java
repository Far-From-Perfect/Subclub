package com.example.subclub.repository;

import com.example.subclub.entity.MediaType;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TypeRepository extends CrudRepository<MediaType, Integer> {
    Optional<MediaType> findByMediaTypename(String type);
}
