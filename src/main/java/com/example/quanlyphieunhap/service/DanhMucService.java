package com.example.quanlyphieunhap.service;

import com.example.quanlyphieunhap.entity.DanhMuc;
import com.example.quanlyphieunhap.repository.DanhMucRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DanhMucService {
    @Autowired
    private DanhMucRepository danhMucRepository;

    public List<DanhMuc> getAllRootCategories() {
        return danhMucRepository.findByParentIsNull();
    }

    public List<DanhMuc> getChildren(Integer parentId) {
        return danhMucRepository.findByParentId(parentId);
    }

    public DanhMuc save(DanhMuc danhMuc) {
        return danhMucRepository.save(danhMuc);
    }

    public void delete(Integer id) {
        danhMucRepository.deleteById(id);
    }

    public DanhMuc getById(Integer id) {
        return danhMucRepository.findById(id).orElse(null);
    }
}