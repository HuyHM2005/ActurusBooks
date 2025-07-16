package com.example.bookstore.controller.admin;

import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/admin/books")
public class AdminBookController {
    @Autowired
    private BookService bookService;

    // Regex linh hoạt hơn, chấp nhận query parameters
    private static final Pattern IMAGE_URL_PATTERN = Pattern.compile(".*\\.(jpg|jpeg|png|gif)(\\?.*)?$",
            Pattern.CASE_INSENSITIVE);

    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.findAll());
        model.addAttribute("book", new BookDTO());
        model.addAttribute("editMode", false);
        return "admin/books/books-mana";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute("book") BookDTO bookDTO, RedirectAttributes redirectAttributes) {
        try {
            // Kiểm tra URL ảnh
            if (bookDTO.getImageUrl() != null && !bookDTO.getImageUrl().isEmpty()) {
                if (!IMAGE_URL_PATTERN.matcher(bookDTO.getImageUrl()).matches()) {
                    redirectAttributes.addFlashAttribute("errorMessage",
                            "URL ảnh không hợp lệ. Vui lòng dùng link trực tiếp (jpg, png, gif). Ví dụ: https://images.unsplash.com/photo-1543002588-bfa74002ed7e");
                    return "redirect:/admin/books";
                }
            }
            bookService.save(bookDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Thêm sách thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi thêm sách: " + e.getMessage());
        }
        return "redirect:/admin/books";
    }

    @PostMapping("/edit")
    public String editBook(@ModelAttribute("book") BookDTO bookDTO, RedirectAttributes redirectAttributes) {
        try {
            if (bookDTO.getImageUrl() != null && !bookDTO.getImageUrl().isEmpty()) {
                if (!IMAGE_URL_PATTERN.matcher(bookDTO.getImageUrl()).matches()) {
                    redirectAttributes.addFlashAttribute("errorMessage",
                            "URL ảnh không hợp lệ. Vui lòng dùng link trực tiếp (jpg, png, gif).");
                    return "redirect:/admin/books";
                }
            }
            bookService.save(bookDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật sách thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi cập nhật sách: " + e.getMessage());
        }
        return "redirect:/admin/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            bookService.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Xóa sách thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi xóa sách: " + e.getMessage());
        }
        return "redirect:/admin/books";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        model.addAttribute("books", bookService.findAll());
        model.addAttribute("editMode", true);
        return "admin/books/books-mana";
    }
}