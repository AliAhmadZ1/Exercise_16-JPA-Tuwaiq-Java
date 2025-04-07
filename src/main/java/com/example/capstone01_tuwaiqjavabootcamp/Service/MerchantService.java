package com.example.capstone01_tuwaiqjavabootcamp.Service;

import com.example.capstone01_tuwaiqjavabootcamp.Model.Merchant;
import com.example.capstone01_tuwaiqjavabootcamp.Model.MerchantStock;
import com.example.capstone01_tuwaiqjavabootcamp.Model.Product;
import com.example.capstone01_tuwaiqjavabootcamp.Model.User;
import com.example.capstone01_tuwaiqjavabootcamp.Repository.MerchantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantService {

    private final ProductService productService;
    private final MerchantStockService merchantStockService;
    ArrayList<Merchant> merchants = new ArrayList<>();
    ArrayList<User> buyers = new ArrayList<>();
    ArrayList<Product> purchased = new ArrayList<>();

    private final MerchantRepository merchantRepository;

    public List<Merchant> getMerchant() {
        return merchantRepository.findAll();
    }

    public void addMerchant(Merchant merchant) {
        merchantRepository.save(merchant);
    }

    public boolean updateMerchant(Integer id, Merchant merchant) {
        Merchant oldMerchant = merchantRepository.getById(id);
        if (oldMerchant==null)
            return false;

        oldMerchant.setName(merchant.getName());

        merchantRepository.save(oldMerchant);
        return true;
    }

    public boolean deleteMerchant(Integer id) {
        Merchant oldMerchant = merchantRepository.getById(id);
        if (oldMerchant==null)
            return false;

        merchantRepository.delete(oldMerchant);
        return true;
    }

    //extra point 2
    // merchant can add offer on his products
    public String addProductStock(String productId, String merchantId, int stockAmount) {
        for (Merchant m : merchants) {
            if (m.getId().equals(merchantId)) {
                for (Product p : productService.getProduct()) {
                    if (p.getId().equals(productId)) {
                        for (MerchantStock ms : merchantStockService.getMerchantStock()) {
                            if (ms.getMerchant_id().equals(merchantId) && ms.getProduct_id().equals(productId)) {
                                ms.setStock(ms.getStock() + stockAmount);
                                return "stocked";
                            }
                        }
                    }
                }
                return "product";
            }
        }
        return "";
    }


    public boolean addProductOffer(Integer merchantId, double percent) {
        boolean addOffer = false;
        for (Merchant m : merchants) {
            if (m.getId().equals(merchantId)) {
                for (MerchantStock ms : merchantStockService.getMerchantStock()) {
                    if (m.getId().equals(ms.getMerchant_id())) {
                        addOffer = productService.addOffer(ms.getProduct_id(), percent);
                    }
                }
            }
        }
        return addOffer;
    }

    public void addBuyer(User user,Product product){
        buyers.add(user);
        purchased.add(product);
    }

    public boolean returnProduct(String userId,String productId){
        for (User u: buyers) {
            if (u.getId().equals(userId)) {
                for (Product p : purchased) {
                    if (p.getId().equals(productId)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
