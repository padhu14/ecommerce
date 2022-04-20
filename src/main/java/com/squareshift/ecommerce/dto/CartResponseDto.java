/*
 * Copyright (C) ThermoFisher Scientific Inc.- All Rights Reserved
 * Unauthorized use or copying of this file, via any medium is strictly prohibited and will be subject to legal action.
 * Proprietary and confidential
 *
 */
package com.squareshift.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.squareshift.ecommerce.controller.request.CartRequest;

import java.util.List;

@JsonIgnoreProperties
public class CartResponseDto {
    private String status;
    private String message;
    private List<CartRequest> items;

    public CartResponseDto(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public CartResponseDto() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<CartRequest> getItems() {
        return items;
    }

    public void setItems(List<CartRequest> items) {
        this.items = items;
    }
}
