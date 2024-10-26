package com.giuaki.example.ui;

import java.util.List;

import com.giuaki.example.entity.Product;

public class GetProductListViewModel {
    // Danh sách sản phẩm cần hiển thị
    private List<Product> products;

    // Tổng số lượng thực phẩm
    private double tongSoLuongThucPham;

    // Tổng số lượng điện máy
    private double tongSoLuongDienMay;

    // Tổng số lượng sành sứ
    private double tongSoLuongSanhSu;

    /**
     * Constructor để khởi tạo ViewModel với dữ liệu cần thiết.
     * 
     * @param products:            Danh sách sản phẩm.
     * @param tongSoLuongThucPham: Tổng số lượng sản phẩm thuộc nhóm thực phẩm.
     * @param tongSoLuongDienMay:  Tổng số lượng sản phẩm thuộc nhóm điện máy.
     * @param tongSoLuongSanhSu:   Tổng số lượng sản phẩm thuộc nhóm sành sứ.
     */
    public GetProductListViewModel(List<Product> products, double tongSoLuongThucPham,
            double tongSoLuongDienMay, double tongSoLuongSanhSu) {
        this.products = products;
        this.tongSoLuongThucPham = tongSoLuongThucPham;
        this.tongSoLuongDienMay = tongSoLuongDienMay;
        this.tongSoLuongSanhSu = tongSoLuongSanhSu;
    }

    /**
     * Lấy danh sách sản phẩm.
     * 
     * @return List<Product>: Danh sách sản phẩm.
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Lấy tổng số lượng thực phẩm.
     * 
     * @return double: Tổng số lượng thực phẩm.
     */
    public double getTongSoLuongThucPham() {
        return tongSoLuongThucPham;
    }

    /**
     * Lấy tổng số lượng điện máy.
     * 
     * @return double: Tổng số lượng điện máy.
     */
    public double getTongSoLuongDienMay() {
        return tongSoLuongDienMay;
    }

    /**
     * Lấy tổng số lượng sành sứ.
     * 
     * @return double: Tổng số lượng sành sứ.
     */
    public double getTongSoLuongSanhSu() {
        return tongSoLuongSanhSu;
    }
}
