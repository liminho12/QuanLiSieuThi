package com.giuaki.example.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.giuaki.example.entity.CeramicProduct;
import com.giuaki.example.entity.ElectronicsProduct;
import com.giuaki.example.entity.FoodProduct;
import com.giuaki.example.entity.Product;

public class SuaSanPhamDialog extends JDialog {
    private Product sanPhamHienTai;
    private JTextField maHangField;
    private JTextField tenHangField;
    private JTextField soLuongField;
    private JTextField donGiaField;
    private JButton xacNhanButton;
    private JButton huyButton;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    // Các trường riêng cho từng loại sản phẩm
    private JTextField nhaCungCapField; // cho FoodProduct
    private JTextField thoiGianBaoHanhField; // cho ElectronicsProduct
    private JTextField congSuatField; // cho ElectronicsProduct
    private JTextField nhaSanXuatField; // cho CeramicProduct

    public SuaSanPhamDialog(JFrame parent, Product sanPham) {
        super(parent, "Sửa Thông Tin Sản Phẩm", true);
        this.sanPhamHienTai = sanPham;
        initComponents();
        loadProductData();
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Các trường cơ bản
        maHangField = new JTextField(20);
        maHangField.setEditable(false); // Không cho phép sửa mã hàng
        tenHangField = new JTextField(20);
        soLuongField = new JTextField(20);
        donGiaField = new JTextField(20);

        // Thêm các components vào dialog
        int gridy = 0;

        gbc.gridx = 0;
        gbc.gridy = gridy;
        add(new JLabel("Mã hàng:"), gbc);
        gbc.gridx = 1;
        add(maHangField, gbc);

        gridy++;
        gbc.gridx = 0;
        gbc.gridy = gridy;
        add(new JLabel("Tên hàng:"), gbc);
        gbc.gridx = 1;
        add(tenHangField, gbc);

        gridy++;
        gbc.gridx = 0;
        gbc.gridy = gridy;
        add(new JLabel("Số lượng:"), gbc);
        gbc.gridx = 1;
        add(soLuongField, gbc);

        gridy++;
        gbc.gridx = 0;
        gbc.gridy = gridy;
        add(new JLabel("Đơn giá:"), gbc);
        gbc.gridx = 1;
        add(donGiaField, gbc);

        // Thêm các trường riêng theo loại sản phẩm
        if (sanPhamHienTai instanceof FoodProduct) {
            nhaCungCapField = new JTextField(20);
            gridy++;
            gbc.gridx = 0;
            gbc.gridy = gridy;
            add(new JLabel("Nhà cung cấp:"), gbc);
            gbc.gridx = 1;
            add(nhaCungCapField, gbc);
        } else if (sanPhamHienTai instanceof ElectronicsProduct) {
            thoiGianBaoHanhField = new JTextField(20);
            congSuatField = new JTextField(20);

            gridy++;
            gbc.gridx = 0;
            gbc.gridy = gridy;
            add(new JLabel("Thời gian bảo hành (tháng):"), gbc);
            gbc.gridx = 1;
            add(thoiGianBaoHanhField, gbc);

            gridy++;
            gbc.gridx = 0;
            gbc.gridy = gridy;
            add(new JLabel("Công suất (KW):"), gbc);
            gbc.gridx = 1;
            add(congSuatField, gbc);
        } else if (sanPhamHienTai instanceof CeramicProduct) {
            nhaSanXuatField = new JTextField(20);
            gridy++;
            gbc.gridx = 0;
            gbc.gridy = gridy;
            add(new JLabel("Nhà sản xuất:"), gbc);
            gbc.gridx = 1;
            add(nhaSanXuatField, gbc);
        }

        // Panel chứa nút
        JPanel buttonPanel = new JPanel();
        xacNhanButton = new JButton("Xác nhận");
        huyButton = new JButton("Hủy");

        xacNhanButton.addActionListener(e -> handleXacNhan());
        huyButton.addActionListener(e -> dispose());

        buttonPanel.add(xacNhanButton);
        buttonPanel.add(huyButton);

        gridy++;
        gbc.gridx = 0;
        gbc.gridy = gridy;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);

        pack();
        setLocationRelativeTo(null);
    }

    private void loadProductData() {
        // Load dữ liệu sản phẩm hiện tại vào form
        maHangField.setText(sanPhamHienTai.getMaHang());
        tenHangField.setText(sanPhamHienTai.getTenHang());
        soLuongField.setText(String.valueOf(sanPhamHienTai.getSoLuongTon()));
        donGiaField.setText(String.valueOf(sanPhamHienTai.getDonGia()));

        // Load dữ liệu riêng theo loại sản phẩm
        if (sanPhamHienTai instanceof FoodProduct) {
            FoodProduct food = (FoodProduct) sanPhamHienTai;
            nhaCungCapField.setText(food.getNhaCungCap());
        } else if (sanPhamHienTai instanceof ElectronicsProduct) {
            ElectronicsProduct electronics = (ElectronicsProduct) sanPhamHienTai;
            thoiGianBaoHanhField.setText(String.valueOf(electronics.getThoiGianBaoHanh()));
            congSuatField.setText(String.valueOf(electronics.getCongSuat()));
        } else if (sanPhamHienTai instanceof CeramicProduct) {
            CeramicProduct ceramic = (CeramicProduct) sanPhamHienTai;
            nhaSanXuatField.setText(ceramic.getNhaSanXuat());
        }
    }

    private void handleXacNhan() {
        try {
            // Cập nhật thông tin cơ bản
            sanPhamHienTai.setTenHang(tenHangField.getText());
            sanPhamHienTai.setSoLuongTon(Integer.parseInt(soLuongField.getText()));
            sanPhamHienTai.setDonGia(Double.parseDouble(donGiaField.getText()));

            // Cập nhật thông tin riêng theo loại
            if (sanPhamHienTai instanceof FoodProduct) {
                FoodProduct food = (FoodProduct) sanPhamHienTai;
                food.setNhaCungCap(nhaCungCapField.getText());
            } else if (sanPhamHienTai instanceof ElectronicsProduct) {
                ElectronicsProduct electronics = (ElectronicsProduct) sanPhamHienTai;
                electronics.setThoiGianBaoHanh(Integer.parseInt(thoiGianBaoHanhField.getText()));
                electronics.setCongSuat(Double.parseDouble(congSuatField.getText()));
            } else if (sanPhamHienTai instanceof CeramicProduct) {
                CeramicProduct ceramic = (CeramicProduct) sanPhamHienTai;
                ceramic.setNhaSanXuat(nhaSanXuatField.getText());
            }

            dispose();
            JOptionPane.showMessageDialog(this,
                    "Cập nhật thông tin sản phẩm thành công!",
                    "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Vui lòng nhập đúng định dạng số!",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}