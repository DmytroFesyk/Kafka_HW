package cam.example.kafka.config;

import com.example.kafka.VehicleLocation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
@RequiredArgsConstructor
public class KafkaConfig {

    @Bean
    public KafkaTemplate<String, VehicleLocation> kafkaTemplate(ProducerFactory<String, VehicleLocation> producerFactory) {
        DefaultKafkaProducerFactory<String, VehicleLocation> factory = (DefaultKafkaProducerFactory<String, VehicleLocation>) producerFactory;
        factory.setProducerPerThread(true);
        return new KafkaTemplate<>(producerFactory);
    }
}
