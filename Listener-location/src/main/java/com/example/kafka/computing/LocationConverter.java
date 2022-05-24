package com.example.kafka.computing;

import org.springframework.stereotype.Service;

@Service
public class LocationConverter {
    public double computeDistanceInMeters(
            double latitudeStart, double longitudeStart,
            double latitudeEnd, double longitudeEnd
    ) {
        int radius = 6371;
        double latitudeDistance = Math.toRadians(latitudeEnd - latitudeStart);
        double longitudeDistance = Math.toRadians(longitudeEnd - longitudeStart);
        double a = (Math.sin(latitudeDistance / 2) * Math.sin(latitudeDistance / 2)
                + (Math.cos(Math.toRadians(latitudeStart)) * Math.cos(Math.toRadians(longitudeEnd))
                * Math.sin(longitudeDistance / 2) * Math.sin(longitudeDistance / 2)));
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return Math.sqrt(radius * c * 1000);
    }
}
