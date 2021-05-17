package com.orderservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderservice.model.OrderHistory;
import com.orderservice.repository.OrderHistoryRepository;

@Service
public class OrderHistoryServiceImpl implements OrderHistoryService {

	@Autowired
	OrderHistoryRepository orderHistoryRepository;

	@Override
	public OrderHistory addOrderHistory(OrderHistory orderHistory) {

		return orderHistoryRepository.save(orderHistory);
	}

	@Override
	public OrderHistory getOrderHistory(int id) {

		return orderHistoryRepository.findById(id).get();
	}

//	@Override
//	public List<OrderHistory> getOrderHistoryForUser(Long userId) {
//
//		return orderHistoryRepository.findByUserIdIs(userId);
//	}

	@Override
	public List<OrderHistory> getOrderHistoryForUser(String username) {

		return orderHistoryRepository.findByUserNameIs(username);
	}

}
