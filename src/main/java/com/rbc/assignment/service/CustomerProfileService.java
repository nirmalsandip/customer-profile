package com.rbc.assignment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rbc.assignment.dao.entity.Customer;
import com.rbc.assignment.exceptions.CustomerProfileException;
import com.rbc.assignment.response.CustomerProfileResponse;

/**
 * THis is a service class which will communicate to DAO.
 * 
 * @author Sandip.Nirmal
 *
 */
@Service
public interface CustomerProfileService {

	/**
	 * This method is used to add a new customer to the database.
	 * 
	 * @param customer
	 * @return CustomerProfileResponse : message detailing customer successful created or problem while
	 *         creating customer.
	 * @throws CustomerProfileException
	 */
	public CustomerProfileResponse addCustomer(Customer customer) throws CustomerProfileException;

	/**
	 * This method is used to update an existing customer to the database.
	 * 
	 * @param customer
	 * @return CustomerProfileResponse : message detailing customer successful updated or problem while
	 *         updating customer.
	 * @throws CustomerProfileException
	 */
	public CustomerProfileResponse updateCustomer(Customer customer) throws CustomerProfileException;

	/**
	 * This method is used to delete an existing customer from the database.
	 * 
	 * @param mobile
	 * @return CustomerProfileResponse : message detailing customer successful deleted or problem while
	 *         deleting customer.
	 * @throws CustomerProfileException
	 */
	public CustomerProfileResponse deleteCustomer(String mobile) throws CustomerProfileException;

	/**
	 * This method is used to search customer from the mobile and email id
	 * 
	 * @param mobile
	 * @param email
	 * @return if found, returns {@link Customer} object else null
	 * @throws CustomerProfileException
	 */
	public Customer findCustomerByMobileAndEmail(String mobile, String email)
			throws CustomerProfileException;

	/**
	 * This method is used to search customer from the mobile
	 * 
	 * @param mobile
	 * @return if found, returns {@link Customer} object else null
	 * @throws CustomerProfileException
	 */
	public Customer findCustomerByMobile(String mobile) throws CustomerProfileException;

	/**
	 * Gets all the customers which have been there in the database
	 * 
	 * @return list of {@link Customer} object
	 * @throws CustomerProfileException
	 */
	public List<Customer> getAllCustomers() throws CustomerProfileException;

}
