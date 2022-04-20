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
import com.squareshift.ecommerce.dto.WarehouseResponseDto;

public interface CartService {

    CartResponseDto addItemToTheCart(CartRequest cartRequest) throws Exception;

    CartResponseDto getAllItemsInCart();

    CartResponseDto deleteAllItemsInCart(CartActionRequest cartActionRequest);

    CartResponseDto getTotalAmountInCart(Long postalCode) throws Exception;
}
