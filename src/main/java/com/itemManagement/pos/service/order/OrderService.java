package com.itemManagement.pos.service.order;


import com.itemManagement.pos.dto.order.OrderDTO;
import com.itemManagement.pos.entity.order.Order;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface OrderService {
    List<Order> getAllOrders();
    Order getOrderById(Long id);
    Order createOrder(OrderDTO orderDTO);
}
