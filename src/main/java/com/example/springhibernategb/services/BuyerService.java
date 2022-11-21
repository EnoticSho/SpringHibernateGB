package com.example.springhibernategb.services;

import com.example.springhibernategb.entity.Buyer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BuyerService {
    public List<Buyer> getAllBuyers();
    public Buyer getBuyersById(int id);
    public void saveBuyer(Buyer buyer);
    public void deleteBuyer(int id);
}
