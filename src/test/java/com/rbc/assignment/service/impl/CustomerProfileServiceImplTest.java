package com.rbc.assignment.service.impl;

import static org.mockito.Mockito.doNothing;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.rbc.assignment.dao.CustomerRepository;
import com.rbc.assignment.dao.entity.Customer;
import com.rbc.assignment.exceptions.CustomerProfileException;
import com.rbc.assignment.response.CustomerProfileResponse;
import com.rbc.assignment.validator.CustomerProfileValidator;

@RunWith(MockitoJUnitRunner.class)
public class CustomerProfileServiceImplTest {

	@InjectMocks
	private CustomerProfileServiceImpl customerProfileService;
	
	@Mock
	private CustomerProfileValidator validator;
	
	@Mock
	private CustomerRepository customerRepository;
	
	@Test
	public void testAddCustomer() throws CustomerProfileException {
		Customer customer = new Customer();
		customer.setEmail("san.nirm@syn.com");
		customer.setFirstName("sand");
		customer.setLastName("nirm");
		customer.setMobile("9049363322");
		
		Mockito.when(customerRepository.save(customer)).thenReturn(customer);
		
		ReflectionTestUtils.setField(customerProfileService, "successMessage", "message");
		ReflectionTestUtils.setField(customerProfileService, "successStatus", "SUCCESS");
		
		CustomerProfileResponse addCustomerResponse = customerProfileService.addCustomer(customer);
		Assert.assertEquals("SUCCESS", addCustomerResponse.getStatus());
		
	}
	
	@Test(expected=CustomerProfileException.class)
	public void testAddCustomerNegative() throws CustomerProfileException {
		Customer customer = new Customer();
		customer.setEmail("san.nirm@syn.com");
		customer.setFirstName("sand");
		customer.setLastName("nirm");
		customer.setMobile("9049363322");
		
		Mockito.when(validator.validateIsCustomerExistsByMobileAndEmail(customer)).thenReturn(customer);
				
		CustomerProfileResponse addCustomerResponse = customerProfileService.addCustomer(customer);
		Assert.assertEquals("FAILURE", addCustomerResponse.getStatus());
		
	}
	
	@Test
	public void testAddCustomerNegative1() throws CustomerProfileException {
		Customer customer = new Customer();
		customer.setEmail("san.nirm@syn.com");
		customer.setFirstName("sand");
		customer.setLastName("nirm");
		customer.setMobile("9049363322");
		
		Mockito.when(validator.validateIsCustomerExistsByMobileAndEmail(customer)).thenReturn(null);
		Mockito.when(customerRepository.save(customer)).thenReturn(null);
		
		ReflectionTestUtils.setField(customerProfileService, "failureMessage", "message");
		ReflectionTestUtils.setField(customerProfileService, "failureStatus", "FAILURE");
		
		CustomerProfileResponse addCustomerResponse = customerProfileService.addCustomer(customer);
		Assert.assertEquals("FAILURE", addCustomerResponse.getStatus());
		
	}
	
	@Test
	public void testUpdateCustomer() throws CustomerProfileException {
		Customer customer = new Customer();
		customer.setEmail("san.nirm@syn.com");
		customer.setFirstName("sand");
		customer.setLastName("nirm");
		customer.setMobile("9049363322");
		Mockito.when(validator.validateIsCustomerExistsByMobileAndEmail(customer)).thenReturn(customer);
		Mockito.when(customerRepository.save(customer)).thenReturn(customer);
		
		ReflectionTestUtils.setField(customerProfileService, "successMessage", "message");
		ReflectionTestUtils.setField(customerProfileService, "successStatus", "SUCCESS");
		
		CustomerProfileResponse addCustomerResponse = customerProfileService.updateCustomer(customer);
		Assert.assertEquals("SUCCESS", addCustomerResponse.getStatus());
		
	}
	
	@Test(expected=CustomerProfileException.class)
	public void testUpdateCustomerNegative() throws CustomerProfileException {
		Customer customer = new Customer();
		customer.setEmail("san.nirm@syn.com");
		customer.setFirstName("sand");
		customer.setLastName("nirm");
		customer.setMobile("9049363322");
		Mockito.when(validator.validateIsCustomerExistsByMobileAndEmail(customer)).thenReturn(null);
				
		CustomerProfileResponse addCustomerResponse = customerProfileService.updateCustomer(customer);
		Assert.assertEquals("SUCCESS", addCustomerResponse.getStatus());
		
	}
	
