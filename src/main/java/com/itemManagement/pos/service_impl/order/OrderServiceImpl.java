package com.itemManagement.pos.service_impl.order;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.itemManagement.pos.dto.order.OrderDTO;
import com.itemManagement.pos.entity.item.Item;
import com.itemManagement.pos.entity.order.Order;
import com.itemManagement.pos.repository.item.ItemRepository;
import com.itemManagement.pos.repository.order.OrderRepository;
import com.itemManagement.pos.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderServiceImpl implements OrderService {
    
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order createOrder(OrderDTO orderDTO) {

        Order order = new Order();

        List<Long> products = orderDTO.getItems();

        Set<Item> productsSet = new HashSet<>();

        order.setTotal(0.0);

        for (Long productId : products) {
            Item product = itemRepository.findById(productId).orElse(null);

            if(product != null && product.getQty() != 0) {
                productsSet.add(product);
                order.setTotal(order.getTotal() + product.getPrice());

                //Reduce the QTY of current stock
            }
        }

        Double tax = (order.getTotal()/100) * 15; //Adding 15% Tax

        order.setTax(tax);
        order.setOrderTime(LocalDateTime.now());
        order.setItems(productsSet);

        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }
}
