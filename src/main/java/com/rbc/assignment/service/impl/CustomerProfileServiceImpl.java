package com.rbc.assignment.service.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rbc.assignment.dao.CustomerRepository;
import com.rbc.assignment.dao.entity.Customer;
import com.rbc.assignment.exceptions.CustomerProfileException;
import com.rbc.assignment.response.CustomerProfileResponse;
import com.rbc.assignment.service.CustomerProfileService;
import com.rbc.assignment.validator.CustomerProfileValidator;

@Service
public class CustomerProfileServiceImpl implements CustomerProfileService {

	/**
	 * Logger to log the messages
	 */
	private static final Logger logger = LoggerFactory.getLogger(CustomerProfileServiceImpl.class);

	/**
	 * Repository object to persisting it in database
	 */
	@Autowired
	private CustomerRepository customerRepository;

	/**
	 * Customer validator to validate the customer business rules
	 */
	@Autowired
	private CustomerProfileValidator validator;

	@Value("${messages.failure.message}")
	private String failureMessage;

	@Value("${messages.failure.status}")
	private String failureStatus;

	@Value("${messages.success.message}")
	private String successMessage;

	@Value("${messages.success.status}")
	private String successStatus;

	@Override
	public CustomerProfileResponse addCustomer(Customer customer) throws CustomerProfileException {
		// Validate if customer with this mobile and email already exists
		logger.info("Inside CustomerProfileServiceImpl.addCustomer() ");
		Customer foundCustomer = validator.validateIsCustomerExistsByMobileAndEmail(customer);
		if (foundCustomer != null) {
			logger.error("Duplicate record! Customer with this mobile and email already exists.");
			throw new CustomerProfileException(
					"Duplicate record! Customer with this mobile and email already exists.");
		}
		logger.debug("Calling customerRepository save()");
		Customer result = customerRepository.save(customer);
		CustomerProfileResponse response = null;
		if (result == null) {
			logger.debug("Problem while adding customer ");
			response = new CustomerProfileResponse(MessageFormat.format(failureMessage, "add"),
					failureStatus);
		} else {
			logger.info("Customer successfuly added");
			response = new CustomerProfileResponse(MessageFormat.format(successMessage, "add"),
					successStatus);
		}
		return response;
	}

	@Override
	public CustomerProfileResponse updateCustomer(Customer customer)
			throws CustomerProfileException {
		// Validate that the customer which we are updating exists
		logger.info("Inside CustomerProfileServiceImpl.updateCustomer() ");
		Customer foundCustomer = validator.validateIsCustomerExistsByMobileAndEmail(customer);
		if (foundCustomer == null) {
			logger.error("Customer with this mobile and email does not exists.");
			throw new CustomerProfileException(
					"Customer with this mobile and email does not exists.");
		}
		logger.debug("Calling customerRepository save()");
		Customer result = customerRepository.save(customer);
		CustomerProfileResponse response = null;
		if (result == null) {
			logger.debug("Problem while updating customer ");
			response = new CustomerProfileResponse(MessageFormat.format(failureMessage, "update"),
					failureStatus);
		} else {
			logger.info("Customer successfuly updated");
			response = new CustomerProfileResponse(MessageFormat.format(successMessage, "update"),
					successStatus);
		}
		return response;
	}

	@Override
	public CustomerProfileResponse deleteCustomer(String mobile) throws CustomerProfileException {
		// Validate that the customer which we are deleting exists
		logger.info("Inside CustomerProfileServiceImpl.deleteCustomer() ");
		Customer foundCustomer = validator.validateIsCustomerExistsByMobile(mobile);
		if (foundCustomer == null) {
			logger.error("Customer with this mobile does not exists.");
			throw new CustomerProfileException("Customer with this mobile does not exists.");
		}
		logger.debug("Calling customerRepository delete()");
		customerRepository.deleteById(mobile);
		logger.info("Customer successfuly deleted");
		return new CustomerProfileResponse(MessageFormat.format(successMessage, "delete"),
				successStatus);
	}

	@Override
	public Customer findCustomerByMobileAndEmail(String mobile, String email)
			throws CustomerProfileException {
		return customerRepository.findCustomerByMobileAndEmail(mobile, email);
	}

	@Override
	public Customer findCustomerByMobile(String mobile) throws CustomerProfileException {
		Optional<Customer> customer = customerRepository.findById(mobile);
		if (customer.isPresent()) {
			return customer.get();
		}
		return null;
	}

	@Override
	public List<Customer> getAllCustomers() throws CustomerProfileException {
		logger.info(
				"Inside CustomerProfileServiceImpl.getAllCustomers(). \nCalling customerRepository findAll");
		Iterable<Customer> allCustomers = customerRepository.findAll();
		List<Customer> customerList = new ArrayList<>();

		// Converting from Iterable to List
		allCustomers.forEach(customerList::add);
		logger.debug("found {} customers.", customerList.size());
		return customerList;
	}
}
