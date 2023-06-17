package com.tradebulls.service;

import java.util.List;

import com.tradebulls.entity.Category;
import com.tradebulls.entity.Product;

public interface CartService {
		
		public Iterable<Product> saveProduct(List<Product> productList) ;
		
		public List<Category> saveCustomer(List<Category> customerList);
		
		public List<Product> getAllProduct() ;
		
		public List<Category> getAllCustomers();
		
		public void saveAllCustomerFullDetails(); 
		
	
}