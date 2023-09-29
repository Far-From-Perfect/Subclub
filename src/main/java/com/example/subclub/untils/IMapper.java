package com.example.subclub.untils;

import java.util.List;

public interface IMapper<D, E> {
    D toDto(E entity);
    List<D> toDtoList(List<E> entityList);
    E toEntity(D dto);
    List<E> toEntityList(List<D> dtoList);
}
