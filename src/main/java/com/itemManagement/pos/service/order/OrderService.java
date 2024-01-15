package com.itemManagement.pos.service.order;


import com.itemManagement.pos.dto.order.OrderDTO;
import com.itemManagement.pos.entity.order.OrderMaster;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface OrderService {
    List<OrderMaster> getAllOrders();
    OrderMaster getOrderById(Long id);
    OrderMaster createOrder(OrderDTO orderDTO) throws Exception;

    ResponseEntity<?> updateOrder(OrderMaster order) throws Exception;


    ResponseEntity<?> deleteOrder(Long id);
}
