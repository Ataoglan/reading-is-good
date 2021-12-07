package com.getir.readingisgood.adapters.postgre.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends PagingAndSortingRepository<T, ID> {
    @Query("select t from #{#entityName} t where  t.deleted = false")
    List<T> findAllRecords();

    @Query("select t from #{#entityName} t where  t.deleted = false")
    Page<T> findAllRecordsPageable(Pageable pageable);

    @Query("select t from #{#entityName} t")
    List<T> findAllWithDeleted();
}
