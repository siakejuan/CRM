package com.hibernate.dao;

import java.io.PrintWriter;
import java.util.List;

import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.Transactional;

import com.hibernate.entity.Customer;
import com.hibernate.utilities.SortUtils;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// inject bean from xml
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomers(int theSortField) {

		PrintWriter out = new PrintWriter(System.out);

		// get current hibernate session
		out.println("get hibernate session in Customer DAO...");
		Session currentSession = sessionFactory.getCurrentSession();

		String theFieldName = null;

		switch (theSortField) {
		case SortUtils.FIRST_NAME:
			theFieldName = "firstName";
			break;
		case SortUtils.LAST_NAME:
			theFieldName = "lastName";
			break;
		case SortUtils.EMAIL:
			theFieldName = "email";
			break;
		default:
			// if nothing matches
			theFieldName = "lastName";
		}
		// create query
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by " + theFieldName, Customer.class);

		// get result list
		List<Customer> customers = theQuery.getResultList();

		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {

		// get current session
		Session currentSession = sessionFactory.getCurrentSession();

		// save customer
		currentSession.saveOrUpdate(theCustomer);
	}

	@Override
	public Customer getCustomer(int customerId) {
		// get current session
		Session currentSession = sessionFactory.getCurrentSession();

		// save customer
		return currentSession.get(Customer.class, customerId);
	}

	@Override
	public void deleteCustomer(int id) {
		Session currentSession = sessionFactory.getCurrentSession();

		Query query = currentSession.createQuery("delete from Customer where id=:id");
		query.setParameter("id", id);

		query.executeUpdate();
	}

	@Override
	public List<Customer> searchCustomers(String theSearchName) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		Query theQuery = null;

		//
		// only search by name if theSearchName is not empty
		//
		if (theSearchName != null && theSearchName.trim().length() > 0) {
			// search for firstName or lastName ... case insensitive
			theQuery = currentSession.createQuery(
					"from Customer where lower(firstName) like :theName or lower(lastName) like :theName",
					Customer.class);
			theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
		} else {
			// theSearchName is empty ... so just get all customers
			theQuery = currentSession.createQuery("from Customer", Customer.class);
		}

		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();

		// return the results
		return customers;

	}

}
