package com.giuaki.example.ui;

import com.giuaki.example.usecase.GetProductListOutputBoundary;
import com.giuaki.example.usecase.ResponseData;

public class ProductManagementPresenter implements GetProductListOutputBoundary {
    private ProductManagementForm view; // Tham chiếu đến giao diện người dùng

    /**
     * Phương thức để thiết lập giao diện người dùng cho Presenter.
     * 
     * @param view Giao diện người dùng mà Presenter sẽ tương tác
     */
    public void setView(ProductManagementForm view) {
        this.view = view;
    }

    /**
     * Phương thức để hiển thị danh sách sản phẩm trên giao diện người dùng.
     * 
     * @param responseData Dữ liệu phản hồi chứa thông tin sản phẩm
     */
    @Override
    public void displayProductList(ResponseData responseData) {
        // Tạo ViewModel từ dữ liệu phản hồi
        GetProductListViewModel viewModel = new GetProductListViewModel(
                responseData.getData().getProducts(),
                responseData.getData().getTongThucPham(),
                responseData.getData().getTongDienMay(),
                responseData.getData().getTongSanhSu());

        // Cập nhật giao diện người dùng với ViewModel
        view.updateView(viewModel);
    }
}
