package com.giuaki.example.usecase;

import java.util.List;

import com.giuaki.example.entity.Product;

public class GetProductListOutputDTO {
    private List<Product> products;
    private double tongThucPham;
    private double tongDienMay;
    private double tongSanhSu;

    // Constructor để khởi tạo đối tượng
    public GetProductListOutputDTO(List<Product> products, double tongThucPham,
            double tongDienMay, double tongSanhSu) {
        this.products = products; // Gán danh sách sản phẩm
        this.tongThucPham = tongThucPham; // Gán tổng số lượng thực phẩm
        this.tongDienMay = tongDienMay; // Gán tổng số lượng điện máy
        this.tongSanhSu = tongSanhSu; // Gán tổng số lượng sành sứ
    }

    // Phương thức trả về danh sách sản phẩm
    public List<Product> getProducts() {
        return products;
    }

    // Phương thức trả về tổng số lượng thực phẩm
    public double getTongThucPham() {
        return tongThucPham;
    }

    // Phương thức trả về tổng số lượng điện máy
    public double getTongDienMay() {
        return tongDienMay;
    }

    // Phương thức trả về tổng số lượng sành sứ
    public double getTongSanhSu() {
        return tongSanhSu;
    }
}
