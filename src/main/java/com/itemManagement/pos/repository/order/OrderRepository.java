package com.itemManagement.pos.repository.order;

import com.itemManagement.pos.entity.order.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<OrderMaster, Long> {

    @Query("SELECT orderMst FROM OrderMaster orderMst WHERE orderMst.status <> 'Deleted'")
    List<OrderMaster> getOrdersNotDeleted();

}
