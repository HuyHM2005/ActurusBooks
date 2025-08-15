package com.example.bookstore.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "category")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer categoryId;

    @Column(name = "ten")
    private String categoryName;

    @Column(name = "description")
    private String description;

    @Column(name = "folder_path")
    private String folderPath; // Đường dẫn thư mục lưu trữ

    @Column(name = "is_active")
    private Boolean isActive = true;
}
