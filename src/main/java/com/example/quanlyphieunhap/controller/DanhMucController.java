package com.example.quanlyphieunhap.controller;

import com.example.quanlyphieunhap.entity.DanhMuc;
import com.example.quanlyphieunhap.service.DanhMucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/danh-muc")
public class DanhMucController {
    @Autowired
    private DanhMucService danhMucService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model) {
        model.addAttribute("listDanhMuc", danhMucService.getAllRootCategories());
        return "danh_muc/hien_thi";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("danhMuc", new DanhMuc());
        model.addAttribute("listDanhMuc", danhMucService.getAllRootCategories());
        return "danh_muc/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute DanhMuc danhMuc) {
        danhMucService.save(danhMuc);
        return "redirect:/danh-muc/hien-thi";
    }

    @GetMapping("/edit")
    public String editForm(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("danhMuc", danhMucService.getById(id));
        model.addAttribute("listDanhMuc", danhMucService.getAllRootCategories());
        return "danh_muc/edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute DanhMuc danhMuc) {
        danhMucService.save(danhMuc);
        return "redirect:/danh-muc/hien-thi";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Integer id) {
        danhMucService.delete(id);
        return "redirect:/danh-muc/hien-thi";
    }
}