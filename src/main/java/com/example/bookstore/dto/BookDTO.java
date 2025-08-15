package com.example.bookstore.dto;

import lombok.Data;

@Data
public class BookDTO {
    private Integer bookId;
    private String title;
    private String description;
    private Double price;
    private Integer stock;
    private String category; // Giữ lại để tương thích
    private String imageUrl;

    // Thêm fields mới cho category system
    private Integer categoryId;
    private String categoryName;
    private String folderPath;
}