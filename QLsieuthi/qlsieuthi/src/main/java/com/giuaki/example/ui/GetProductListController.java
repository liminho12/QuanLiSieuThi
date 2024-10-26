package com.giuaki.example.ui;

import com.giuaki.example.usecase.GetProductListInputBoundary;
import com.giuaki.example.usecase.RequestData;

public class GetProductListController {
    // Tham chiếu đến UseCase thông qua interface GetProductListInputBoundary
    private final GetProductListInputBoundary useCase;

    /**
     * Constructor khởi tạo controller với một use case cụ thể.
     * 
     * @param useCase: UseCase thực hiện logic lấy danh sách sản phẩm.
     */
    public GetProductListController(GetProductListInputBoundary useCase) {
        this.useCase = useCase;
    }

    /**
     * Phương thức nhận từ giao diện người dùng (UI) để lấy danh sách sản phẩm.
     * 
     * @param keyword: Từ khóa tìm kiếm (có thể null nếu không tìm kiếm).
     */
    public void getProductList(String keyword) {
        // Tạo RequestData từ từ khóa tìm kiếm
        RequestData request = new RequestData(keyword);

        // Gọi UseCase để xử lý yêu cầu và lấy danh sách sản phẩm
        useCase.getProductList(request);
    }
}
