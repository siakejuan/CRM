package com.hibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hibernate.dao.CustomerDAO;
import com.hibernate.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	// inject DAO (data access object)
	@Autowired 
	private CustomerDAO customerDAO;
	
	@Override
	@Transactional
	public List<Customer> getCustomers(int theSortField) {
		List<Customer> theCustomers = customerDAO.getCustomers(theSortField);
		
		return theCustomers;
	
	}

	@Override
	@Transactional
	public void saveCustomer(Customer theCustomer) {
		customerDAO.saveCustomer(theCustomer);
		
	}

	@Override
	@Transactional
	public Customer getCustomer(int customerId) {
		return customerDAO.getCustomer(customerId);
	}

	@Override
	@Transactional
	public void deleteCustomer(int id) {
		customerDAO.deleteCustomer(id);
	}

	@Override
	@Transactional
	public List<Customer> searchCustomers(String theSearchName) {

		return customerDAO.searchCustomers(theSearchName);
	}


}
