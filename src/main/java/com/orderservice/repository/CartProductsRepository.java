package com.orderservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orderservice.model.CartProducts;

@Repository
public interface CartProductsRepository extends JpaRepository<CartProducts, Integer>{

	List<CartProducts> findByUserIdIs(Long userId);
	
	List<CartProducts> findByUserNameIs(String username);
	
}
