package com.giuaki.example.usecase;

import java.util.List;

import com.giuaki.example.entity.CeramicProduct;
import com.giuaki.example.entity.ElectronicsProduct;
import com.giuaki.example.entity.FoodProduct;
import com.giuaki.example.entity.Product;

public class GetProductListUseCase implements GetProductListInputBoundary {
    private final GetProductListDatabaseBoundary database; // Biến để lưu trữ đối tượng database
    private final GetProductListOutputBoundary presenter; // Biến để lưu trữ đối tượng presenter

    // Constructor để khởi tạo đối tượng
    public GetProductListUseCase(GetProductListDatabaseBoundary database,
            GetProductListOutputBoundary presenter) {
        this.database = database; // Gán đối tượng database
        this.presenter = presenter; // Gán đối tượng presenter
    }

    /**
     * Phương thức thực hiện việc lấy danh sách sản phẩm và hiển thị chúng.
     *
     * @param request Đối tượng chứa thông tin yêu cầu.
     */
    @Override
    public void getProductList(RequestData request) {
        List<Product> products = database.getProductList(); // Lấy danh sách sản phẩm từ cơ sở dữ liệu

        double tongThucPham = 0; // Biến lưu tổng số lượng thực phẩm
        double tongDienMay = 0; // Biến lưu tổng số lượng điện máy
        double tongSanhSu = 0; // Biến lưu tổng số lượng sành sứ

        // Duyệt qua từng sản phẩm và tính tổng số lượng theo loại
        for (Product product : products) {
            if (product instanceof FoodProduct) {
                tongThucPham += product.getSoLuongTon(); // Cộng dồn số lượng thực phẩm
            } else if (product instanceof ElectronicsProduct) {
                tongDienMay += product.getSoLuongTon(); // Cộng dồn số lượng điện máy
            } else if (product instanceof CeramicProduct) {
                tongSanhSu += product.getSoLuongTon(); // Cộng dồn số lượng sành sứ
            }
        }

        // Tạo đối tượng ResponseData với thông tin cần thiết
        ResponseData responseData = new ResponseData(new GetProductListOutputDTO(
                products, tongThucPham, tongDienMay, tongSanhSu));

        // Gọi phương thức để hiển thị danh sách sản phẩm
        presenter.displayProductList(responseData);
    }
}
