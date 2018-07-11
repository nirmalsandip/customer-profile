package com.rbc.assignment.dao;

import org.springframework.data.repository.CrudRepository;

import com.rbc.assignment.dao.entity.Customer;

/**
 * 
 * @author Sandip Nirmal
 *
 */
public interface CustomerRepository extends CrudRepository<Customer, String> {
	/**
	 * Method to find the customers with their mobile number and email id.
	 * @param mobile 
	 * @param email
	 * @return {@link Customer} object if found else null
	 */
	public Customer findCustomerByMobileAndEmail(String mobile, String email);
}
