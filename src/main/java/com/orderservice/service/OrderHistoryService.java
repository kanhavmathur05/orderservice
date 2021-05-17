package com.orderservice.service;

import java.util.List;

import com.orderservice.model.OrderHistory;

public interface OrderHistoryService {

	OrderHistory addOrderHistory(OrderHistory orderHistory);
	OrderHistory getOrderHistory(int id);
	List<OrderHistory> getOrderHistoryForUser(String username);
}
