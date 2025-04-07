package com.example.capstone01_tuwaiqjavabootcamp.Controller;

import com.example.capstone01_tuwaiqjavabootcamp.ApiResponse.ApiResponse;
import com.example.capstone01_tuwaiqjavabootcamp.Model.Merchant;
import com.example.capstone01_tuwaiqjavabootcamp.Service.MerchantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/merchant")
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;

    //8- Create endpoint for getting and adding and deleting updating a Merchant.

    @GetMapping("/get")
    public ResponseEntity getMerchant() {
        if (merchantService.getMerchant().isEmpty())
            return ResponseEntity.status(400).body(new ApiResponse("there are no merchant"));
        return ResponseEntity.status(200).body(merchantService.getMerchant());
    }

    @PostMapping("/add")
    public ResponseEntity addMerchant(@RequestBody @Valid Merchant merchant, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }

        merchantService.addMerchant(merchant);
        return ResponseEntity.status(200).body(new ApiResponse("new merchant is added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchant(@PathVariable Integer id, @RequestBody @Valid Merchant merchant, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        if (merchantService.updateMerchant(id, merchant))
            return ResponseEntity.status(200).body(new ApiResponse("merchant is updated"));
        return ResponseEntity.status(400).body(new ApiResponse("not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchant(@PathVariable Integer id) {
        if (merchantService.deleteMerchant(id))
            return ResponseEntity.status(200).body(new ApiResponse("merchant is deleted"));
        return ResponseEntity.status(400).body(new ApiResponse("not found"));
    }

    //11- Create endpoint where merchant can add more stocks of product to a merchantStock
    //• this endpoint should accept a product id and merchant id and the amount of additional stock.
    @PutMapping("/add-product-stock/{productId},{merchantId},{stockAmount}")
    public ResponseEntity addProductStock(@PathVariable String productId, @PathVariable String merchantId, @PathVariable int stockAmount) {
        if (stockAmount>0) {
            if (merchantService.addProductStock(productId, merchantId, stockAmount).equals("stocked"))
                return ResponseEntity.status(200).body(new ApiResponse("stock of product is added"));
            if (merchantService.addProductStock(productId, merchantId, stockAmount).equals("product"))
                return ResponseEntity.status(400).body(new ApiResponse("product not found"));
            return ResponseEntity.status(400).body(new ApiResponse("merchant not found"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("stock amount cannot be negative or zero"));
    }

    //extra point 2
    // merchant can add offer on his products
    @PutMapping("/add-offer/{merchantId},{percent}")
    public ResponseEntity addProductOffer(@PathVariable Integer merchantId,@PathVariable double percent){
        if (merchantService.addProductOffer(merchantId, percent))
            return ResponseEntity.status(200).body(new ApiResponse("offer is applied"));
        return ResponseEntity.status(400).body(new ApiResponse("merchant doesn't exist or he doesn't have products"));
    }

}
