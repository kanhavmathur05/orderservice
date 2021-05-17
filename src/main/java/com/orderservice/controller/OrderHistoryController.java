package com.orderservice.controller;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orderservice.model.CartProducts;
import com.orderservice.model.OrderHistory;
import com.orderservice.repository.OrderHistoryRepository;
import com.orderservice.service.CartProductsService;
import com.orderservice.service.OrderHistoryService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class OrderHistoryController {

	@Autowired
	OrderHistoryService orderHistoryService;

	@Autowired
	CartProductsService cartProductsService;
	
	@Autowired
	OrderHistoryRepository orderHistoryRepository;
	
//	@Autowired
//	ProductService productService;

//	@GetMapping("/order-history/{userId}")
//	public ResponseEntity<List<Product>> getOrderHistory(@PathVariable Long userId) {
//		List<OrderHistory> orderHistoryList = orderHistoryService.getOrderHistoryForUser(userId);
//		List<Product> productList = new ArrayList<>();
//		for (OrderHistory orderHistory : orderHistoryList) {
//			Product product = new Product();
//			product = productService.getProduct(orderHistory.getProductId());
//			productList.add(product);
//		}
//
//		return ResponseEntity.ok().body(productList);
//	}
	
	@GetMapping("/order-history/{username}")
	public ResponseEntity<List<OrderHistory>> getOrderHistory(@PathVariable String username) {
		List<OrderHistory> orderHistoryList = orderHistoryService.getOrderHistoryForUser(username);
//		List<OrderHistory> productList = new ArrayList<>();
//		for (OrderHistory orderHistory : orderHistoryList) {
//			Product product = new Product();
//			product = productService.getProduct(orderHistory.getProductId());
//			productList.add(product);
//		}

		return ResponseEntity.ok().body(orderHistoryList);
	}

	@GetMapping("/get-order-history/{id}")
	public void getOneOrderHistory(int id) {
		orderHistoryService.getOrderHistory(id);
	}
	
	@GetMapping("/payment/{username}")
	public ResponseEntity<?> doPayment(@PathVariable String username) { 

		List<CartProducts> cartProductList=cartProductsService.getProductsInUserCart(username);
		List<OrderHistory> orderHistoryList=new ArrayList<>();
		for(CartProducts cartProduct:cartProductList) {
			OrderHistory orderHistory=new OrderHistory();
			orderHistory.setProductName(cartProduct.getProductName());
			orderHistory.setProductDescription(cartProduct.getProductDescription());
			orderHistory.setPrice(cartProduct.getPrice());
			orderHistory.setProductImage(cartProduct.getProductImage());
			orderHistory.setUserName(cartProduct.getUserName());
			
			orderHistoryList.add(orderHistory);
		}
		// perform save list in db
		List<OrderHistory> response = orderHistoryRepository.saveAll(orderHistoryList);
		cartProductsService.deleteAllCartItems(username);
		return ResponseEntity.ok(response);
	}
}
