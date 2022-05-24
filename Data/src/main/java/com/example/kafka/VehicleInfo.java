package com.example.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VehicleInfo {
    private long carId;
    private double distanceKm;
}
