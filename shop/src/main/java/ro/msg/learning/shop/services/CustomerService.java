package ro.msg.learning.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entities.Customer;
import ro.msg.learning.shop.repositories.CustomerRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public void createCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void deleteAllCustomers() {
        customerRepository.deleteAll();
    }

    public Customer getCustomerById(Integer id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return customer.get();
        } else {
            throw new EntityNotFoundException("Customer" + id + "not found!");
        }
    }
}
