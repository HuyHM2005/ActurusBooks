package com.example.bookstore.controller.admin;

import com.example.bookstore.entity.Category;
import com.example.bookstore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public String showCategoryList(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("categories", categoryService.getAllActiveCategories());
        return "admin/category/category";
    }

    @PostMapping("/add")
    public String addCategory(@ModelAttribute Category category, Model model) {
        try {
            // Đảm bảo không set folderPath khi thêm mới
            if (category.getCategoryId() == null) {
                category.setFolderPath(null);
                category.setDescription(null);
                category.setIsActive(true);
            }
            categoryService.save(category);
            model.addAttribute("successMessage", "Thêm danh mục thành công!");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Có lỗi xảy ra: " + e.getMessage());
        }

        model.addAttribute("category", new Category());
        model.addAttribute("categories", categoryService.getAllActiveCategories());
        return "admin/category/category";
    }

    @GetMapping("/delete")
    public String deleteCategory(@RequestParam("id") Integer id) {
        categoryService.deleteById(id);
        return "redirect:/category/list";
    }

    @GetMapping("/edit")
    public String showEditForm(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("category", categoryService.getById(id));
        model.addAttribute("categories", categoryService.getAllActiveCategories());
        return "admin/category/category"; // dùng lại 1 file
    }
}
