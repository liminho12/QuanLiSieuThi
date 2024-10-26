package com.giuaki.example.usecase; // Định nghĩa gói của lớp

public interface GetProductListInputBoundary {
    /**
     * Phương thức để lấy danh sách sản phẩm dựa trên yêu cầu.
     *
     * @param request Đối tượng chứa thông tin yêu cầu.
     */
    // Phương thức nhận một đối tượng RequestData và thực hiện việc lấy danh sách
    // sản phẩm
    void getProductList(RequestData request);
}
