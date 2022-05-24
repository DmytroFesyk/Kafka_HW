package com.example.kafka;

import lombok.Data;

@Data
public class VehicleLocation {
    private long carId;
    private Coordinates coordinates;
}
