/*
 * Copyright (C) ThermoFisher Scientific Inc.- All Rights Reserved
 * Unauthorized use or copying of this file, via any medium is strictly prohibited and will be subject to legal action.
 * Proprietary and confidential
 *
 */
package com.squareshift.ecommerce.service;

import com.squareshift.ecommerce.controller.request.CartActionRequest;
import com.squareshift.ecommerce.controller.request.CartRequest;
import com.squareshift.ecommerce.dto.CartResponseDto;
import com.squareshift.ecommerce.dto.ProductDto;
import com.squareshift.ecommerce.dto.WarehouseResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    private static final Map<ProductDto, Long> productDtos = new LinkedHashMap<>();

    @Autowired
    private ProductService productService;

    @Autowired
    private WareHouseService wareHouseService;

    @Override
    public CartResponseDto addItemToTheCart(CartRequest cartRequest) throws Exception {
        CartResponseDto cartResponseDto = new CartResponseDto();
        ProductDto productDto = productService.getProductById(cartRequest.getProductId());
        if (productDto == null) {
            throw new IllegalArgumentException("Invalid product id");
        }
        productDtos.put(productDto, cartRequest.getQuantity());
        cartResponseDto.setStatus("success");
        cartResponseDto.setMessage("Item has been added to cart");

        return cartResponseDto;
    }

    @Override
    public CartResponseDto getAllItemsInCart() {
        CartResponseDto cartResponseDto = new CartResponseDto();
        if (productDtos.isEmpty()) {
            throw new IllegalArgumentException("Cart is empty.");
        }
        cartResponseDto.setStatus("success");
        cartResponseDto.setItems(productDtos.entrySet().stream().map(productDto -> new CartRequest(productDto.getKey().getId().longValue(), productDto.getKey().getDescription(), productDto.getValue())).collect(Collectors.toList()));

        return cartResponseDto;
    }

    @Override
    public CartResponseDto deleteAllItemsInCart(CartActionRequest cartActionRequest) {
        if (cartActionRequest != null && "empty_cart".equalsIgnoreCase(cartActionRequest.getAction())) {
            throw new IllegalArgumentException("Wrong Action");
        }
        if (productDtos.isEmpty()) {
            throw new IllegalArgumentException("No Items in the Cart!!!");
        }
        productDtos.clear();
        return new CartResponseDto("success", "All items have been removed from the cart !");
    }

    @Override
    public CartResponseDto getTotalAmountInCart(Long postalCode) throws Exception {
        WarehouseResponseDto warehouseResponseDto = wareHouseService.getWareHouseDistanceByPostalCode(postalCode);
        if (warehouseResponseDto == null) {
            throw new IllegalArgumentException("Invalid postal code, valid ones are 465535 to 465545");
        }
        if (productDtos.isEmpty()) {
            throw new IllegalArgumentException("No Items in the Cart!!!");
        }

        long totalValue = 0;
        for (Map.Entry<ProductDto, Long> entry : productDtos.entrySet()) {
            Long count = entry.getValue();
            double weight = entry.getKey().getWeight_in_grams() * count;
            Long dist = warehouseResponseDto.getDistance_in_kilometers();

            long amount = 0;
            if (weight < 2) {
                if (dist < 5) {
                    amount = 12;
                } else if (dist < 20) {
                    amount = 15;
                } else if (dist < 50) {
                    amount = 20;
                } else if (dist < 500) {
                    amount = 50;
                } else if (dist < 800) {
                    amount = 100;
                } else if (dist > 800) {
                    amount = 220;
                }
            } else if (weight > 2 && weight < 5) {
                if (dist < 5) {
                    amount = 14;
                } else if (dist < 20) {
                    amount = 18;
                } else if (dist < 50) {
                    amount = 24;
                } else if (dist < 500) {
                    amount = 55;
                } else if (dist < 800) {
                    amount = 110;
                } else if (dist > 800) {
                    amount = 250;
                }
            } else if (weight > 5 && weight < 20) {
                if (dist < 5) {
                    amount = 16;
                } else if (dist < 20) {
                    amount = 25;
                } else if (dist < 50) {
                    amount = 30;
                } else if (dist < 500) {
                    amount = 80;
                } else if (dist < 800) {
                    amount = 120;
                } else if (dist > 800) {
                    amount = 270;
                }
            } else {
                if (dist < 5) {
                    amount = 21;
                } else if (dist < 20) {
                    amount = 35;
                } else if (dist < 50) {
                    amount = 50;
                } else if (dist < 500) {
                    amount = 90;
                } else if (dist < 800) {
                    amount = 150;
                } else if (dist > 800) {
                    amount = 300;
                }
            }

            totalValue = totalValue + amount;
        }

        return new CartResponseDto("success", "Total value of your shopping cart is - $" + totalValue);
    }


}
