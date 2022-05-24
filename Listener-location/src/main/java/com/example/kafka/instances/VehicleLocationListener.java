package com.example.kafka.instances;

import com.example.kafka.Coordinates;
import com.example.kafka.VehicleInfo;
import com.example.kafka.VehicleLocation;
import com.example.kafka.computing.LocationConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class VehicleLocationListener {

    private final Producer producer;
    private final LocationConverter locationConverter;
    private final Map<Long, Coordinates> previousCoordinates = new HashMap<>();

    @KafkaListener(
            topics = "vehicle.location",
            groupId = "vehicle-location-converter",
            containerFactory = "locationListenerContainerFactory"
    )
    @Transactional
    public void listen(ConsumerRecord<String, VehicleLocation> consumerRecord) {
        VehicleLocation vehicleLocationEnd = consumerRecord.value();
        log.info("location was received " + vehicleLocationEnd);
        Coordinates coordinatesStart = previousCoordinates.get(vehicleLocationEnd.getCarId());
        double distance = 0.0;
        if (coordinatesStart != null) {
            distance = locationConverter.computeDistanceInMeters(
                    coordinatesStart.getLatitude(), coordinatesStart.getLongitude(),
                    vehicleLocationEnd.getCoordinates().getLatitude(), vehicleLocationEnd.getCoordinates().getLongitude()
            );
        }
        VehicleInfo vehicleInfo = new VehicleInfo(vehicleLocationEnd.getCarId(), distance);
        producer.send(vehicleInfo);
        if (vehicleLocationEnd.getCarId() == 3L) {
            log.info("transaction failed " + vehicleInfo);
            throw new RuntimeException("transaction failed");
        }
        previousCoordinates.put(vehicleLocationEnd.getCarId(), vehicleLocationEnd.getCoordinates());
    }
}
