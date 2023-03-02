package com.springboot.pagination.mongodb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.springboot.pagination.mongodb.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer,String>{

	Slice<Customer> findAllBySalary(double salary,Pageable pageoble );
	Page<Customer> findAlByAgeGreaterThan(int age,Pageable pageable);
}
