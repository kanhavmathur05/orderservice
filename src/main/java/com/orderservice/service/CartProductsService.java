package com.orderservice.service;

import java.util.List;

import com.orderservice.model.CartProducts;

public interface CartProductsService {

	CartProducts addProductToCart(CartProducts cartProducts);
	
	void removeProductFromCart(int id);
	
	CartProducts updateProductInCart(CartProducts cartProducts);
	
	List<CartProducts> getProductsInUserCart(String username);
	
	void deleteAllCartItems(String username);
}
