package com.itemManagement.pos.service_impl.order;

import java.time.LocalDateTime;
import java.util.*;

import com.itemManagement.pos.common.CustomException;
import com.itemManagement.pos.dto.order.OrderDTO;
import com.itemManagement.pos.entity.item.Item;
import com.itemManagement.pos.entity.order.OrderMaster;
import com.itemManagement.pos.repository.item.ItemRepository;
import com.itemManagement.pos.repository.order.OrderRepository;
import com.itemManagement.pos.service.order.OrderService;
import org.hibernate.metamodel.mapping.ordering.ast.OrderingExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<OrderMaster> getAllOrders() {
        return orderRepository.getOrdersNotDeleted();
    }

    @Override
    public OrderMaster createOrder(OrderDTO orderDTO) throws Exception {

        OrderMaster order = new OrderMaster();

        List<Long> items = orderDTO.getItems();

        if(items.isEmpty()){
            throw new CustomException(207, "Should be selected at least one item to proceed an order");
        }

        Set<Item> itemSet = new HashSet<>();

        order.setTotal(0.0);

        for (Long productId : items) {
            Item product = itemRepository.findById(productId).orElse(null);

            if (product != null && product.getQty() != 0) {
                itemSet.add(product);
                order.setTotal(order.getTotal() + product.getPrice());

                //Reduce the QTY of current stock
            }
        }

        Double tax = (order.getTotal() / 100) * 15; //Adding 15% Tax

        order.setTax(tax);
        order.setOrderTime(LocalDateTime.now());
        order.setItems(itemSet);
        order.setStatus("Active");

        return orderRepository.save(order);
    }

    @Override
    public ResponseEntity<Object> updateOrder(OrderMaster order) throws Exception {
        if (order.getId() == null) {
            throw new Exception("Order can not be found");
        } else {
            if ("Accepted".equals(order.getStatus())) {
                throw new Exception("Accepted Order cannot be updated");
            } else {
                Optional<OrderMaster> optionalOrder = orderRepository.findById(order.getId());
                if (optionalOrder.isEmpty()) {
                    throw new Exception("Order can not be found");
                } else {
                    OrderMaster orderMst = optionalOrder.get();
                    Set<Item> items = orderMst.getItems();

                    for (Item item : items) {
                        Integer updatedQuantity = order.getItems()
                                .stream()
                                .filter(updatedItem -> updatedItem.getId().equals(item.getId()))
                                .map(Item::getQty)
                                .findFirst()
                                .orElse(0);

                        item.setQty(updatedQuantity);
                        if(item.getQty() == 0){
                            item.setStatus("D");
                        }
                    }

                    // Save the updated items
                    itemRepository.saveAll(items);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteOrder(Long id) {
        if (id == null) {
            return ResponseEntity.status(207).body("Order ID Can not be found");
        } else {
            Optional<OrderMaster> existOrder = orderRepository.findById(id);
            if(existOrder.isPresent()){
               OrderMaster order =  existOrder.get();
               if(Objects.equals(order.getStatus(), "Accepted")){
                   return ResponseEntity.status(207).body("Accepted Order Can not be deleted");
               } else {
                   order.setStatus("Deleted");
                   orderRepository.save(order);
               }

            } else {
                return ResponseEntity.status(207).body("Requested Order Can not be found");
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public OrderMaster getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }
}
