package com.example.bookstore.controller.customer;

import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.service.BookService;
import com.example.bookstore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/product")
    public String product(Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice) {

        Pageable pageable = PageRequest.of(page, size);
        Page<BookDTO> bookPage;

        // Apply filters
        if (category != null || minPrice != null || maxPrice != null) {
            bookPage = bookService.findBooksWithFilters(category, minPrice, maxPrice, pageable);
        } else {
            bookPage = bookService.findAll(pageable);
        }

        model.addAttribute("books", bookPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", bookPage.getTotalPages());
        model.addAttribute("totalItems", bookPage.getTotalElements());
        model.addAttribute("hasNext", bookPage.hasNext());
        model.addAttribute("hasPrevious", bookPage.hasPrevious());

        // Add filter parameters for maintaining state
        model.addAttribute("selectedCategory", category);
        model.addAttribute("selectedMinPrice", minPrice);
        model.addAttribute("selectedMaxPrice", maxPrice);

        // Add categories for filter options
        model.addAttribute("categories", categoryService.getAllActiveCategories());

        return "customer/product/product";
    }

    @GetMapping("/product-detail/{id}")
    public String productDetail(@PathVariable("id") Integer id, Model model) {
        BookDTO book = bookService.findById(id);
        model.addAttribute("book", book);
        return "customer/product/product-detail";
    }
}