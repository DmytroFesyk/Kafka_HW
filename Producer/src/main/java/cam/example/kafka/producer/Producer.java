package cam.example.kafka.producer;

import com.example.kafka.VehicleLocation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class Producer {


    private final KafkaTemplate<String,VehicleLocation> kafkaTemplate;

    public void send(VehicleLocation vehicleLocation) {
        kafkaTemplate.send("vehicle.location", vehicleLocation);
        kafkaTemplate.flush();
        log.info("location was sent " + vehicleLocation);
    }
}
