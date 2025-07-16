package com.example.bookstore;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.servlet.http.HttpSession;
import com.example.bookstore.entity.User;
import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.CartItem;
import com.example.bookstore.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("username", user.getUsername());
        }
        List<Book> newBooks = bookRepository.findTop4ByOrderByBookIdDesc();
        model.addAttribute("newBooks", newBooks);
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/product-detail")
    public String productDetail(@RequestParam(required = false) Integer bookId, Model model) {
        if (bookId != null) {
            Book book = bookRepository.findById(bookId).orElse(null);
            if (book != null) {
                model.addAttribute("book", book);
            } else {
                return "redirect:/product"; // Chuyển hướng nếu không tìm thấy sách
            }
        }
        return "product-detail";
    }

    // Mapping cho giỏ hàng
    @GetMapping("/cart/view")
    public String viewCart(Model model, HttpSession session) {
        @SuppressWarnings("unchecked")
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }
        model.addAttribute("cart", cart);
        model.addAttribute("total", cart.stream().mapToDouble(i -> i.getPrice() * i.getQuantity()).sum());
        return "cart";
    }

    @GetMapping("/cart/add")
    public String addToCart(@RequestParam Integer bookId, @RequestParam(defaultValue = "1") int quantity,
            HttpSession session) {
        System.out.println("addToCart: bookId=" + bookId + ", quantity=" + quantity);
        Book book = bookRepository.findById(bookId).orElse(null);
        if (book == null) {
            System.out.println("Book not found for bookId: " + bookId);
            return "redirect:/product";
        }
        @SuppressWarnings("unchecked")
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }
        Optional<CartItem> existing = cart.stream().filter(i -> i.getBookId().equals(bookId)).findFirst();
        if (existing.isPresent()) {
            existing.get().setQuantity(existing.get().getQuantity() + quantity);
        } else {
            CartItem item = new CartItem();
            item.setBookId(book.getBookId());
            item.setTitle(book.getTitle());
            item.setImageUrl(book.getImageUrl());
            item.setPrice(book.getPrice());
            item.setQuantity(quantity);
            cart.add(item);
        }
        session.setAttribute("cart", cart);
        return "redirect:/cart/view";
    }

    @GetMapping("/cart/update")
    public String updateCart(@RequestParam Integer bookId, @RequestParam int quantity, HttpSession session) {
        @SuppressWarnings("unchecked")
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart != null) {
            cart.stream().filter(i -> i.getBookId().equals(bookId)).findFirst()
                    .ifPresent(i -> i.setQuantity(quantity));
            session.setAttribute("cart", cart);
        }
        return "redirect:/cart/view";
    }

    @GetMapping("/cart/remove/{bookId}")
    public String removeFromCart(@PathVariable Integer bookId, HttpSession session) {
        @SuppressWarnings("unchecked")
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart != null) {
            cart.removeIf(i -> i.getBookId().equals(bookId));
            session.setAttribute("cart", cart);
        }
        return "redirect:/cart/view";
    }

}