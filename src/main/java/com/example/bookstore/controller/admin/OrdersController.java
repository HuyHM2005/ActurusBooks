package com.example.bookstore.controller.admin;

import com.example.bookstore.entity.Orders;
import com.example.bookstore.service.OrdersService;
import com.example.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("orders", ordersService.getAll());
        model.addAttribute("order", new Orders()); // dùng cho form thêm/sửa
        return "admin/orders/orders"; // đúng theo path bạn đang dùng
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Orders order) {
        ordersService.save(order);
        return "redirect:/admin/orders";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Orders order = ordersService.getById(id).orElseThrow();
        model.addAttribute("order", order);
        model.addAttribute("orders", ordersService.getAll());
        return "admin/orders/orders"; // dùng chung view
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        ordersService.delete(id);
        return "redirect:/admin/orders";
    }

    /* --------- Chức năng phụ trợ (mở dần khi cần) ----------- */

    // @GetMapping("/filter")
    // public String filterByStatus(@RequestParam String status, Model model) {
    // model.addAttribute("orders", ordersService.getByStatus(status));
    // return "admin/orders/orders";
    // }

    @GetMapping("/details/{id}")
    public String viewDetails(@PathVariable Integer id, Model model) {
        Orders order = ordersService.getById(id).orElseThrow();
        model.addAttribute("order", order);
        return "admin/orders/details";
    }

    // @GetMapping("/export/excel")
    // public ResponseEntity<Resource> exportExcel() {
    // return ordersService.exportToExcel(); // cần viết hàm trong service
    // }

    // @GetMapping("/export/pdf")
    // public ResponseEntity<Resource> exportPdf() {
    // return ordersService.exportToPdf(); // cần viết hàm trong service
    // }
}
