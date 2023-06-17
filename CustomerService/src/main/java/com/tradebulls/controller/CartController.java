
package com.tradebulls.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tradebulls.entity.Category;
import com.tradebulls.entity.Product;
import com.tradebulls.exception.CartException;
import com.tradebulls.service.CartServiceImpl;

@RestController
@RequestMapping("/api")
public class CartController {
	
	
	@Autowired
	private CartServiceImpl productService ;
	
	@PostMapping("/categories")
	public ResponseEntity<Iterable<Category>> createCategory(@RequestBody List<Category> categoryList)
			throws Exception {
		Iterable<Category> createdCategory = this.productService.saveCategory(categoryList);
		return ResponseEntity.ok().body(createdCategory); //createdPolicy that is return to createPolicy
	}
	
	@PostMapping("/products")
	public ResponseEntity<Iterable<Product>> createProduct(@RequestBody List<Product> productList)
			throws Exception {
		Iterable<Product> createdProduct = this.productService.saveProduct(productList);
		return ResponseEntity.ok().body(createdProduct); //createdPolicy that is return to createPolicy
	}
	
	@GetMapping("/getAllCategories")
	public @ResponseBody Iterable<Category> getAllCategorieList() throws Exception {
		return productService.getAllCategories();  //here we are calling //
	}
	
	@GetMapping("/getAllProduct")
	public @ResponseBody Iterable<Product> getAllProductsList() throws Exception {
		return productService.getAllProducts();  //here we are calling //
	}
	
	/*
	 * This method is used to get the policy details of user by using user id.
	 */
	@GetMapping("/getCategory/{id}")
	public ResponseEntity<Optional<Category>> getCategoryById(@PathVariable("id") Integer id) { //getting data by using id.
		Optional<Category> policy = productService.getCategoryById(id); //method calling
		return ResponseEntity.ok().body(policy);
	}
	
	@GetMapping("/getProduct/{id}")
	public ResponseEntity<Optional<Product>> getProductById(@PathVariable("id") Long id) { //getting data by using id.
		Optional<Product> product = productService.getProductById(id); //method calling
		return ResponseEntity.ok().body(product);
	}
	
	@PutMapping("/updateCategory/{id}")
	public ResponseEntity<String> updateCustomer(@PathVariable Integer id, @RequestBody Category categoryDetails) {
		try {
			productService.updateCategoryById(id, categoryDetails);
			return ResponseEntity.ok("Record Updated");
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/updateProduct/{id}")
	public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
		try {
			productService.updateProductById(id, productDetails);
			return ResponseEntity.ok("Record Updated");
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/deleteCategory/{id}")
	public ResponseEntity<String> deleteCategoryById(@PathVariable("id") Integer id) throws CartException {
		try {
			productService.deleteCategory(id);
			return ResponseEntity.ok("Record Deleted");
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/deleteProduct/{id}")
	public ResponseEntity<String> deleteProductById(@PathVariable("id") Long id) throws CartException {
		try {
			productService.deleteProduct(id);
			return ResponseEntity.ok("Record Deleted");
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
    @GetMapping("/getAllProductPaging")
    public ResponseEntity<List<Product>> getAllProduct(
                        @RequestParam(defaultValue = "0") Integer pageNo,
                        @RequestParam(defaultValue = "10") Integer pageSize,
                        @RequestParam(defaultValue = "id") String sortBy)
    {
        List<Product> list = productService.getAllEmployees(pageNo, pageSize, sortBy);

        return new ResponseEntity<List<Product>>(list, new HttpHeaders(), HttpStatus.OK);
    }
	

	

	
	//-----------------------------------------------------------------------
	
//	@PostMapping("/saveProduct")
//	public List<Product> saveProduct(@RequestBody List<Product> productList){
//		System.out.println("productList :"+productList);
//		return productService.saveProduct(productList);
//	}
//	
//	@PostMapping("/saveCustomer")
//	public List<Category> saveCustomer(@RequestBody List<Category> categoryList){
//		System.out.println("customerList :"+categoryList);
//		return productService.saveCustomer(categoryList);
//	}
//		
//	@GetMapping("/getAllProducts")
//	public List<Product> getAllProducts() {
//		List<Product> products = productService.getAllProduct();
//		return products ;
//	}
//
//	@GetMapping("/getAllCustomers")
//	public List<Category> getAllCustomers() {
//		List<Category> customers = productService.getAllCustomers();
//		System.out.println("customers :"+customers.toString());
//		return  customers;
//	}
//	
//
//	
//	//-------------------------------------------------------------------------
//	
//	@GetMapping("/getAll")
//	public ResponseEntity<?> getAll() {
//		List<CustomerDetails> allCustomers = customerService.getAllCustomers();
//		System.out.println(allCustomers.isEmpty());
//		if (allCustomers.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		} else {
//			return ResponseEntity.ok(allCustomers);
//		}
//	}
//
//	@PostMapping("/addCustomer")
//	public ResponseEntity<?> addCustomerDetail(@RequestBody CustomerDetails customerDetails) {
//
//		if (customerDetails.getCust_name() != null) {
//			customerService.addCustomer(customerDetails);
//			return ResponseEntity.ok(customerDetails);
//		}
//		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//	}
//
//	@GetMapping("/getCustomerById/{id}")
//	public ResponseEntity<CustomerDetails> getCustomerById(@PathVariable(value = "id") String id) {
//		if (id != null) {
//			CustomerDetails customerByID = customerService.getCustomerByID(id);
//			return ResponseEntity.ok(customerByID);
//		} else {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
//	}
//	
//	@GetMapping("/saveCustomerFullDetails")
//	public ResponseEntity<String> saveAllCustomerFullDetails() {
//		customerService.saveAllCustomerFullDetails();
//		return ResponseEntity.ok("Record added in Redis");
//	}
//
//	@DeleteMapping("deleteById/{id}")
//	public String deleteRecord(@PathVariable String id) {
////		Customer customer = customerService.getCustomerByID(id);
//		return customerService.deleteCustomerById(id);
//	}
//
//	@PutMapping("/update/{id}")
//	public ResponseEntity<String> updateCustomer(@PathVariable String id, @RequestBody CustomerDetails customerDetails) {
//		try {
//			customerService.updateCustomerById(id, customerDetails);
//			return ResponseEntity.ok("Record Updated");
//		} catch (Exception e) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}
//	
//	@GetMapping("/getCustomerDetails/{id}")
//	public ResponseEntity<List> getDetails(@PathVariable String id){
//		if (id != null) {
//			List<?> customerByID = customerService.getCustomerDetailsByID(id);
//			return ResponseEntity.ok(customerByID);
//		} else {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
//		
//	}

}
