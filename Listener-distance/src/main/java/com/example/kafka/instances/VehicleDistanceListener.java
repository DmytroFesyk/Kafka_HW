package com.example.kafka.instances;

import com.example.kafka.VehicleInfo;
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
public class VehicleDistanceListener {

    private final Map<Long, Double> distances = new HashMap<>();

    @KafkaListener(
            topics = "vehicle.distance",
            groupId = "vehicle-distance-converter",
            containerFactory = "distanceListenerContainerFactory"
    )
    @Transactional
    public void listen(ConsumerRecord<String, VehicleInfo> consumerRecord) {
        VehicleInfo vehicleInfo = consumerRecord.value();
        double oldDistance = 0.0;
        if (distances.get(vehicleInfo.getCarId()) != null) {
            oldDistance = distances.get(vehicleInfo.getCarId());
        }
        double newDistance = oldDistance + vehicleInfo.getDistanceMeters();
        log.info("Сar " + vehicleInfo.getCarId() + "distance,m: before[" + oldDistance + "] current [" + vehicleInfo.getDistanceMeters() + "] after [" + newDistance + "]");
        distances.put(vehicleInfo.getCarId(), newDistance);
    }
}
