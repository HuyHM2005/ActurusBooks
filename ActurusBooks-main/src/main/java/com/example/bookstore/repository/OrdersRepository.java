package com.example.bookstore.repository;

import com.example.bookstore.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    // List<Orders> findByStatus(String status); // ❌ Block comment: lọc theo trạng
    // thái
    // @Query("SELECT FUNCTION('MONTH', o.order_date) AS month, SUM(o.total_amount)
    // FROM Orders o GROUP BY FUNCTION('MONTH', o.order_date)")
    // List<Object[]> findMonthlyRevenue(); // ❌ Block comment: biểu đồ doanh thu
}
