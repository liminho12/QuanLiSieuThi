package com.giuaki.example.usecase;

import com.giuaki.example.entity.FoodProduct;
import com.giuaki.example.entity.Product;

public class VatCalculator {
    public static double tinhVAT(Product product) {
        if (product instanceof FoodProduct) {
            return product.getDonGia() * 0.05; // 5% cho thực phẩm
        } else {
            return product.getDonGia() * 0.1; // 10% cho điện máy và sành sứ
        }
    }
}
