package com.giuaki.example.entity;

public abstract class Product {
    // Mã sản phẩm
    private String maHang;

    // Tên sản phẩm
    private String tenHang;

    // Số lượng tồn kho
    private int soLuongTon;

    // Đơn giá của sản phẩm
    private double donGia;

    /**
     * Constructor khởi tạo đối tượng Product với các thuộc tính cơ bản.
     *
     * @param maHang     Mã sản phẩm.
     * @param tenHang    Tên sản phẩm.
     * @param soLuongTon Số lượng tồn kho.
     * @param donGia     Đơn giá sản phẩm.
     */
    public Product(String maHang, String tenHang, int soLuongTon, double donGia) {
        this.maHang = maHang;
        this.tenHang = tenHang;
        this.soLuongTon = soLuongTon;
        this.donGia = donGia;
    }

    /**
     * Lấy mã sản phẩm.
     * 
     * @return Mã sản phẩm.
     */
    public String getMaHang() {
        return maHang;
    }

    /**
     * Lấy tên sản phẩm.
     * 
     * @return Tên sản phẩm.
     */
    public String getTenHang() {
        return tenHang;
    }

    /**
     * Thiết lập tên sản phẩm mới.
     * Chỉ thay đổi nếu tên không rỗng hoặc null.
     *
     * @param tenHang Tên sản phẩm mới.
     */
    public void setTenHang(String tenHang) {
        if (tenHang != null && !tenHang.trim().isEmpty()) {
            this.tenHang = tenHang;
        }
    }

    /**
     * Lấy số lượng tồn kho.
     * 
     * @return Số lượng tồn kho.
     */
    public int getSoLuongTon() {
        return soLuongTon;
    }

    /**
     * Thiết lập số lượng tồn kho mới.
     * Chỉ cho phép giá trị >= 0.
     *
     * @param soLuongTon Số lượng tồn kho mới.
     */
    public void setSoLuongTon(int soLuongTon) {
        if (soLuongTon >= 0) {
            this.soLuongTon = soLuongTon;
        }
    }

    /**
     * Lấy đơn giá sản phẩm.
     * 
     * @return Đơn giá sản phẩm.
     */
    public double getDonGia() {
        return donGia;
    }

    /**
     * Thiết lập đơn giá mới cho sản phẩm.
     * Đơn giá phải > 0.
     *
     * @param donGia Đơn giá mới.
     */
    public void setDonGia(double donGia) {
        if (donGia > 0) {
            this.donGia = donGia;
        }
    }

    /**
     * Phương thức trừu tượng tính VAT (thuế giá trị gia tăng).
     * Các lớp con phải triển khai phương thức này.
     *
     * @return Giá trị VAT của sản phẩm.
     */
    public abstract double tinhVAT();
}
