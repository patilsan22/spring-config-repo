package com.tradebulls.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.tradebulls.entity.Category;
import com.tradebulls.entity.Product;
import com.tradebulls.exception.CartException;
import com.tradebulls.repo.CategoryRepo;
import com.tradebulls.repo.ProductRepo;

@Service
public class CartServiceImpl {
	
		@Autowired
		private CategoryRepo categoryRepo;
		
		@Autowired
		private ProductRepo productRepo;
		
		public Iterable<Category> saveCategory(List<Category> category) {
			return categoryRepo.saveAll(category);  //return policy;
		}

		public Iterable<Product> saveProduct(List<Product> productList) {
			return productRepo.saveAll(productList);
		}
		
		public Iterable<Category> getAllCategories() {
			return categoryRepo.findAll(); //to get the all list
		}
		
		public Iterable<Product> getAllProducts() {
			return productRepo.findAll(); //to get the all list
		}
		
		public Optional<Category> getCategoryById(Integer Id) {
			Optional<Category> category=categoryRepo.findById(Id);//null
			if(category==null) { //6==null or null==null
				throw new CartException("Policy id "+Id+" incorrect..");
			}
			return category;
		}
		
		public Optional<Product> getProductById(Long Id) {
			Optional<Product> product=productRepo.findById(Id);//null
			if(product==null) { //6==null or null==null
				throw new CartException("Policy id "+Id+" incorrect..");
			}
			return product;
		}
		
		public void deleteCategory(Integer id) {
			categoryRepo.deleteById(id);
		}
		
		public void deleteProduct(Long id) {
			productRepo.deleteById(id);
		}
		
		public void updateCategoryById(Integer id, Category categoryDetails) {
			Category updateCategory = categoryRepo.findById(id).orElse(null);
			updateCategory.setCategoryId(categoryDetails.getCategoryId());
			updateCategory.setCategoryName(categoryDetails.getCategoryName());
			updateCategory.setProducts(categoryDetails.getProducts());
			categoryRepo.save(updateCategory);
		}
		
		public void updateProductById(Long id, Product productDetails) {
			Product updateProduct = productRepo.findById(id).orElse(null);
			updateProduct.setProductId(productDetails.getProductId());
			updateProduct.setCategory(productDetails.getCategory()); 
			updateProduct.setName(productDetails.getName());
			productRepo.save(updateProduct);
		}
		
		public List<Product> getAllEmployees(Integer pageNo, Integer pageSize, String sortBy)
		    {
			   Pageable paging = PageRequest.of(pageNo, pageSize);

			   Page<Product> pagedResult = productRepo.findAll(paging);

		        if(pagedResult.hasContent()) {
		            return pagedResult.getContent();
		        } else {
		            return new ArrayList<Product>();
		        }
		    }
		
//		public void updateCustomerById(String id, CustomerDetails customerDetails) {
//			CustomerDetails updateCustomer = customerRepository.findById(id).orElse(null);
//			updateCustomer.setCust_name(customerDetails.getCust_name());
//			updateCustomer.setCust_city(customerDetails.getCust_city());
//			updateCustomer.setCust_status(customerDetails.getCust_status());
//			customerRepository.save(updateCustomer);
//		}
		
//		public List<Category> saveCustomer(List<Category> customerList) {
//			return (List<Category>) categoryRepo.saveAll(customerList);
//		}

		// get pending orders of Product with catagory games 
//		public List<Product> getAllProduct() {
//			List<Product> products = productRepo.findAll();
//			return products;
//		}
//		
//		public List<Category> getAllCustomers() {
//			List<Category> categories = categoryRepo.findAll();
//			return categories;
//		}
		
//		public void saveAllCustomerFullDetails() {
//			Map<String, Object> outerMap = new HashMap<>();
//			List<CustomerDetails> list = new ArrayList<>();
//			list = getAllCustomers();
//			for (CustomerDetails customerDetails : list) {
//				String cust_id = customerDetails.getCust_ID();
//				hashOperations.put(hashReference, cust_id, (outerMap = getAddressandBankDetails(cust_id)));
//			}
//		}
//		
//		public Policy savePolicy(Policy policy) {
//			logger.info("Policy Service Implementation : savePolicy() method");
//			return repo.save(policy);  //return policy;
//		}
		
	
}