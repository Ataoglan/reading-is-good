package com.getir.readingisgood.adapters.postgre.repository;

import com.getir.readingisgood.adapters.postgre.entity.BookEntity;

import java.util.Optional;

public interface BookRepository extends BaseRepository<BookEntity, Long> {
    Optional<BookEntity> findBookEntitiesByName(String bookName);
}
