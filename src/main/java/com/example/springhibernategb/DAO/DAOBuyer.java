package com.example.springhibernategb.DAO;

import com.example.springhibernategb.entity.Buyer;

import java.util.List;

public interface DAOBuyer {
    public List<Buyer> getAllBuyers();
    public Buyer getBuyersById(int id);
    public void saveBuyer(Buyer buyer);
    public void deleteBuyer(int id);
}
