package com.example.capstone01_tuwaiqjavabootcamp.Controller;

import com.example.capstone01_tuwaiqjavabootcamp.ApiResponse.ApiResponse;
import com.example.capstone01_tuwaiqjavabootcamp.Model.User;
import com.example.capstone01_tuwaiqjavabootcamp.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //10- Create endpoint for getting and adding and deleting updating a User.

    @GetMapping("/get")
    public ResponseEntity getUsers() {
        if (userService.getUsers().isEmpty())
            return ResponseEntity.status(400).body(new ApiResponse("There are no users"));
        return ResponseEntity.status(200).body(userService.getUsers());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("new user is created"));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        boolean stringFlag = userService.updateUser(id, user);
        if (stringFlag)
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("user is updated"));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Not Found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        if (userService.deleteUser(id))
            return ResponseEntity.status(200).body(new ApiResponse("user is deleted"));
        return ResponseEntity.status(400).body(new ApiResponse("Not Found"));
    }

// 12- Create endpoint where user can buy a product directly
//• this endpoint should accept user id, product id, merchant id.
//• check if all the given ids are valid or not
//• check if the merchant has the product in stock or return bad request.
//• reduce the stock from the MerchantStock.
//• deducted the price of the product from the user balance.
//• if balance is less than the product price returns bad request.

    @PutMapping("/buy/{userId},{productId},{merchantId}")
    public ResponseEntity buyProduct(@PathVariable String userId,@PathVariable String productId, @PathVariable String merchantId){
        String stringFlag = userService.buyProduct(userId, productId, merchantId);
        if (stringFlag.equals("user"))
            return ResponseEntity.status(400).body(new ApiResponse("user not found"));
        if (stringFlag.equals("product"))
            return ResponseEntity.status(400).body(new ApiResponse("product not found"));
        if (stringFlag.equals("merchant"))
            return ResponseEntity.status(400).body(new ApiResponse("merchant not found"));
        if (stringFlag.equals("no stock"))
            return ResponseEntity.status(400).body(new ApiResponse("the product is out of stock"));
        if (stringFlag.equals("less balance"))
            return ResponseEntity.status(400).body(new ApiResponse("you don't have enough balance to buy."));
        return ResponseEntity.status(200).body(new ApiResponse("successfully purchased"));
    }

    @GetMapping("/search-filter/{categoryName},{min},{max}")
    public ResponseEntity filterSearch(@PathVariable String categoryName, @PathVariable int min,@PathVariable int max){
        if (userService.filterSearch(categoryName, min, max).isEmpty())
            return ResponseEntity.status(400).body(new ApiResponse("Can't find results of your search"));
        return ResponseEntity.status(200).body(userService.filterSearch(categoryName, min, max));
    }

    @PutMapping("/add-tax/{userId},{tax}")
    public ResponseEntity addTax(@PathVariable String userId, @PathVariable double tax){
        if (userService.addTax(userId,tax))
            return ResponseEntity.status(200).body(new ApiResponse("Tax added"));
        return ResponseEntity.status(400).body(new ApiResponse("ERROR of: not found, role permission or already signed"));
    }

    @PutMapping("/remove-tax/{userId}")
    public ResponseEntity removeTax(@PathVariable String userId){
        if (userService.removeTax(userId))
            return ResponseEntity.status(200).body(new ApiResponse("Tax is deleted"));
        return ResponseEntity.status(400).body(new ApiResponse("ERROR of: not found, role permission or already removed"));
    }

//    @PutMapping("/return-product/{id},{productId}")
//    public ResponseEntity returnProduct(@PathVariable Integer id,@PathVariable String productId){
//        if (userService.returnProduct(id, productId).equals("user"))
//            return ResponseEntity.status(400).body(new ApiResponse("this user not exist"));
//        if (userService.returnProduct(id, productId).equals("product"))
//            return ResponseEntity.status(400).body(new ApiResponse("you've not buy this product"));
//        if (userService.returnProduct(id, productId).equals("returned"))
//            return ResponseEntity.status(200).body(new ApiResponse("the product is returned successfully"));
//        return ResponseEntity.status(400).body(new ApiResponse("ERROR"));
//    }
}





