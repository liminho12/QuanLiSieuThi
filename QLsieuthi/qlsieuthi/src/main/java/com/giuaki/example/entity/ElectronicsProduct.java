package com.giuaki.example.entity;

public class ElectronicsProduct extends Product {
    // Thời gian bảo hành của sản phẩm điện tử, tính bằng tháng
    private int thoiGianBaoHanh;

    // Công suất của sản phẩm điện tử, tính bằng kW
    private double congSuat;

    /**
     * Constructor khởi tạo đối tượng ElectronicsProduct với các tham số.
     * 
     * @param maHang          Mã sản phẩm.
     * @param tenHang         Tên sản phẩm.
     * @param soLuongTon      Số lượng tồn kho.
     * @param donGia          Đơn giá của sản phẩm.
     * @param thoiGianBaoHanh Thời gian bảo hành (tháng).
     * @param congSuat        Công suất (kW).
     */
    public ElectronicsProduct(String maHang, String tenHang, int soLuongTon, double donGia,
            int thoiGianBaoHanh, double congSuat) {
        // Gọi constructor của lớp cha Product để khởi tạo các thuộc tính chung
        super(maHang, tenHang, soLuongTon, donGia);
        this.thoiGianBaoHanh = thoiGianBaoHanh;
        this.congSuat = congSuat;
    }

    /**
     * Lấy thời gian bảo hành của sản phẩm.
     * 
     * @return Thời gian bảo hành (tháng).
     */
    public int getThoiGianBaoHanh() {
        return thoiGianBaoHanh;
    }

    /**
     * Thiết lập thời gian bảo hành mới cho sản phẩm.
     * Thời gian bảo hành không được nhỏ hơn 0.
     * 
     * @param thoiGianBaoHanh Thời gian bảo hành (tháng).
     */
    public void setThoiGianBaoHanh(int thoiGianBaoHanh) {
        if (thoiGianBaoHanh >= 0) { // Kiểm tra tính hợp lệ của thời gian bảo hành
            this.thoiGianBaoHanh = thoiGianBaoHanh;
        }
    }

    /**
     * Lấy công suất của sản phẩm.
     * 
     * @return Công suất (kW).
     */
    public double getCongSuat() {
        return congSuat;
    }

    /**
     * Thiết lập công suất mới cho sản phẩm.
     * Công suất phải lớn hơn 0 để hợp lệ.
     * 
     * @param congSuat Công suất mới (kW).
     */
    public void setCongSuat(double congSuat) {
        if (congSuat > 0) { // Kiểm tra công suất có hợp lệ hay không
            this.congSuat = congSuat;
        }
    }

    /**
     * Tính VAT (thuế giá trị gia tăng) cho sản phẩm điện tử.
     * 
     * @return Giá trị VAT, được tính là 10% của đơn giá.
     */
    @Override
    public double tinhVAT() {
        return getDonGia() * 0.1; // VAT = 10% đơn giá
    }
}
