package tfip.day21workshop.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import tfip.day21workshop.model.Customer;
import tfip.day21workshop.model.Order;

import static tfip.day21workshop.repository.DBQueries.*;

@Repository
public class NorthwindRepository {
    
    @Autowired
    private JdbcTemplate jdbc;

    public List<Customer> findAllCustomers(Integer offset, Integer limit) {
        SqlRowSet rs = jdbc.queryForRowSet(SELECT_ALL_CUSTOMERS, offset, limit);

        List<Customer> result = new ArrayList<>();
        while(rs.next()) {
            result.add(Customer.create(rs));
        }

        return result;
    }

    public Customer findCustomerById(Integer customerId) {
        SqlRowSet rs = jdbc.queryForRowSet(SELECT_CUSTOMER_BY_ID, customerId);

        Customer result = null;
        while(rs.next()) {
            result = Customer.create(rs);
        }
        
        return result;
    }

    public List<Order> findOrdersByCustomerId(Integer customerId) {
        if(findCustomerById(customerId) == null) {
            return null;
        } 
        
        SqlRowSet rs = jdbc.queryForRowSet(SELECT_ORDERS_BY_CUSTOMER_ID, customerId);

        List<Order> result = new ArrayList<>();
        while(rs.next()) {
            result.add(Order.create(rs));
        }

        return result;
    }
    
}
