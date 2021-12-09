package com.getir.readingisgood.adapters.postgre.repository;

import com.getir.readingisgood.adapters.postgre.entity.OrderEntity;
import com.getir.readingisgood.adapters.postgre.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends BaseRepository<OrderEntity, Long> {
    List<OrderEntity> findOrderEntityByUser(UserEntity user);
}
