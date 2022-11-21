package com.example.springhibernategb.DAO;

import com.example.springhibernategb.entity.Product;

import java.util.List;

public interface DAOProduct {
    public List<Product> getAllProducts();
    public Product getProductById(int id);
    public void saveProduct(Product product);
    public void deleteProduct(int id);
}
