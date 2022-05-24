package cam.example.kafka.controller;

import cam.example.kafka.producer.Producer;
import com.example.kafka.VehicleLocation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final Producer producer;

    @PostMapping("/vehicle")
    @ResponseStatus(HttpStatus.OK)
    public void sendMessage(@RequestBody VehicleLocation vehicleLocation) {
        producer.send(vehicleLocation);
    }

}
