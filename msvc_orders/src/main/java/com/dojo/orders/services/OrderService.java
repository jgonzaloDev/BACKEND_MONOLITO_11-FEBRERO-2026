package com.dojo.orders.services;

import com.dojo.orders.entities.Order;
import com.dojo.orders.entities.OrderDetail;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<Order> listAllOrders();
    List<Order> getOrdersByCustomer(Long id);
    List<Order> getOrdersByUsername(String username);
    Optional<Order> getOrderById(Long id);
    Order save(Long customerId, List<OrderDetail> details);
    Order updateStatus(Long orderId, String status);
    void delete(Long id);

}
