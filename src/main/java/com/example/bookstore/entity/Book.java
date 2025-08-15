package com.example.bookstore.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Books")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Integer bookId;

    private String title;
    private String description;
    private Double price;
    private Integer stock;

    // Giữ lại field category cũ để tương thích
    private String category;

    @Column(name = "image_url")
    private String imageUrl;
}