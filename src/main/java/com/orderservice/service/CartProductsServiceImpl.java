package com.orderservice.service;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderservice.model.CartProducts;
import com.orderservice.repository.CartProductsRepository;

@Service
public class CartProductsServiceImpl implements CartProductsService {

	@Autowired
	CartProductsRepository cartProductsRepository;

//	@Autowired
//	ProductService productService;

	@Override
	public CartProducts addProductToCart(CartProducts cartProducts) {

		return cartProductsRepository.save(cartProducts);
	}

	@Override
	public void removeProductFromCart(int id) {

		cartProductsRepository.deleteById(id);

	}

	@Override
	public CartProducts updateProductInCart(CartProducts cartProducts) {
		return cartProductsRepository.save(cartProducts);
	}

	@Override
	public List<CartProducts> getProductsInUserCart(String username) {
		return cartProductsRepository.findByUserNameIs(username);
	}

	@Override
	public void deleteAllCartItems(String username) {
		
		List<CartProducts> cartProductList = cartProductsRepository.findByUserNameIs(username); 
		for(CartProducts cartProduct:cartProductList) {
			cartProductsRepository.delete(cartProduct);
		}
		
	}
	

//	@Override
//	public List<CartProductItem> getProductsInUserCart(Long userId) {
//
//		List<CartProducts> cartProducts = cartProductsRepository.findByUserIdIs(userId);
//		List<CartProductItem> cartProductList = new ArrayList<>();
//		for (CartProducts cartProduct : cartProducts) {
//			CartProductItem item = new CartProductItem();
//			Product product = productService.getProduct(cartProduct.getProductId());
//			item.setId(cartProduct.getId()); // set ID of product in cart i.e. cart product ID
//
//			item.setPrice(product.getPrice());
//			item.setProductImage(product.getProductImage());
//			item.setProductName(product.getProductName());
//			item.setProductDescription(product.getProductDescription());
//
//			cartProductList.add(item);
//		}
//
//		return cartProductList;
//	}

}
