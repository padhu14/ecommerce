package com.squareshift.ecommerce.dto;

import lombok.Data;

@Data
public class WarehouseResponseDto {
    private String status;
    private Long distance_in_kilometers;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getDistance_in_kilometers() {
        return distance_in_kilometers;
    }

    public void setDistance_in_kilometers(Long distance_in_kilometers) {
        this.distance_in_kilometers = distance_in_kilometers;
    }
}
