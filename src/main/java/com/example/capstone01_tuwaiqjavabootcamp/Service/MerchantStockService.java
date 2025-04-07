package com.example.capstone01_tuwaiqjavabootcamp.Service;

import com.example.capstone01_tuwaiqjavabootcamp.Model.Merchant;
import com.example.capstone01_tuwaiqjavabootcamp.Model.MerchantStock;
import com.example.capstone01_tuwaiqjavabootcamp.Model.Product;
import com.example.capstone01_tuwaiqjavabootcamp.Repository.MerchantStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantStockService {

    ArrayList<MerchantStock> merchantStocks = new ArrayList<>();

    private final MerchantStockRepository merchantStockRepository;


    public List<MerchantStock> getMerchantStock() {
        return merchantStockRepository.findAll();
    }

    public void addMerchantStock(MerchantStock merchantStock) {
        merchantStockRepository.save(merchantStock);
    }

    public Boolean updateMerchantStock(Integer id, MerchantStock merchantStock) {
        MerchantStock oldMerchantStock = merchantStockRepository.getById(id);
        if (oldMerchantStock == null)
            return false;

        oldMerchantStock.setMerchant_id(merchantStock.getMerchant_id());
        oldMerchantStock.setProduct_id(merchantStock.getProduct_id());
        oldMerchantStock.setStock(merchantStock.getStock());

        merchantStockRepository.save(oldMerchantStock);
        return true;
    }

    public Boolean deleteMerchantStock(Integer id) {
        MerchantStock merchantStock = merchantStockRepository.getById(id);

        if (merchantStock==null)
            return false;

        merchantStockRepository.delete(merchantStock);
        return true;
    }

    public void updateProductStock(Integer productId) {
        for (MerchantStock ms : merchantStockRepository.findAll()) {
            if (ms.getProduct_id().equals(productId)) {
                ms.setStock(ms.getStock() + 1);
            }
        }
    }

}
