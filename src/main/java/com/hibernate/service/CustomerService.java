package com.hibernate.service;

import java.util.List;

import com.hibernate.entity.Customer;

public interface CustomerService {
	public List<Customer> getCustomers(int theSortField);

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomer(int customerId);

	public void deleteCustomer(int id);

	public List<Customer> searchCustomers(String theSearchName);
	
}
