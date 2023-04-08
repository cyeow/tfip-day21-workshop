package tfip.day21workshop.repository;

public class DBQueries {
    
    public static final String SELECT_ALL_CUSTOMERS = "SELECT * FROM customers LIMIT ?, ?;";
    public static final String SELECT_CUSTOMER_BY_ID = "SELECT * FROM customers WHERE id = ?;";
    public static final String SELECT_ORDERS_BY_CUSTOMER_ID = "SELECT * FROM orders WHERE customer_id = ?;";
    
}
