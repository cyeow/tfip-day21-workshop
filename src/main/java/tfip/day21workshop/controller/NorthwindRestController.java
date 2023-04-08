package tfip.day21workshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import tfip.day21workshop.model.Customer;
import tfip.day21workshop.model.Jsonable;
import tfip.day21workshop.model.Order;
import tfip.day21workshop.repository.NorthwindRepository;

@RestController
@RequestMapping(path = "/api")
public class NorthwindRestController {

    @Autowired
    NorthwindRepository repo;

    @GetMapping(path = "/customers", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> findAllCustomers(@RequestParam(defaultValue = "0") Integer offset,
            @RequestParam(defaultValue = "5") Integer limit) {

        List<Customer> customers = repo.findAllCustomers(offset, limit);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(listToJSON("customers", customers).toString());
    }

    @GetMapping(path = "/customer/{customerId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> findCustomerById(@PathVariable Integer customerId) {
        Customer c = repo.findCustomerById(customerId);

        if (c == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(generateMsgJsonString("Customer id " + customerId + " was not found."));
        }

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(c.toJSON().toString());
    }

    @GetMapping(path = "/customer/{customerId}/orders")
    public ResponseEntity<String> findOrdersByCustomerId(@PathVariable Integer customerId) {
        List<Order> orders = repo.findOrdersByCustomerId(customerId);

        if (orders == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(generateMsgJsonString("Customer id " + customerId + " was not found."));
        } else if (orders.size() == 0) {
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(generateMsgJsonString("No orders found for customer id " + customerId + "."));
        }

        String response = Json.createObjectBuilder()
                .add("customer_id", customerId)
                .add("orders", listToJSONArrayBuilder(orders))
                .build().toString();

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    private <T extends Jsonable> JsonObject listToJSON(String arrayLabel, List<T> list) {
        return Json.createObjectBuilder().add(arrayLabel, listToJSONArrayBuilder(list)).build();
    }

    private <T extends Jsonable> JsonArrayBuilder listToJSONArrayBuilder(List<T> list) {
        JsonArrayBuilder ab = Json.createArrayBuilder();
        list.forEach(l -> ab.add(l.toJSON()));

        return ab;
    }

    private String generateMsgJsonString(String message) {
        return Json.createObjectBuilder()
                .add("message", message)
                .build()
                .toString();
    }

}
