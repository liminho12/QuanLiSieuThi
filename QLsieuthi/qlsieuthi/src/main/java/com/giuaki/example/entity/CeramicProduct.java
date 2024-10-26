package com.giuaki.example.entity;

import java.util.Date;

public class CeramicProduct extends Product {
    // Thuộc tính nhà sản xuất của sản phẩm sành sứ
    private String nhaSanXuat;

    // Thuộc tính ngày nhập kho của sản phẩm
    private Date ngayNhapKho;

    /**
     * Constructor khởi tạo đối tượng CeramicProduct với các tham số.
     * 
     * @param maHang      Mã sản phẩm.
     * @param tenHang     Tên sản phẩm.
     * @param soLuongTon  Số lượng tồn kho.
     * @param donGia      Đơn giá của sản phẩm.
     * @param nhaSanXuat  Nhà sản xuất.
     * @param ngayNhapKho Ngày nhập kho.
     */
    public CeramicProduct(String maHang, String tenHang, int soLuongTon, double donGia,
            String nhaSanXuat, Date ngayNhapKho) {
        // Gọi constructor của lớp cha Product để khởi tạo các thuộc tính chung
        super(maHang, tenHang, soLuongTon, donGia);
        this.nhaSanXuat = nhaSanXuat;
        this.ngayNhapKho = ngayNhapKho;
    }

    /**
     * Lấy thông tin nhà sản xuất của sản phẩm.
     * 
     * @return Nhà sản xuất.
     */
    public String getNhaSanXuat() {
        return nhaSanXuat;
    }

    /**
     * Thiết lập nhà sản xuất cho sản phẩm.
     * 
     * @param nhaSanXuat Nhà sản xuất mới.
     */
    public void setNhaSanXuat(String nhaSanXuat) {
        this.nhaSanXuat = nhaSanXuat;
    }

    /**
     * Lấy ngày nhập kho của sản phẩm.
     * 
     * @return Ngày nhập kho.
     */
    public Date getNgayNhapKho() {
        return ngayNhapKho;
    }

    /**
     * Thiết lập ngày nhập kho cho sản phẩm.
     * 
     * @param ngayNhapKho Ngày nhập kho mới.
     */
    public void setNgayNhapKho(Date ngayNhapKho) {
        this.ngayNhapKho = ngayNhapKho;
    }

    /**
     * Tính VAT (thuế giá trị gia tăng) cho sản phẩm.
     * 
     * @return Giá trị VAT, được tính là 10% của đơn giá.
     */
    @Override
    public double tinhVAT() {
        return getDonGia() * 0.1; // VAT = 10% đơn giá
    }
}
