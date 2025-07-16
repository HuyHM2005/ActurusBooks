package com.example.bookstore.controller.customer;

import com.example.bookstore.entity.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String userProfile(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        // ✅ Nếu là admin → không cho vào trang khách
        if ("admin".equalsIgnoreCase(user.getRole())) {
            return "redirect:/admin/dashboard";
        }

        // ✅ Nếu là user thường → hiển thị profile
        model.addAttribute("user", user);
        return "customer/profile/profile";
    }
}
