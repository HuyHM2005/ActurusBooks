package com.example.quanlyphieunhap.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "phieu_nhap_chi_tiet")
public class PhieuNhapChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_phieu_nhap")
    private Integer idPhieuNhap;

    @Column(name = "ten_san_pham")
    private String tenSanPham;

    @Column(name = "so_luong")
    private Integer soLuong;

    @NotNull(message = "Giá nhập không được để trống")
    @DecimalMin(value = "100.01", message = "Giá nhập phải lớn hơn 100")
    @Column(name = "gia_nhap")
    private BigDecimal giaNhap;

    @Column(name = "ngay_san_xuat")
    private LocalDate ngaySanXuat;

    // Getters and setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getIdPhieuNhap() { return idPhieuNhap; }
    public void setIdPhieuNhap(Integer idPhieuNhap) { this.idPhieuNhap = idPhieuNhap; }
    public String getTenSanPham() { return tenSanPham; }
    public void setTenSanPham(String tenSanPham) { this.tenSanPham = tenSanPham; }
    public Integer getSoLuong() { return soLuong; }
    public void setSoLuong(Integer soLuong) { this.soLuong = soLuong; }
    public BigDecimal getGiaNhap() { return giaNhap; }
    public void setGiaNhap(BigDecimal giaNhap) { this.giaNhap = giaNhap; }
    public LocalDate getNgaySanXuat() { return ngaySanXuat; }
    public void setNgaySanXuat(LocalDate ngaySanXuat) { this.ngaySanXuat = ngaySanXuat; }
} 