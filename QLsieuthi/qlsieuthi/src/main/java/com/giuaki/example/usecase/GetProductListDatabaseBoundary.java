package com.giuaki.example.usecase;

import java.util.List;

import com.giuaki.example.entity.Product;

public interface GetProductListDatabaseBoundary {
    /**
     * Phương thức để lấy danh sách sản phẩm.
     * 
     * @return Danh sách các sản phẩm.
     */
    List<Product> getProductList(); // Phương thức trả về danh sách sản phẩm
}
