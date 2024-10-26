package com.giuaki.example.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
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

public class ThemSanPhamDialog extends JDialog {
    private JPanel mainPanel;
    private JPanel additionalPanel;
    private JTextField maHangField;
    private JTextField tenHangField;
    private JTextField soLuongField;
    private JTextField donGiaField;
    private JComboBox<String> loaiSPCombo;

    // Fields for FoodProduct
    private JTextField nsxField;
    private JTextField hsdField;
    private JTextField nccField;

    // Fields for ElectronicsProduct
    private JTextField baoHanhField;
    private JTextField congSuatField;

    // Fields for CeramicProduct
    private JTextField nhaSXField;
    private JTextField ngayNhapField;

    private ProductManagementForm parentForm;

    public ThemSanPhamDialog(JFrame parent) {
        super(parent, "Thêm Sản Phẩm", true);
        this.parentForm = (ProductManagementForm) parent;
        initComponents();
    }

    private void initComponents() {
        mainPanel = new JPanel(new BorderLayout(5, 5));

        // Basic info panel
        JPanel basicPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        String[] loaiSP = { "Thực phẩm", "Điện máy", "Sành sứ" };
        loaiSPCombo = new JComboBox<>(loaiSP);
        maHangField = new JTextField(20);
        tenHangField = new JTextField(20);
        soLuongField = new JTextField(20);
        donGiaField = new JTextField(20);

        basicPanel.add(new JLabel("Loại sản phẩm:"));
        basicPanel.add(loaiSPCombo);
        basicPanel.add(new JLabel("Mã hàng:"));
        basicPanel.add(maHangField);
        basicPanel.add(new JLabel("Tên hàng:"));
        basicPanel.add(tenHangField);
        basicPanel.add(new JLabel("Số lượng:"));
        basicPanel.add(soLuongField);
        basicPanel.add(new JLabel("Đơn giá:"));
        basicPanel.add(donGiaField);

        // Additional fields panel
        additionalPanel = new JPanel(new GridLayout(3, 2, 5, 5));

        // Button panel
        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("Xác nhận");
        JButton cancelButton = new JButton("Hủy");
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        mainPanel.add(basicPanel, BorderLayout.NORTH);
        mainPanel.add(additionalPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add listeners
        loaiSPCombo.addActionListener(e -> updateAdditionalFields());
        okButton.addActionListener(e -> handleThem());
        cancelButton.addActionListener(e -> dispose());

        add(mainPanel);
        updateAdditionalFields();
        pack();
        setLocationRelativeTo(null);
    }

    private void updateAdditionalFields() {
        additionalPanel.removeAll();
        String selectedType = (String) loaiSPCombo.getSelectedItem();

        switch (selectedType) {
            case "Thực phẩm":
                nsxField = new JTextField(20);
                hsdField = new JTextField(20);
                nccField = new JTextField(20);
                additionalPanel.add(new JLabel("Ngày sản xuất:"));
                additionalPanel.add(nsxField);
                additionalPanel.add(new JLabel("Hạn sử dụng:"));
                additionalPanel.add(hsdField);
                additionalPanel.add(new JLabel("Nhà cung cấp:"));
                additionalPanel.add(nccField);
                break;

            case "Điện máy":
                baoHanhField = new JTextField(20);
                congSuatField = new JTextField(20);
                additionalPanel.add(new JLabel("Thời gian bảo hành (tháng):"));
                additionalPanel.add(baoHanhField);
                additionalPanel.add(new JLabel("Công suất (KW):"));
                additionalPanel.add(congSuatField);
                break;

            case "Sành sứ":
                nhaSXField = new JTextField(20);
                ngayNhapField = new JTextField(20);
                additionalPanel.add(new JLabel("Nhà sản xuất:"));
                additionalPanel.add(nhaSXField);
                additionalPanel.add(new JLabel("Ngày nhập kho:"));
                additionalPanel.add(ngayNhapField);
                break;
        }

        pack();
        additionalPanel.revalidate();
        additionalPanel.repaint();
    }

    private void handleThem() {
        try {
            String maHang = maHangField.getText();
            String tenHang = tenHangField.getText();
            int soLuong = Integer.parseInt(soLuongField.getText());
            double donGia = Double.parseDouble(donGiaField.getText());
            String loaiSP = (String) loaiSPCombo.getSelectedItem();

            Product newProduct;
            switch (loaiSP) {
                case "Thực phẩm":
                    newProduct = new FoodProduct(maHang, tenHang, soLuong, donGia,
                            new Date(), new Date(), nccField.getText());
                    break;

                case "Điện máy":
                    int baoHanh = Integer.parseInt(baoHanhField.getText());
                    double congSuat = Double.parseDouble(congSuatField.getText());
                    newProduct = new ElectronicsProduct(maHang, tenHang, soLuong,
                            donGia, baoHanh, congSuat);
                    break;

                default: // Sành sứ
                    newProduct = new CeramicProduct(maHang, tenHang, soLuong,
                            donGia, nhaSXField.getText(), new Date());
                    break;
            }

            if (parentForm.getDatabase().addProduct(newProduct)) {
                JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công!");
                parentForm.refreshData();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Mã hàng đã tồn tại!");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng số!");
        }
    }
}
