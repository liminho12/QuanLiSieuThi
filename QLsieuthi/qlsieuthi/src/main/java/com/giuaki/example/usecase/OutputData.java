package com.giuaki.example.usecase;

import java.util.List;

import com.giuaki.example.entity.Product;

public class OutputData {
    // Danh sách sản phẩm
    private List<Product> danhSachSanPham;
    // Thông báo cho người dùng
    private String thongBao;
    // Trạng thái thành công hay không
    private boolean thanhCong;

    /**
     * Constructor khởi tạo đối tượng OutputData.
     * 
     * @param danhSachSanPham Danh sách sản phẩm
     * @param thongBao        Thông báo
     * @param thanhCong       Trạng thái thành công
     */
    public OutputData(List<Product> danhSachSanPham, String thongBao, boolean thanhCong) {
        this.danhSachSanPham = danhSachSanPham;
        this.thongBao = thongBao;
        this.thanhCong = thanhCong;
    }

    /**
     * Phương thức lấy danh sách sản phẩm.
     * 
     * @return Danh sách sản phẩm
     */
    public List<Product> getDanhSachSanPham() {
        return danhSachSanPham;
    }

    /**
     * Phương thức lấy thông báo.
     * 
     * @return Thông báo
     */
    public String getThongBao() {
        return thongBao;
    }

    /**
     * Phương thức kiểm tra trạng thái thành công.
     * 
     * @return true nếu thành công, false nếu không
     */
    public boolean isThanhCong() {
        return thanhCong;
    }
}
