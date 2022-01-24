/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.flooringmastery.dao;

import com.raveenm.flooringmastery.dto.Material;
import java.util.List;

/**
 *
 * @author ravee
 */
public interface FlooringMasteryMaterialsDao {
    List<Material> getallProductTypes();
    Material getProductType(String productType);
    Material addProduct(Material product);
}
