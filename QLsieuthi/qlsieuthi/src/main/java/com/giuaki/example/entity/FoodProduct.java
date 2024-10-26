package com.giuaki.example.entity;

import java.util.Date;

public class FoodProduct extends Product {
    // Ngày sản xuất của sản phẩm thực phẩm
    private Date ngaySanXuat;

    // Ngày hết hạn của sản phẩm thực phẩm
    private Date ngayHetHan;

    // Tên nhà cung cấp sản phẩm
    private String nhaCungCap;

    /**
     * Constructor để khởi tạo đối tượng FoodProduct với các thuộc tính cụ thể.
     * 
     * @param maHang      Mã sản phẩm.
     * @param tenHang     Tên sản phẩm.
     * @param soLuongTon  Số lượng tồn kho.
     * @param donGia      Đơn giá sản phẩm.
     * @param ngaySanXuat Ngày sản xuất.
     * @param ngayHetHan  Ngày hết hạn.
     * @param nhaCungCap  Tên nhà cung cấp.
     */
    public FoodProduct(String maHang, String tenHang, int soLuongTon, double donGia,
            Date ngaySanXuat, Date ngayHetHan, String nhaCungCap) {
        // Gọi constructor của lớp cha để khởi tạo các thuộc tính chung
        super(maHang, tenHang, soLuongTon, donGia);
        this.ngaySanXuat = ngaySanXuat;
        this.ngayHetHan = ngayHetHan;
        this.nhaCungCap = nhaCungCap;
    }

    /**
     * Lấy ngày sản xuất của sản phẩm.
     * 
     * @return Ngày sản xuất.
     */
    public Date getNgaySanXuat() {
        return ngaySanXuat;
    }

    /**
     * Thiết lập ngày sản xuất mới cho sản phẩm.
     * 
     * @param ngaySanXuat Ngày sản xuất mới.
     */
    public void setNgaySanXuat(Date ngaySanXuat) {
        this.ngaySanXuat = ngaySanXuat;
    }

    /**
     * Lấy ngày hết hạn của sản phẩm.
     * 
     * @return Ngày hết hạn.
     */
    public Date getNgayHetHan() {
        return ngayHetHan;
    }

    /**
     * Thiết lập ngày hết hạn mới cho sản phẩm.
     * Ngày hết hạn phải bằng hoặc sau ngày sản xuất.
     * 
     * @param ngayHetHan Ngày hết hạn mới.
     */
    public void setNgayHetHan(Date ngayHetHan) {
        // Kiểm tra ngày hết hạn phải >= ngày sản xuất
        if (ngayHetHan.after(ngaySanXuat) || ngayHetHan.equals(ngaySanXuat)) {
            this.ngayHetHan = ngayHetHan;
        }
    }

    /**
     * Lấy tên nhà cung cấp của sản phẩm.
     * 
     * @return Tên nhà cung cấp.
     */
    public String getNhaCungCap() {
        return nhaCungCap;
    }

    /**
     * Thiết lập nhà cung cấp mới cho sản phẩm.
     * 
     * @param nhaCungCap Tên nhà cung cấp mới.
     */
    public void setNhaCungCap(String nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }

    /**
     * Tính VAT (thuế giá trị gia tăng) cho sản phẩm thực phẩm.
     * 
     * @return Giá trị VAT, được tính là 5% của đơn giá.
     */
    @Override
    public double tinhVAT() {
        return getDonGia() * 0.05; // VAT = 5% đơn giá
    }
}
