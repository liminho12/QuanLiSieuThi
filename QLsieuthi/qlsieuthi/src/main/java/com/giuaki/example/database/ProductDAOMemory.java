package com.giuaki.example.database;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.giuaki.example.entity.CeramicProduct;
import com.giuaki.example.entity.ElectronicsProduct;
import com.giuaki.example.entity.FoodProduct;
import com.giuaki.example.entity.Product;
import com.giuaki.example.usecase.GetProductListDatabaseBoundary;

public class ProductDAOMemory implements GetProductListDatabaseBoundary {
    // Danh sách các sản phẩm lưu trữ trong bộ nhớ
    private List<Product> products;

    public ProductDAOMemory() {
        products = new ArrayList<>(); // Tạo danh sách sản phẩm trống
        initSampleData(); // Khởi tạo dữ liệu mẫu
    }

    private void initSampleData() {
    Calendar cal = Calendar.getInstance();
    Date currentDate = cal.getTime();
    
    // Tạo ngày hết hạn còn 1 tuần
    cal.add(Calendar.DATE, 7);
    Date oneWeekExpiry = cal.getTime();
    
    // Tạo ngày hết hạn xa hơn (6 tháng)
    cal.add(Calendar.MONTH, 6);
    Date farExpiry = cal.getTime();

    // 6 sản phẩm ban đầu
    products.add(new FoodProduct("F001", "Gạo Nàng Hương", 100, 18000.0, currentDate, oneWeekExpiry, "Công ty TNHH ABC"));
    products.add(new FoodProduct("F002", "Đường trắng", 150, 22000.0, currentDate, oneWeekExpiry, "Công ty XYZ"));
    products.add(new ElectronicsProduct("E001", "Tủ lạnh Samsung", 10, 15000000.0, 24, 2.0));
    products.add(new ElectronicsProduct("E002", "Máy giặt LG", 15, 12000000.0, 12, 1.5));
    products.add(new CeramicProduct("C001", "Bộ ấm trà", 50, 500000.0, "Bát Tràng", currentDate));
    products.add(new CeramicProduct("C002", "Bộ bát đĩa", 30, 800000.0, "Minh Long", currentDate));

    // Thêm 6 sản phẩm mới
    products.add(new FoodProduct("F004", "Mì gói Hảo Hảo", 300, 4000.0, currentDate, oneWeekExpiry, "Acecook"));
    products.add(new FoodProduct("F005", "Sữa tươi TH True Milk", 200, 7500.0, currentDate, oneWeekExpiry, "TH True Milk"));
    products.add(new ElectronicsProduct("E003", "Điều hòa Panasonic", 8, 8500000.0, 36, 1.8));
    products.add(new ElectronicsProduct("E004", "Nồi cơm điện Sharp", 25, 1200000.0, 12, 0.8));
    products.add(new CeramicProduct("C003", "Bình hoa gốm", 40, 350000.0, "Bát Tràng", currentDate));
    products.add(new CeramicProduct("C004", "Tượng gốm trang trí", 20, 450000.0, "Chu Đậu", currentDate));
}

    // Biến tham chiếu đến đối tượng database của chính lớp này
    private ProductDAOMemory database;

    /**
     * Trả về đối tượng database hiện tại.
     */
    public ProductDAOMemory getDatabase() {
        return database;
    }

    /**
     * Lấy danh sách tất cả sản phẩm trong bộ nhớ.
     * 
     * @return Danh sách sản phẩm.
     */
    @Override
    public List<Product> getProductList() {
        return new ArrayList<>(products); // Trả về một bản sao của danh sách
    }

    /**
     * Tìm kiếm sản phẩm theo từ khóa.
     * So khớp từ khóa với mã hàng hoặc tên hàng.
     * 
     * @param keyword Từ khóa tìm kiếm.
     * @return Danh sách sản phẩm phù hợp với từ khóa.
     */
    public List<Product> searchProducts(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return getProductList(); // Trả về tất cả nếu từ khóa rỗng
        }

        String searchTerm = keyword.toLowerCase(); // Chuyển từ khóa sang chữ thường để tìm kiếm không phân biệt hoa
                                                   // thường
        return products.stream()
                .filter(p -> p.getMaHang().toLowerCase().contains(searchTerm) ||
                        p.getTenHang().toLowerCase().contains(searchTerm))
                .collect(Collectors.toList());
    }

    /**
     * Thêm sản phẩm mới vào danh sách.
     * 
     * @param product Sản phẩm cần thêm.
     * @return true nếu thêm thành công, false nếu sản phẩm đã tồn tại.
     */
    public boolean addProduct(Product product) {
        if (getProductByMaHang(product.getMaHang()) == null) { // Kiểm tra trùng mã
            return products.add(product); // Thêm sản phẩm nếu chưa tồn tại
        }
        return false; // Không thêm nếu mã sản phẩm đã tồn tại
    }

    /**
     * Cập nhật thông tin sản phẩm.
     * 
     * @param product Sản phẩm với thông tin cập nhật.
     * @return true nếu cập nhật thành công, false nếu không tìm thấy sản phẩm.
     */
    public boolean updateProduct(Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getMaHang().equals(product.getMaHang())) {
                products.set(i, product); // Thay thế sản phẩm tại vị trí tìm thấy
                return true; // Cập nhật thành công
            }
        }
        return false; // Không tìm thấy sản phẩm để cập nhật
    }

    /**
     * Xóa sản phẩm theo mã hàng.
     * 
     * @param maHang Mã hàng của sản phẩm cần xóa.
     * @return true nếu xóa thành công, false nếu không tìm thấy sản phẩm.
     */
    public boolean deleteProduct(String maHang) {
        return products.removeIf(product -> product.getMaHang().equals(maHang)); // Xóa nếu mã hàng khớp
    }

    /**
     * Lấy thông tin sản phẩm theo mã hàng.
     * 
     * @param maHang Mã hàng của sản phẩm cần tìm.
     * @return Sản phẩm nếu tìm thấy, null nếu không tìm thấy.
     */
    public Product getProductByMaHang(String maHang) {
        return products.stream()
                .filter(p -> p.getMaHang().equals(maHang))
                .findFirst()
                .orElse(null); // Trả về null nếu không tìm thấy
    }

    /**
     * Tính tổng số lượng tồn kho của các sản phẩm thực phẩm.
     * 
     * @return Tổng số lượng tồn của thực phẩm.
     */
    public double getTongSoLuongThucPham() {
        return products.stream()
                .filter(p -> p instanceof FoodProduct)
                .mapToDouble(Product::getSoLuongTon)
                .sum();
    }

    /**
     * Tính tổng số lượng tồn kho của các sản phẩm điện máy.
     * 
     * @return Tổng số lượng tồn của điện máy.
     */
    public double getTongSoLuongDienMay() {
        return products.stream()
                .filter(p -> p instanceof ElectronicsProduct)
                .mapToDouble(Product::getSoLuongTon)
                .sum();
    }

    /**
     * Tính tổng số lượng tồn kho của các sản phẩm sành sứ.
     * 
     * @return Tổng số lượng tồn của sành sứ.
     */
    public double getTongSoLuongSanhSu() {
        return products.stream()
                .filter(p -> p instanceof CeramicProduct)
                .mapToDouble(Product::getSoLuongTon)
                .sum();
    }
}
