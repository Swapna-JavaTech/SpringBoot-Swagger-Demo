package com.hexaware.swaggerdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.hexaware.swaggerdemo.entity.Product;
import com.hexaware.swaggerdemo.respository.ProductRepository;

@Service
public class ProductServiceImpl implements IProductService {
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Optional<Product> getProductById(Long pId) {
		
		return productRepository.findById(pId);
	}

	@Override
	public Product saveProduct(Product product) {
		
		return productRepository.save(product);
	}

	@Override
	public void deleteProduct(Long pId) {
		
		productRepository.deleteById(pId);
	}

	@Override
	public Product updateProduct(Product product) {
		
		return productRepository.save(product);
	}

}
