package com.itemManagement.pos.controller.order;

import java.util.List;

import com.itemManagement.pos.dto.order.OrderDTO;
import com.itemManagement.pos.entity.order.OrderMaster;
import com.itemManagement.pos.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
public class OrderController {
    
    @Autowired
    private OrderService orderService;

    @GetMapping("/get_all_orders")
    public ResponseEntity<List<OrderMaster>> getAllOrders() {
        return ResponseEntity.status(200).body(orderService.getAllOrders());
    }

    @GetMapping("/get_order_by_id/{id}")
    public ResponseEntity<OrderMaster> getOrderById(@PathVariable Long id) {
        return ResponseEntity.status(200).body(orderService.getOrderById(id));
    }

    @PostMapping("/complete_order")
    public ResponseEntity<OrderMaster> createOrder(@RequestBody OrderDTO orderDTO) throws Exception {
        OrderMaster newOrder = orderService.createOrder(orderDTO);

        return ResponseEntity.status(201).body(newOrder);
    }

    @PostMapping("/update_order")
    public ResponseEntity<?> updateOrder(@RequestBody OrderMaster order) throws Exception{
        return orderService.updateOrder(order);
    }

    @DeleteMapping("/delete_order")
    public ResponseEntity<?> deleteOrder(@RequestParam Long id){
        return orderService.deleteOrder(id);
    }
}
