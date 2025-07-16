package com.example.quanlyphieunhap.repository;

import com.example.quanlyphieunhap.entity.DanhMuc;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DanhMucRepository extends JpaRepository<DanhMuc, Integer> {
    List<DanhMuc> findByParentIsNull();

    List<DanhMuc> findByParentId(Integer parentId);
}