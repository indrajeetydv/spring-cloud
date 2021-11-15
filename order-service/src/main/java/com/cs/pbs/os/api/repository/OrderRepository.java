package com.cs.pbs.os.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cs.pbs.os.api.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
