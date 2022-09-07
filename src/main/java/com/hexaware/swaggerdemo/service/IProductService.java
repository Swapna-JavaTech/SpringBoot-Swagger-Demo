package com.hexaware.swaggerdemo.service;

import java.util.List;
import java.util.Optional;

import com.hexaware.swaggerdemo.entity.Product;

public interface IProductService {
	
	List<Product> getAllProducts();
	Optional<Product> getProductById(Long pId);
	Product saveProduct(Product product);
	void deleteProduct(Long pId);
	Product updateProduct(Product product);
	

}
