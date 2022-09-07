package com.hexaware.swaggerdemo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.swaggerdemo.entity.Product;
import com.hexaware.swaggerdemo.service.IProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/product")
@Api(value = "Product Management System",description = "Operations of Product class")
public class ProductController {
	
	@Autowired
	private IProductService productService;
	
	@GetMapping("/products")
	@ApiOperation(value = "view a list of products",response = List.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200,message = "Successfully retrieved list of products"),
			@ApiResponse(code = 401,message = "you are not authorized to view this resource"),
			@ApiResponse(code = 403,message = "accessing of this resource forbidden"),
			@ApiResponse(code = 404,message = "Resource not found"),
	})
	List<Product> getAllProducts(){
		return productService.getAllProducts();
	}
	
	
	@GetMapping("/products/{id}")
	@ApiOperation(value = "Get an product by id")
	Optional<Product> getProductById(
			@ApiParam (value="returns the product with id",required = true)
			@PathVariable("id") Long pId){
		return productService.getProductById(pId);
	}
	
	@PostMapping("/products")
	@ApiOperation(value = "add an product")
	Product saveProduct(
			@ApiParam (value = "Product object is stored in database",required = true)
			@RequestBody Product product) {
		return productService.saveProduct(product);
		
	}
	
	
	@DeleteMapping("/products/{id}")
	@ApiOperation(value = "deletes a product")
	void deleteProduct(
			@PathVariable("id") Long pId) {
		productService.deleteProduct(pId);
	}
	
	@PutMapping("/products/{id}")
	@ApiOperation(value = "update an product.......")
	Product updateProduct(
			@ApiParam (value = "Product object to update",required = true)
			 @Valid @RequestBody Product product,
			@ApiParam (value="returns the product with id to update",required = true)
			@PathVariable("id") Long pId) throws Exception {
		Product existingProduct =  productService.getProductById(pId)
				.orElseThrow(() -> new Exception("Product not found with id" + pId));
		existingProduct.setProductId(product.getProductId());
		existingProduct.setProductName(product.getProductName());
		existingProduct.setProductPrice(product.getProductPrice());
		
		return productService.updateProduct(existingProduct);
	}

}
