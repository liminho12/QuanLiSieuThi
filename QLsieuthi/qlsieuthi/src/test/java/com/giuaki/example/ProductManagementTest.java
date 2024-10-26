package com.giuaki.example;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import com.giuaki.example.database.ProductDAOMemory;
import com.giuaki.example.entity.CeramicProduct;
import com.giuaki.example.entity.ElectronicsProduct;
import com.giuaki.example.entity.FoodProduct;
import com.giuaki.example.entity.Product;

public class ProductManagementTest {
        private ProductDAOMemory database;

        @Before
        public void setUp() {
                database = new ProductDAOMemory();
        }

        @Test
        public void testAddProduct() {
                // Test thêm sản phẩm thực phẩm
                FoodProduct food = new FoodProduct("F003", "Sữa tươi", 100, 15000.0,
                                new Date(), new Date(), "Vinamilk");
                assertTrue(database.addProduct(food));

                // Test thêm sản phẩm điện máy
                ElectronicsProduct electronic = new ElectronicsProduct("E003", "TV Sony",
                                5, 15000000.0, 24, 220.0);
                assertTrue(database.addProduct(electronic));

                // Test thêm sản phẩm sành sứ
                CeramicProduct ceramic = new CeramicProduct("C003", "Bình hoa",
                                20, 250000.0, "Bát Tràng", new Date());
                assertTrue(database.addProduct(ceramic));
        }

        @Test
        public void testDuplicateProduct() {
                // Create a product with a new unique ID
                FoodProduct food1 = new FoodProduct("F999", "Gạo", 100, 15000.0,
                                new Date(), new Date(), "ABC");
                assertTrue(database.addProduct(food1));

                // Try to add another product with the same ID
                FoodProduct food2 = new FoodProduct("F999", "Gạo mới", 200, 16000.0,
                                new Date(), new Date(), "XYZ");
                assertFalse(database.addProduct(food2));
        }

        @Test
        public void testSearchProduct() {
                FoodProduct food = new FoodProduct("F004", "Bánh mì", 50, 10000.0,
                                new Date(), new Date(), "ABC");
                database.addProduct(food);

                List<Product> results = database.searchProducts("Bánh");
                assertFalse(results.isEmpty());
                assertEquals("Bánh mì", results.get(0).getTenHang());
        }

        @Test
        public void testDeleteProduct() {
                FoodProduct food = new FoodProduct("F005", "Kẹo", 200, 5000.0,
                                new Date(), new Date(), "ABC");
                database.addProduct(food);

                assertTrue(database.deleteProduct("F005"));
                assertNull(database.getProductByMaHang("F005"));
        }

        @Test
        public void testUpdateProduct() {
                FoodProduct food = new FoodProduct("F006", "Cà phê", 100, 50000.0,
                                new Date(), new Date(), "ABC");
                database.addProduct(food);

                food.setSoLuongTon(150);
                assertTrue(database.updateProduct(food));
                assertEquals(150, database.getProductByMaHang("F006").getSoLuongTon());
        }

        @Test
        public void testCalculateVAT() {
                // Test VAT thực phẩm (5%)
                FoodProduct food = new FoodProduct("F007", "Mì gói", 100, 10000.0,
                                new Date(), new Date(), "ABC");
                assertEquals(500.0, food.tinhVAT(), 0.01);

                // Test VAT điện máy (10%)
                ElectronicsProduct electronic = new ElectronicsProduct("E007", "Quạt",
                                10, 500000.0, 12, 50.0);
                assertEquals(50000.0, electronic.tinhVAT(), 0.01);

                // Test VAT sành sứ (10%)
                CeramicProduct ceramic = new CeramicProduct("C007", "Chén",
                                50, 20000.0, "Bát Tràng", new Date());
                assertEquals(2000.0, ceramic.tinhVAT(), 0.01);
        }
}
