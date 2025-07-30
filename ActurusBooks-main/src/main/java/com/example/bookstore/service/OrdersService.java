package com.example.bookstore.service;

import com.example.bookstore.entity.Orders;
import com.example.bookstore.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersService {
    @Autowired
    private OrdersRepository ordersRepository;

    public List<Orders> getAll() {
        return ordersRepository.findAll();
    }

    public Orders save(Orders order) {
        return ordersRepository.save(order);
    }

    public Optional<Orders> getById(Integer id) {
        return ordersRepository.findById(id);
    }

    public void delete(Integer id) {
        ordersRepository.deleteById(id);
    }

    // public List<Orders> getByStatus(String status) {
    // return ordersRepository.findByStatus(status);
    // }

    // public List<Object[]> getMonthlyRevenue() {
    // return ordersRepository.findMonthlyRevenue();
    // }

    // public ByteArrayInputStream exportToExcel() { ... }

    // public ByteArrayInputStream exportToPdf() { ... }
}
