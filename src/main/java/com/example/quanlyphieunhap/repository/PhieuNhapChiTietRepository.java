package com.example.quanlyphieunhap.repository;

import com.example.quanlyphieunhap.entity.PhieuNhapChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PhieuNhapChiTietRepository extends JpaRepository<PhieuNhapChiTiet, Integer> {
    List<PhieuNhapChiTiet> findByIdPhieuNhap(Integer idPhieuNhap);
} 