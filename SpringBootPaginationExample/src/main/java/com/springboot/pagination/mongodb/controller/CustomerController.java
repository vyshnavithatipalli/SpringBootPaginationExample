package com.springboot.pagination.mongodb.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.pagination.mongodb.model.Customer;
import com.springboot.pagination.mongodb.model.Response;
import com.springboot.pagination.mongodb.repository.CustomerRepository;

@RequestMapping("/api/customers")
@CrossOrigin(origins="https://localhost:4200")
@RestController
public class CustomerController {
@Autowired
	CustomerRepository customerRepository;
	@GetMapping("/pageable")
	public Page<Customer> getCustomerWithPaging(@Param(value="page") int page,@Param(value="size") int size){
		Pageable requestedPage=PageRequest.of(page, size);
		Page<Customer> customers=customerRepository.findAll(requestedPage);
		return customers;
	}
	
	@GetMapping("/pageable/list")
	public List<Customer> getCustomerListWithPaging(@Param(value="page") int page,@Param(value="size") int size){
		Pageable requestedPage=PageRequest.of(page, size);
		Page<Customer> customers=customerRepository.findAll(requestedPage);
		return customers.toList();
	}
	
	@GetMapping("/customerdetailsfilteredbysalary")
	public Slice<Customer> getCustomerDetailsFilteredBySalary(@Param(value="salary") double salary,@Param(value="page") int page,@Param(value="size") int size){
		Pageable requestedPage=PageRequest.of(page, size);
		Slice<Customer> customers=customerRepository.findAllBySalary(salary,requestedPage);
		return customers;
	}
	
	@GetMapping("/customerdetailsfilteredbyagegreaterthan")
	public Slice<Customer> getCustomerDetailsFilteredByAge(@Param(value="age") int age,@Param(value="page") int page,@Param(value="size") int size){
		Pageable requestedPage=PageRequest.of(page, size);
		Slice<Customer> customers=customerRepository.findAlByAgeGreaterThan(age,requestedPage);
		return customers;
	}
	
	@GetMapping("/pagingandsorting")
	public Page<Customer> pagingAndSortingCustomers(@Param(value="page") int page,@Param(value="size") int size){
		Pageable requestedPage=PageRequest.of(page, size,Sort.by("salary").descending().and(Sort.by("age")).and(Sort.by("firstName")));
		Page<Customer> customers=customerRepository.findAll(requestedPage);
		return customers;
	}
	
	@GetMapping("/pagingfilteringandsorting")
	public Page<Customer> pagingFilteringAndSortingByAge(@Param(value="age") int age,@Param(value="salary") double salary,@Param(value="page") int page,@Param(value="size") int size){
		Pageable requestedPage=PageRequest.of(page, size,Sort.by("salary").descending().and(Sort.by("age")).and(Sort.by("firstName")));
		Page<Customer> customers=customerRepository.findAlByAgeGreaterThan(age,requestedPage);
		return customers;
	}
	
	@GetMapping("/custom/pageable")
	public Response getCustomer(@Param(value="page") int page,@Param(value="size") int size) {
		Pageable requestedPage=PageRequest.of(page, size);
		Page<Customer> customers=customerRepository.findAll(requestedPage);
		
		Response res=new Response(customers.getContent(),customers.getTotalPages(),customers.getNumber(),customers.getSize());
		return res;
		
	}
}
