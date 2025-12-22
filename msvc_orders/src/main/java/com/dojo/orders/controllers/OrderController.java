package com.dojo.orders.controllers;

import com.dojo.orders.entities.Order;
import com.dojo.orders.entities.OrderDetail;
import com.dojo.orders.services.OrderServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("order")
public class OrderController {
    private OrderServiceImpl orderService;

    public OrderController(OrderServiceImpl orderService){
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<?> listAll() {
        return ResponseEntity.ok(orderService.listAllOrders());
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<?> listOrdersByUsername(@PathVariable String username) {
        return ResponseEntity.ok(orderService.getOrdersByUsername(username));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<?> listOrdersByCustomer( @PathVariable Long id){
        List<Order> ordersByCustomer = orderService.getOrdersByCustomer(id);
        return ResponseEntity.ok(ordersByCustomer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(@PathVariable Long id){
        Optional<Order> optional =orderService.getOrderById(id);
        if(optional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("Mensaje","Orden no econtrado!"));
        }
        return ResponseEntity.ok(optional.get());
    }

    @PostMapping("/{customerId}")
    public ResponseEntity<Order> createOrder(@PathVariable Long customerId, @RequestBody List<OrderDetail> details) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.save(customerId, details));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id ) {
        Optional<Order> optionalOrder = orderService.getOrderById(id);
        if(optionalOrder.isPresent()){
            orderService.delete(id);
            return ResponseEntity.ok("Orden eliminado con éxito!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("Mensaje","Orden no econtrado!"));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable Long id,@RequestBody Order order){
        Order orderUpdated = orderService.updateStatus(id, order.getStatusOrder());
        return ResponseEntity.ok(orderUpdated);
    }

}
