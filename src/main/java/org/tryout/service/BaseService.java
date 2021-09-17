package org.tryout.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface BaseService<T> {
    default List<T> listAll() {
        return Streamable.of(getRepository().findAll()).stream()
                .collect(Collectors.toList());
    }

    default Optional<T> findById(Long id) {
        return getRepository().findById(id);
    }

    CrudRepository<T, Long>  getRepository();
}
