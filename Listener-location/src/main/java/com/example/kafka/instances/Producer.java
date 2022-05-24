package com.example.kafka.instances;

import com.example.kafka.VehicleInfo;
import com.example.kafka.VehicleLocation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class Producer {


    private final KafkaTemplate<String, VehicleInfo> kafkaTemplate;

    public void send(VehicleInfo vehicleInfo) {
        kafkaTemplate.send("vehicle.distance", vehicleInfo);
        kafkaTemplate.flush();
        log.info("location was sent " + vehicleInfo);
    }
}
