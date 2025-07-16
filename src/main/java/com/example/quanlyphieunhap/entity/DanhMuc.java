package com.example.quanlyphieunhap.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class DanhMuc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String ten;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private DanhMuc parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<DanhMuc> children;

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public DanhMuc getParent() {
        return parent;
    }

    public void setParent(DanhMuc parent) {
        this.parent = parent;
    }

    public List<DanhMuc> getChildren() {
        return children;
    }

    public void setChildren(List<DanhMuc> children) {
        this.children = children;
    }
}