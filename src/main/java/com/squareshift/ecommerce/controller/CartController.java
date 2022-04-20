/*
 * Copyright (C) ThermoFisher Scientific Inc.- All Rights Reserved
 * Unauthorized use or copying of this file, via any medium is strictly prohibited and will be subject to legal action.
 * Proprietary and confidential
 *
 */
package com.squareshift.ecommerce.controller;

import com.squareshift.ecommerce.controller.request.CartActionRequest;
import com.squareshift.ecommerce.controller.request.CartRequest;
import com.squareshift.ecommerce.dto.CartResponseDto;
import com.squareshift.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;


    @PostMapping(value = "/v1/cart/item", produces = MediaType.APPLICATION_JSON_VALUE)
    public CartResponseDto addItemToCart(@RequestBody CartRequest cartRequest) {
        CartResponseDto cartResponseDto = new CartResponseDto();
        try {
            Assert.notNull(cartRequest, "Cart Request cannot be null");
            Assert.notNull(cartRequest.getProductId(), "Product Id cannot be null");
            Assert.isTrue((cartRequest.getQuantity() != null || cartRequest.getQuantity().intValue() == 0), "Quantity cannot be null or Zero");

            cartResponseDto = cartService.addItemToTheCart(cartRequest);
        } catch (Exception e) {
            cartResponseDto.setStatus("error");
            cartResponseDto.setMessage(e.getMessage());
        }

        return cartResponseDto;
    }

    @GetMapping(value = "/v1/cart/items", produces = MediaType.APPLICATION_JSON_VALUE)
    public CartResponseDto getAllItemsInCart() {
        CartResponseDto cartResponseDto = new CartResponseDto();
        try {
            cartResponseDto = cartService.getAllItemsInCart();
        } catch (Exception e) {
            cartResponseDto.setStatus("error");
            cartResponseDto.setMessage(e.getMessage());
        }

        return cartResponseDto;
    }

    @PostMapping(value = "/v1/cart/items", produces = MediaType.APPLICATION_JSON_VALUE)
    public CartResponseDto deleteAllItemsInCart(@RequestBody CartActionRequest cartActionRequest) {
        CartResponseDto cartResponseDto = new CartResponseDto();
        try {

            cartResponseDto = cartService.deleteAllItemsInCart(cartActionRequest);
        } catch (Exception e) {
            cartResponseDto.setStatus("error");
            cartResponseDto.setMessage(e.getMessage());
        }

        return cartResponseDto;
    }

    @GetMapping(value = "/v1/cart/checkout-value", produces = MediaType.APPLICATION_JSON_VALUE)
    public CartResponseDto getTotalAmountInCart(@RequestParam("shipping_postal_code") Long postalCode) {
        CartResponseDto cartResponseDto = new CartResponseDto();
        try {
            if (postalCode > 465534 && postalCode < 465546) {
                throw new IllegalArgumentException("Invalid postal code, valid ones are 465535 to 465545");
            }
            cartResponseDto = cartService.getTotalAmountInCart(postalCode);
        } catch (Exception e) {
            cartResponseDto.setStatus("error");
            cartResponseDto.setMessage(e.getMessage());
        }

        return cartResponseDto;
    }

}
