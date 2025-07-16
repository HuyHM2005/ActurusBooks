package com.example.quanlyphieunhap.controller;

import com.example.quanlyphieunhap.entity.PhieuNhap;
import com.example.quanlyphieunhap.entity.PhieuNhapChiTiet;
import com.example.quanlyphieunhap.repository.PhieuNhapRepository;
import com.example.quanlyphieunhap.repository.PhieuNhapChiTietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/phieu-nhap")
public class PhieuNhapController {
    @Autowired
    private PhieuNhapRepository phieuNhapRepo;
    @Autowired
    private PhieuNhapChiTietRepository chiTietRepo;

    @GetMapping
    public List<PhieuNhap> getAllPhieuNhap() {
        return phieuNhapRepo.findAll();
    }

    @PostMapping
    public PhieuNhap createPhieuNhap(@RequestBody PhieuNhap phieuNhap) {
        return phieuNhapRepo.save(phieuNhap);
    }

    @GetMapping("/{id}/chi-tiet")
    public List<PhieuNhapChiTiet> getChiTietByPhieuNhap(@PathVariable Integer id) {
        return chiTietRepo.findByIdPhieuNhap(id);
    }

    @PostMapping("/{id}/chi-tiet")
    public PhieuNhapChiTiet createChiTiet(@PathVariable Integer id, @RequestBody PhieuNhapChiTiet chiTiet) {
        chiTiet.setIdPhieuNhap(id);
        return chiTietRepo.save(chiTiet);
    }
} 