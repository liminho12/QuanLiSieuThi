package com.giuaki.example.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.giuaki.example.database.ProductDAOMemory;
import com.giuaki.example.entity.CeramicProduct;
import com.giuaki.example.entity.ElectronicsProduct;
import com.giuaki.example.entity.FoodProduct;
import com.giuaki.example.entity.Product;

public class ProductManagementForm extends JFrame {
    private JTable productTable;
    private JButton themButton;
    private JButton suaButton;
    private JButton xoaButton;
    private JButton timKiemButton;
    private GetProductListController controller;
    private DefaultTableModel tableModel;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private ProductDAOMemory database;
    private JLabel lblTongThucPham;
    private JLabel lblTongDienMay;
    private JLabel lblTongSanhSu;

    public ProductDAOMemory getDatabase() {
        return database;
    }

    public ProductManagementForm(GetProductListController controller, ProductDAOMemory database) {
        this.controller = controller;
        this.database = database;
        initComponents();
    }

    private void initComponents() {
        setTitle("Quản Lý Kho Hàng Siêu Thị");
        setSize(1200, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel thông tin tổng hợp
        JPanel summaryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
        summaryPanel.setBorder(BorderFactory.createTitledBorder("Thông tin tổng hợp"));

        lblTongThucPham = new JLabel("Tổng số lượng thực phẩm: 0");
        lblTongDienMay = new JLabel("Tổng số lượng điện máy: 0");
        lblTongSanhSu = new JLabel("Tổng số lượng sành sứ: 0");

        summaryPanel.add(lblTongThucPham);
        summaryPanel.add(lblTongDienMay);
        summaryPanel.add(lblTongSanhSu);

        add(summaryPanel, BorderLayout.NORTH);

        // Tạo bảng sản phẩm
        String[] columnNames = { "Mã Hàng", "Tên Hàng", "Số Lượng Tồn", "Đơn Giá", "Loại SP", "Thông Tin Thêm", "VAT" };
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        productTable = new JTable(tableModel);

        // Set column widths
        productTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        productTable.getColumnModel().getColumn(1).setPreferredWidth(200);
        productTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        productTable.getColumnModel().getColumn(3).setPreferredWidth(150);
        productTable.getColumnModel().getColumn(4).setPreferredWidth(100);
        productTable.getColumnModel().getColumn(5).setPreferredWidth(300);
        productTable.getColumnModel().getColumn(6).setPreferredWidth(100);

        JScrollPane scrollPane = new JScrollPane(productTable);
        add(scrollPane, BorderLayout.CENTER);

        // Panel chứa các nút chức năng
        JPanel buttonPanel = new JPanel();
        themButton = new JButton("Thêm Sản Phẩm");
        suaButton = new JButton("Sửa Sản Phẩm");
        xoaButton = new JButton("Xóa Sản Phẩm");
        timKiemButton = new JButton("Tìm Kiếm");

        buttonPanel.add(themButton);
        buttonPanel.add(suaButton);
        buttonPanel.add(xoaButton);
        buttonPanel.add(timKiemButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Thêm sự kiện cho các nút
        themButton.addActionListener(e -> handleThem());
        suaButton.addActionListener(e -> handleSua());
        xoaButton.addActionListener(e -> handleXoa());
        timKiemButton.addActionListener(e -> handleTimKiem());

        // Set table properties
        productTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        productTable.getTableHeader().setReorderingAllowed(false);
    }

    private void handleThem() {
        ThemSanPhamDialog dialog = new ThemSanPhamDialog(this);
        dialog.setVisible(true);
        controller.getProductList(null);
    }

    public void refreshData() {
        controller.getProductList(null);
    }

    private void handleSua() {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow >= 0) {
            String maHang = (String) tableModel.getValueAt(selectedRow, 0);
            Product selectedProduct = database.getProductByMaHang(maHang);
            if (selectedProduct != null) {
                SuaSanPhamDialog dialog = new SuaSanPhamDialog(this, selectedProduct);
                dialog.setVisible(true);
                controller.getProductList(null);
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    "Vui lòng chọn sản phẩm cần sửa",
                    "Thông báo",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void handleXoa() {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow >= 0) {
            String maHang = (String) tableModel.getValueAt(selectedRow, 0);
            int option = JOptionPane.showConfirmDialog(this,
                    "Bạn có chắc muốn xóa sản phẩm này?",
                    "Xác nhận xóa",
                    JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                if (database.deleteProduct(maHang)) {
                    JOptionPane.showMessageDialog(this,
                            "Xóa sản phẩm thành công!",
                            "Thông báo",
                            JOptionPane.INFORMATION_MESSAGE);
                    controller.getProductList(null);
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Không thể xóa sản phẩm!",
                            "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    "Vui lòng chọn sản phẩm cần xóa",
                    "Thông báo",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void handleTimKiem() {
        String keyword = JOptionPane.showInputDialog(this, "Nhập từ khóa tìm kiếm:");
        if (keyword != null) {
            controller.getProductList(keyword);
            String searchTerm = keyword.toLowerCase();
            for (int i = 0; i < productTable.getRowCount(); i++) {
                String maHang = productTable.getValueAt(i, 0).toString().toLowerCase();
                String tenHang = productTable.getValueAt(i, 1).toString().toLowerCase();
                if (maHang.contains(searchTerm) || tenHang.contains(searchTerm)) {
                    productTable.setRowSelectionInterval(i, i);
                    productTable.scrollRectToVisible(productTable.getCellRect(i, 0, true));
                }
            }
        }
    }

    public void updateView(GetProductListViewModel viewModel) {
        tableModel.setRowCount(0);
        List<Product> products = viewModel.getProducts();

        double tongThucPham = 0;
        double tongDienMay = 0;
        double tongSanhSu = 0;

        for (Product product : products) {
            Object[] row = {
                    product.getMaHang(),
                    product.getTenHang(),
                    product.getSoLuongTon(),
                    String.format("%,.0f VNĐ", product.getDonGia()),
                    getLoaiSanPham(product),
                    getThongTinThem(product),
                    String.format("%,.0f VNĐ", product.tinhVAT())
            };
            tableModel.addRow(row);

            if (product instanceof FoodProduct) {
                tongThucPham += product.getSoLuongTon();
            } else if (product instanceof ElectronicsProduct) {
                tongDienMay += product.getSoLuongTon();
            } else if (product instanceof CeramicProduct) {
                tongSanhSu += product.getSoLuongTon();
            }
        }

        lblTongThucPham.setText(String.format("Tổng số lượng thực phẩm: %.0f", tongThucPham));
        lblTongDienMay.setText(String.format("Tổng số lượng điện máy: %.0f", tongDienMay));
        lblTongSanhSu.setText(String.format("Tổng số lượng sành sứ: %.0f", tongSanhSu));
    }

    private String getLoaiSanPham(Product product) {
        if (product instanceof FoodProduct) {
            return "Thực phẩm";
        } else if (product instanceof ElectronicsProduct) {
            return "Điện máy";
        } else if (product instanceof CeramicProduct) {
            return "Sành sứ";
        }
        return "Không xác định";
    }

    private String getThongTinThem(Product product) {
        if (product instanceof FoodProduct) {
            FoodProduct food = (FoodProduct) product;
            return String.format("NSX: %s, HSD: %s, NCC: %s",
                    dateFormat.format(food.getNgaySanXuat()),
                    dateFormat.format(food.getNgayHetHan()),
                    food.getNhaCungCap());
        } else if (product instanceof ElectronicsProduct) {
            ElectronicsProduct electronics = (ElectronicsProduct) product;
            return String.format("Bảo hành: %d tháng, Công suất: %.1f KW",
                    electronics.getThoiGianBaoHanh(),
                    electronics.getCongSuat());
        } else if (product instanceof CeramicProduct) {
            CeramicProduct ceramic = (CeramicProduct) product;
            return String.format("NSX: %s, Ngày nhập: %s",
                    ceramic.getNhaSanXuat(),
                    dateFormat.format(ceramic.getNgayNhapKho()));
        }
        return "";
    }
}
