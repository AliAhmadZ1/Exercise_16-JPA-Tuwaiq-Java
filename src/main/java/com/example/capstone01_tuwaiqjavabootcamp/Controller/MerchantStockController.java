package com.example.capstone01_tuwaiqjavabootcamp.Controller;

import com.example.capstone01_tuwaiqjavabootcamp.ApiResponse.ApiResponse;
import com.example.capstone01_tuwaiqjavabootcamp.Model.MerchantStock;
import com.example.capstone01_tuwaiqjavabootcamp.Service.MerchantStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/merchantstock")
@RequiredArgsConstructor
public class MerchantStockController {


    private final MerchantStockService merchantStockService;
    //9- Create endpoint for getting and adding and deleting updating a MerchantStockStock.

    @GetMapping("/get")
    public ResponseEntity getMerchantStock() {
        if (merchantStockService.getMerchantStock().isEmpty())
            return ResponseEntity.status(400).body(new ApiResponse("there are no merchantStock"));
        return ResponseEntity.status(200).body(merchantStockService.getMerchantStock());
    }

    @PostMapping("/add")
    public ResponseEntity addMerchantStock(@RequestBody @Valid MerchantStock merchantStock, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        merchantStockService.addMerchantStock(merchantStock);
        return ResponseEntity.status(200).body(new ApiResponse("new merchantStock is added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchantStock(@PathVariable Integer id, @RequestBody @Valid MerchantStock merchantStock, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        if (merchantStockService.updateMerchantStock(id, merchantStock))
            return ResponseEntity.status(200).body(new ApiResponse("merchantStock is updated"));
        return ResponseEntity.status(400).body(new ApiResponse("not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchantStock(@PathVariable Integer id) {
        if (merchantStockService.deleteMerchantStock(id))
            return ResponseEntity.status(200).body(new ApiResponse("merchantStock is deleted"));
        return ResponseEntity.status(400).body(new ApiResponse("not found"));
    }

}
