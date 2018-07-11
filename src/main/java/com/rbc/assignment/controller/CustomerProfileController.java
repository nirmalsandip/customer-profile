package com.rbc.assignment.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
import com.rbc.assignment.dao.entity.Customer;
import com.rbc.assignment.exceptions.CustomerProfileException;
import com.rbc.assignment.response.CustomerProfileResponse;
import com.rbc.assignment.service.CustomerProfileService;

/**
 * 
 * @author Sandip Nirmal
 *
 */
@RestController
public class CustomerProfileController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerProfileController.class);

	/**
	 * Customer Profile Service object
	 */
	@Autowired
	private CustomerProfileService customerProfileService;

	/**
	 * Rest call to add customer to the repository
	 * 
	 * @param customer
	 * @return
	 * @throws CustomerProfileException
	 */
	@Timed
    @ExceptionMetered
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<CustomerProfileResponse> addCustomer(
			@RequestBody @Valid Customer customer) throws CustomerProfileException {
		long startTime = System.currentTimeMillis();
		logger.info("In CustomerProfileController.addCustomer() with parameters : {} : Start",
				customer);
		CustomerProfileResponse addedCustomer = customerProfileService.addCustomer(customer);
		long endTime = System.currentTimeMillis();
		logger.info("PERFORMANCE: customer-profile/add - Total execution time = {} ms.",
				(endTime - startTime));
		logger.info("CustomerProfileController.addCustomer  : End ");
		return new ResponseEntity<CustomerProfileResponse>(addedCustomer, HttpStatus.OK);
	}

	/**
	 * Rest call to update the existing customer
	 * 
	 * @param customer
	 * @return
	 * @throws CustomerProfileException
	 */
	@Timed
    @ExceptionMetered
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<CustomerProfileResponse> updateCustomer(
			@RequestBody @Valid Customer customer) throws CustomerProfileException {
		long startTime = System.currentTimeMillis();
		logger.info("In CustomerProfileController.updateCustomer() with parameters : {} : Start",
				customer);
		CustomerProfileResponse updatedCustomer = customerProfileService.updateCustomer(customer);
		long endTime = System.currentTimeMillis();
		logger.info("PERFORMANCE: customer-profile/update - Total execution time = {} ms.",
				(endTime - startTime));
		logger.info("CustomerProfileController.updateCustomer : End ");
		return new ResponseEntity<CustomerProfileResponse>(updatedCustomer, HttpStatus.OK);
	}

	/**
	 * Rest call to delete existing customer
	 * 
	 * @param mobile
	 * @return
	 * @throws CustomerProfileException
	 */
	@Timed
    @ExceptionMetered
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<CustomerProfileResponse> deleteCustomer(@RequestBody String mobile)
			throws CustomerProfileException {
		long startTime = System.currentTimeMillis();
		logger.info("In CustomerProfileController.deleteCustomer() with parameters : {} : Start",
				mobile);
		CustomerProfileResponse deletedCustomer = customerProfileService.deleteCustomer(mobile);
		long endTime = System.currentTimeMillis();
		logger.info("PERFORMANCE: customer-profile/delete - Total execution time = {} ms.",
				(endTime - startTime));
		logger.info("CustomerProfileController.deleteCustomer : End ");
		return new ResponseEntity<CustomerProfileResponse>(deletedCustomer, HttpStatus.OK);
	}

	/**
	 * Rest call to get all the customers
	 * 
	 * @return
	 * @throws CustomerProfileException
	 */
	@Timed
    @ExceptionMetered
	@RequestMapping(value = "/get_all", method = RequestMethod.GET)
	public List<Customer> getAllCustomers() throws CustomerProfileException {
		logger.info("In CustomerProfileController.getAllCustomers()");
		return customerProfileService.getAllCustomers();
	}
}
