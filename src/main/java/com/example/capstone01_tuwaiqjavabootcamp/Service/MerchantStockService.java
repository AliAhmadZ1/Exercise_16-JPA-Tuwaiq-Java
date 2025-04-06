package com.example.capstone01_tuwaiqjavabootcamp.Service;

import com.example.capstone01_tuwaiqjavabootcamp.Model.Merchant;
import com.example.capstone01_tuwaiqjavabootcamp.Model.MerchantStock;
import com.example.capstone01_tuwaiqjavabootcamp.Model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MerchantStockService {

    ArrayList<MerchantStock> merchantStocks = new ArrayList<>();


    public ArrayList<MerchantStock> getMerchantStock() {
        return merchantStocks;
    }

    public boolean addMerchantStock(MerchantStock merchantStock) {
        for (MerchantStock m : merchantStocks) {
            if (m.getId().equals(merchantStock.getId()))
                return false;
        }
        merchantStocks.add(merchantStock);
        return true;
    }

    public boolean updateMerchantStock(String id, MerchantStock merchantStock) {
        for (MerchantStock m : merchantStocks) {
            if (m.getId().equals(id)) {
                merchantStocks.set(merchantStocks.indexOf(m), merchantStock);
                return true;
            }
        }
        return false;
    }

    public boolean deleteMerchantStock(String id) {
        for (MerchantStock m : merchantStocks) {
            if (m.getId().equals(id)) {
                merchantStocks.remove(m);
                return true;
            }
        }
        return false;
    }

    public void updateProductStock(String productId) {
        for (MerchantStock ms : merchantStocks) {
            if (ms.getProductId().equals(productId)) {
                ms.setStock(ms.getStock()+1);
            }
        }
    }

}
