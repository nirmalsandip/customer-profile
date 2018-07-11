package com.rbc.assignment.validator;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rbc.assignment.dao.CustomerRepository;
import com.rbc.assignment.dao.entity.Customer;
import com.rbc.assignment.exceptions.CustomerProfileException;

@Component
public class CustomerProfileValidator {

	private static final Logger logger = LoggerFactory.getLogger(CustomerProfileValidator.class);

	/**
	 * Customer Repository to fetch values from database
	 */
	@Autowired
	private CustomerRepository customerRepository;

	/**
	 * Method to validate if customer with provided email and mobile already exists
	 * @param customer
	 * @return
	 * @throws CustomerProfileException
	 */
	public Customer validateIsCustomerExistsByMobileAndEmail(Customer customer) throws CustomerProfileException {
		logger.info("In validateIsCustomerExistsByMobileAndEmail");
		return customerRepository.findCustomerByMobileAndEmail(customer.getMobile(),
				customer.getEmail());
	}
	
	/**
	 * Method to check customer with provided mobile number exists.
	 * @param mobile
	 * @return
	 * @throws CustomerProfileException
	 */
	public Customer validateIsCustomerExistsByMobile(String mobile) throws CustomerProfileException {
		logger.info("In validateIsCustomerExistsByMobile");
		Optional<Customer> foundCustomer = customerRepository.findById(mobile);
		if(foundCustomer.isPresent()) {
			return foundCustomer.get();
		}
		return null;
	}
}
