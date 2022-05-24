package com.example.kafka.config;

import com.example.kafka.VehicleLocation;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class TopicConfig {

    @Bean
    public NewTopic vehicleLocationTopic()  {
        return TopicBuilder
                .name("vehicle.location")
                .partitions(3)
                .replicas(2)
                .build();
    }

    @Bean
    public NewTopic vehicleDistanceTopic() {
        return TopicBuilder
                .name("vehicle.distance")
                .partitions(3)
                .replicas(2)
                .build();
    }

}
