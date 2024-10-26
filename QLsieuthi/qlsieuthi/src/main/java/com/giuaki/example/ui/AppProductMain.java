package com.giuaki.example.ui;

import javax.swing.SwingUtilities;

import com.giuaki.example.database.ProductDAOMemory;
import com.giuaki.example.usecase.GetProductListUseCase;

public class AppProductMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Khởi tạo các thành phần
            ProductDAOMemory database = new ProductDAOMemory();
            ProductManagementPresenter presenter = new ProductManagementPresenter();
            GetProductListUseCase useCase = new GetProductListUseCase(database, presenter);
            GetProductListController controller = new GetProductListController(useCase);

            // Khởi tạo và hiển thị giao diện
            ProductManagementForm form = new ProductManagementForm(controller, database);
            presenter.setView(form);

            // Load dữ liệu ban đầu
            controller.getProductList(null);

            // Hiển thị form
            form.setLocationRelativeTo(null);
            form.setVisible(true);
        });
    }
}
