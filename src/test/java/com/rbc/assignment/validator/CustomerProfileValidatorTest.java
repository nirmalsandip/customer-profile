package com.rbc.assignment.validator;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.rbc.assignment.dao.CustomerRepository;
import com.rbc.assignment.dao.entity.Customer;
import com.rbc.assignment.exceptions.CustomerProfileException;

@RunWith(MockitoJUnitRunner.class)
public class CustomerProfileValidatorTest {
	
	@InjectMocks
	private CustomerProfileValidator validator;
	
	@Mock
	private CustomerRepository customerRepository;
	
	@Test
	public void testValidateIsCustomerExistsByMobileAndEmail() throws CustomerProfileException {
		Customer customer = new Customer();
		customer.setEmail("san.nirm@syn.com");
		customer.setFirstName("sand");
		customer.setLastName("nirm");
		customer.setMobile("9049363322");
		
		Mockito.when(customerRepository.findCustomerByMobileAndEmail(customer.getMobile(),customer.getEmail())).thenReturn(customer);
		
		Customer customerResponse = validator.validateIsCustomerExistsByMobileAndEmail(customer);
		Assert.assertEquals(customer, customerResponse);
	}
	
	@Test
	public void testValidateIsCustomerExistsByMobile() throws CustomerProfileException {
		Customer customer = new Customer();
		customer.setEmail("san.nirm@syn.com");
		customer.setFirstName("sand");
		customer.setLastName("nirm");
		customer.setMobile("9049363322");
		
		Optional<Customer> optionCustomer = Optional.of(customer);		
		Mockito.when(customerRepository.findById(customer.getMobile())).thenReturn(optionCustomer);
		
		Customer customerResponse = validator.validateIsCustomerExistsByMobile(customer.getMobile());
		Assert.assertEquals(customer, customerResponse);
	}
	
	@Test
	public void testValidateIsCustomerExistsByMobileNegative() throws CustomerProfileException {
				
		Optional<Customer> optionCustomer = Optional.empty();		
		Mockito.when(customerRepository.findById("9049363322")).thenReturn(optionCustomer);
		
		Customer customerResponse = validator.validateIsCustomerExistsByMobile("9049363322");
		Assert.assertEquals(null, customerResponse);
	}
	
}