	@Test
	public void testUpdateCustomerNegative1() throws CustomerProfileException {
		Customer customer = new Customer();
		customer.setEmail("san.nirm@syn.com");
		customer.setFirstName("sand");
		customer.setLastName("nirm");
		customer.setMobile("9049363322");
		Mockito.when(validator.validateIsCustomerExistsByMobileAndEmail(customer)).thenReturn(customer);
		Mockito.when(customerRepository.save(customer)).thenReturn(null);
		
		ReflectionTestUtils.setField(customerProfileService, "failureMessage", "message");
		ReflectionTestUtils.setField(customerProfileService, "failureStatus", "FAILURE");
		
		CustomerProfileResponse addCustomerResponse = customerProfileService.updateCustomer(customer);
		Assert.assertEquals("FAILURE", addCustomerResponse.getStatus());
		
	}
	
	@Test
	public void testDeleteCustomer() throws CustomerProfileException {
		Customer customer = new Customer();
		customer.setEmail("san.nirm@syn.com");
		customer.setFirstName("sand");
		customer.setLastName("nirm");
		customer.setMobile("9049363322");		
		Mockito.when(validator.validateIsCustomerExistsByMobile(customer.getMobile())).thenReturn(customer);		
		doNothing().when(customerRepository).deleteById(customer.getMobile());
		
		ReflectionTestUtils.setField(customerProfileService, "successMessage", "message");
		ReflectionTestUtils.setField(customerProfileService, "successStatus", "SUCCESS");
		
		CustomerProfileResponse addCustomerResponse = customerProfileService.deleteCustomer(customer.getMobile());
		Assert.assertEquals("SUCCESS", addCustomerResponse.getStatus());		
	}
	
	@Test(expected=CustomerProfileException.class)
	public void testDeleteCustomerNegative() throws CustomerProfileException {
		Customer customer = new Customer();
		customer.setEmail("san.nirm@syn.com");
		customer.setFirstName("sand");
		customer.setLastName("nirm");
		customer.setMobile("9049363322");		
		Mockito.when(validator.validateIsCustomerExistsByMobile(customer.getMobile())).thenReturn(null);		
				
		CustomerProfileResponse addCustomerResponse = customerProfileService.deleteCustomer(customer.getMobile());
		Assert.assertEquals("SUCCESS", addCustomerResponse.getStatus());		
	}
	
	@Test
	public void testFindCustomerByMobileAndEmail() throws CustomerProfileException {
		Customer customer = new Customer();
		customer.setEmail("san.nirm@syn.com");
		customer.setFirstName("sand");
		customer.setLastName("nirm");
		customer.setMobile("9049363322");		
		
		Mockito.when(customerRepository.findCustomerByMobileAndEmail(customer.getMobile(),
				customer.getEmail())).thenReturn(customer);
		
		Customer resultCustomer = customerProfileService.findCustomerByMobileAndEmail(customer.getMobile(),
				customer.getEmail());
		Assert.assertEquals(customer, resultCustomer);		
	}
	
	@Test
	public void testFindCustomerByMobile() throws CustomerProfileException {
		Customer customer = new Customer();
		customer.setEmail("san.nirm@syn.com");
		customer.setFirstName("sand");
		customer.setLastName("nirm");
		customer.setMobile("9049363322");		
		
		Optional<Customer> optionCustomer = Optional.of(customer);
		
		Mockito.when(customerRepository.findById(customer.getMobile())).thenReturn(optionCustomer);
		
		Customer resultCustomer = customerProfileService.findCustomerByMobile(customer.getMobile());
		Assert.assertEquals(customer, resultCustomer);		
	}
	
	@Test
	public void testFindCustomerByMobileNegative() throws CustomerProfileException {
		Customer customer = new Customer();
		customer.setEmail("san.nirm@syn.com");
		customer.setFirstName("sand");
		customer.setLastName("nirm");
		customer.setMobile("9049363322");		
		
		Optional<Customer> optionCustomer = Optional.empty();
		
		Mockito.when(customerRepository.findById(customer.getMobile())).thenReturn(optionCustomer);
		
		Customer resultCustomer = customerProfileService.findCustomerByMobile(customer.getMobile());
		Assert.assertNull(resultCustomer);		
	}
	
	@Test
	public void testGetAllCustomers() throws CustomerProfileException {
		Customer customer = new Customer();
		customer.setEmail("san.nirm@syn.com");
		customer.setFirstName("sand");
		customer.setLastName("nirm");
		customer.setMobile("9049363322");		
		
		Iterable<Customer> customers = Arrays.asList(customer); 
		
		Mockito.when(customerRepository.findAll()).thenReturn(customers);
		
		List<Customer> resultCustomer = customerProfileService.getAllCustomers();
		Assert.assertEquals(1, resultCustomer.size());		
	}
}
