package com.orderservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.orderservice.exception.ResourceNotFoundException;
import com.orderservice.model.CartProducts;
import com.orderservice.service.CartProductsService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CartProductsController {

	@Autowired
	CartProductsService cartProductsService;
	
	Logger logger=LoggerFactory.getLogger(CartProductsController.class);

	@PostMapping("/add-product-to-cart")
	public ResponseEntity<CartProducts> addProductToCart(@RequestBody CartProducts cartProduct) throws ResourceNotFoundException {
		CartProducts newCartProduct = cartProductsService.addProductToCart(cartProduct);
		if(newCartProduct==null) {
			logger.error("Unable to add product to cart");
			throw new ResourceNotFoundException("Unable to add the product to cart");
		}
		else {
			logger.info("Product successfully added to cart");
			return ResponseEntity.ok().body(newCartProduct);			
		}

	}
	
	@GetMapping("/cart-products/{username}")
	public ResponseEntity<List<CartProducts>> getCartProducts(@PathVariable String username) throws ResourceNotFoundException {
		List<CartProducts> cartProductList = cartProductsService.getProductsInUserCart(username);
		if(cartProductList==null) {
			logger.error("Unable to get products in cart for User: ",username);
			throw new ResourceNotFoundException("Unable to get cart products for user: "+username);
		}
		else
		{
			logger.info("Successfully get cart products for user: ",username);
			return ResponseEntity.ok().body(cartProductList);			
		}
	}

//	@GetMapping("cart-products/{userId}")
//	public ResponseEntity<List<CartProductItem>> getCartProducts(@PathVariable Long userId) throws ResourceNotFoundException{
//		List<CartProductItem> cartProductList = cartProductsService.getProductsInUserCart(userId);
//		if(cartProductList==null) {
//			logger.error("Unable to get products in cart for User: ",userId);
//			throw new ResourceNotFoundException("Unable to get cart products for user: "+userId);
//		}
//		else
//		{
//			logger.info("Successfully get cart products for user: ",userId);
//			return ResponseEntity.ok().body(cartProductList);			
//		}
//
//	}

	@DeleteMapping("/remove-product-from-cart/{id}")
	public void removeProductFromCart(@PathVariable int id) {
		cartProductsService.removeProductFromCart(id);
	}

	@PutMapping("/update-product-in-cart")
	public void updateProductInCart(@RequestBody CartProducts cartProduct) {
		cartProductsService.updateProductInCart(cartProduct);
	}
}
