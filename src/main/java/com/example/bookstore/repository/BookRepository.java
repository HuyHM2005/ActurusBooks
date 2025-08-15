package com.example.bookstore.repository;

import com.example.bookstore.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findTop4ByOrderByBookIdDesc();

    List<Book> findTop8ByOrderByBookIdDesc();

    // Filter by category
    Page<Book> findByCategoryContainingIgnoreCase(String category, Pageable pageable);

    // Filter by price range
    Page<Book> findByPriceBetween(Double minPrice, Double maxPrice, Pageable pageable);

    // Filter by category and price range
    Page<Book> findByCategoryContainingIgnoreCaseAndPriceBetween(String category, Double minPrice, Double maxPrice,
            Pageable pageable);

    // Custom query for advanced filtering
    @Query("SELECT b FROM Book b WHERE " +
            "(:category IS NULL OR LOWER(b.category) LIKE LOWER(CONCAT('%', :category, '%'))) AND " +
            "(:minPrice IS NULL OR b.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR b.price <= :maxPrice)")
    Page<Book> findBooksWithFilters(@Param("category") String category,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            Pageable pageable);
}
