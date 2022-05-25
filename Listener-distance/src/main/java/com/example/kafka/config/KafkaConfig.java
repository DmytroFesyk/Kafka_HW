package com.example.kafka.config;


import com.example.kafka.VehicleInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
@RequiredArgsConstructor
public class KafkaConfig {

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, VehicleInfo> distanceListenerContainerFactory(
            ConcurrentKafkaListenerContainerFactory<String, VehicleInfo> concurrentKafkaListenerContainerFactory,
            ConsumerFactory<String, VehicleInfo> consumerFactory

    ) {

        concurrentKafkaListenerContainerFactory.setConsumerFactory(consumerFactory);
        concurrentKafkaListenerContainerFactory.setCommonErrorHandler(new DefaultErrorHandler(new FixedBackOff(100, 1)));
        return concurrentKafkaListenerContainerFactory;
    }
}
