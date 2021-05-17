package com.orderservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orderservice.model.OrderHistory;

@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Integer>{

//	List<OrderHistory> findByUserIdIs(Long UserId);
	
	List<OrderHistory> findByUserNameIs(String username);
}
