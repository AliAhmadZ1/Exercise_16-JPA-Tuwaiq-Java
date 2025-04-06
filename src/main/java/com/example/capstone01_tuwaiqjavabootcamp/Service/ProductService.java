package com.example.capstone01_tuwaiqjavabootcamp.Service;

import com.example.capstone01_tuwaiqjavabootcamp.Model.Category;
import com.example.capstone01_tuwaiqjavabootcamp.Model.Product;
import com.example.capstone01_tuwaiqjavabootcamp.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final CategoryService categoryService;
    ArrayList<Product> products = new ArrayList<>();
    ArrayList<Category> categories = new ArrayList<>();


    String[] coupons = {"a12bcd", "34erd"};//1. 15% //2. 50%

    private final ProductRepository productRepository;

    public List<Product> getProduct() {
        return productRepository.findAll();
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public Boolean updateProduct(Integer id, Product product) {
        Product oldProduct = productRepository.getById(id);
        if (oldProduct == null)
            return false;

        oldProduct.setName(product.getName());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setOfferPrice(product.getOfferPrice());
        oldProduct.setWithTax(product.getWithTax());
        oldProduct.setCategory_id(product.getCategory_id());

        productRepository.save(oldProduct);
        return true;
    }

    public boolean deleteProduct(Integer id) {
        Product product = productRepository.getById(id);
        if (product == null)
            return false;

        productRepository.delete(product);
        return true;
    }

    public boolean addOffer(String productId, double percent) {
        for (Product p : products) {
            if (p.getId().equals(productId)) {
                double afterOffer = p.getPrice() - ((percent / 100) * p.getPrice());
                p.setOfferPrice(afterOffer);
                return true;
            }
        }
        return false;
    }


    //assistant extra point 3

    public boolean addTax(double tax) {
        boolean flag = false;
        for (Product p : products) {
            if (p.getWithTax() == 0) {
                if (p.getOfferPrice() > 0) {
                    double taxed = (p.getOfferPrice() * (tax / 100)) + p.getOfferPrice();
                    p.setWithTax(taxed);
                } else {
                    double taxed = (p.getPrice() * (tax / 100)) + p.getPrice();
                    p.setWithTax(taxed);
                }
                flag = true;
            }
        }
        return flag;
    }

    public boolean removeTax() {
        boolean flag = false;
        for (Product p : products) {
            if (p.getWithTax() != 0) {
                p.setWithTax(0.0);
                flag = true;
            }
        }
        return flag;
    }

    //extra point 4
    // this apply 15 or 50 percent of discount on product

    public boolean useCoupon(String productId, String coupon) {
        for (Product p : products) {
            if (p.getId().equals(productId)) {
                if (p.getOfferPrice() != 0)
                    return false;
                if (coupon.equals(coupons[0])) {
                    double discount = p.getPrice() - (p.getPrice() * (0.15));
                    p.setOfferPrice(discount);
                    return true;
                }
                if (coupon.equals(coupons[1])) {
                    double discount = p.getPrice() - (p.getPrice() * (0.5));
                    p.setOfferPrice(discount);
                    return true;
                }
            }
        }
        return false;
    }

    //extra point 5
    // show prices by order using category name

    public ArrayList<String> printByPriceWithCategory(String categoryName) {
        double max = 0;
        ArrayList<String> list = new ArrayList<>();
        for (Category c : categories) {
            for (Product p : products) {
                if (p.getCategory_id() == c.getId()) {
                    if (p.getPrice() > max) {
                        max = p.getPrice();
                        list.add(0, p.getName() + " ," + p.getPrice());
                    } else
                        list.add(p.getName() + " ," + p.getPrice());
                }
            }
        }
        return list;
    }

}


