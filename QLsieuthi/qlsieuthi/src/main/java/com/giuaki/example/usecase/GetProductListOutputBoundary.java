package com.giuaki.example.usecase;

public interface GetProductListOutputBoundary {
    /**
     * Phương thức để hiển thị danh sách sản phẩm.
     * 
     * @param responseData Dữ liệu phản hồi chứa danh sách sản phẩm và thông tin
     *                     liên quan
     */
    void displayProductList(ResponseData responseData);
}
