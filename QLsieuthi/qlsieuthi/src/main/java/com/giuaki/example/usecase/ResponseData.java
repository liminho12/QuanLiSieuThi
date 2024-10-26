package com.giuaki.example.usecase;

public class ResponseData {
    // Đối tượng chứa thông tin về danh sách sản phẩm và các dữ liệu tổng hợp
    private GetProductListOutputDTO data;

    /**
     * Constructor khởi tạo đối tượng ResponseData.
     * 
     * @param data Đối tượng chứa dữ liệu đầu ra
     */
    public ResponseData(GetProductListOutputDTO data) {
        this.data = data;
    }

    /**
     * Phương thức lấy dữ liệu đầu ra.
     * 
     * @return Đối tượng GetProductListOutputDTO chứa dữ liệu sản phẩm
     */
    public GetProductListOutputDTO getData() {
        return data;
    }
}
