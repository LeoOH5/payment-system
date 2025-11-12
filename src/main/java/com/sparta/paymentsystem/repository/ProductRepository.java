package com.sparta.paymentsystem.repository;

import com.sparta.paymentsystem.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

    List<Product> findByStockGreaterThan(Integer stock);

    // 새로 추가된 메서드들
    List<Product> findByStatus(Product.ProductStatus status);

    List<Product> findByCategory(String category);

    List<Product> findByStockLessThanEqual(Integer stock);
}
