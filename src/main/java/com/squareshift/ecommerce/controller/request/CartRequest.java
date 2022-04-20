/*
 * Copyright (C) ThermoFisher Scientific Inc.- All Rights Reserved
 * Unauthorized use or copying of this file, via any medium is strictly prohibited and will be subject to legal action.
 * Proprietary and confidential
 *
 */
package com.squareshift.ecommerce.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;


public class CartRequest {
    @JsonProperty("product_id")
    private Long productId;
    private String description;
    private Long quantity;

    public CartRequest(Long productId, String description, Long quantity) {
        this.productId = productId;
        this.description = description;
        this.quantity = quantity;
    }

    public CartRequest(){}

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartRequest)) return false;
        CartRequest that = (CartRequest) o;
        return Objects.equals(productId, that.productId) && Objects.equals(description, that.description) && Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, description, quantity);
    }

    @Override
    public String toString() {
        return "CartRequest{" +
                "productId='" + productId + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}

