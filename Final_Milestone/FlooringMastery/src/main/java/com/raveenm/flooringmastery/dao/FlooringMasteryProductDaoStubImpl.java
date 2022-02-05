/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.flooringmastery.dao;

import com.raveenm.flooringmastery.dto.Product;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ravee
 */
public class FlooringMasteryProductDaoStubImpl implements FlooringMasteryProductDao {

    Product productCarpet = new Product("Carpet", new BigDecimal("2.25").setScale(2, RoundingMode.HALF_UP), new BigDecimal("2.10").setScale(2, RoundingMode.HALF_UP));
    Product productLaminate = new Product("Laminate", new BigDecimal("1.75").setScale(2, RoundingMode.HALF_UP), new BigDecimal("2.10").setScale(2, RoundingMode.HALF_UP));
    Product productTile = new Product("Tile", new BigDecimal("3.50").setScale(2, RoundingMode.HALF_UP), new BigDecimal("4.15").setScale(2, RoundingMode.HALF_UP));
    Product productWood = new Product("Wood", new BigDecimal("5.15").setScale(2, RoundingMode.HALF_UP), new BigDecimal("4.75").setScale(2, RoundingMode.HALF_UP));

    @Override
    public List<Product> getallProductTypes() throws OrderPersistenceException {
        List<Product> allProducts = new ArrayList<>();
        allProducts.add(productTile);
        allProducts.add(productCarpet);
        allProducts.add(productWood);
        allProducts.add(productLaminate);

        return allProducts;

    }

    @Override
    public Product getProduct(String productType) throws OrderPersistenceException {
        if (productType == productCarpet.getProductType()) {
            return productCarpet;
        } else if (productType == productTile.getProductType()) {
            return productTile;

        } else if (productType == productLaminate.getProductType()) {
            return productLaminate;
        } else if (productType == productWood.getProductType()) {
            return productWood;
        }
        return null;
    }
}
