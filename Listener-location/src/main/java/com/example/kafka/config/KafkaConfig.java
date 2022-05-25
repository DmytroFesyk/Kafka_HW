package com.example.kafka.config;

import com.example.kafka.VehicleLocation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.DefaultAfterRollbackProcessor;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
@RequiredArgsConstructor
public class KafkaConfig {

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, VehicleLocation> locationListenerContainerFactory(
            ConcurrentKafkaListenerContainerFactory<String, VehicleLocation> concurrentKafkaListenerContainerFactory,
            ConsumerFactory<String, VehicleLocation> consumerFactory

    ) {

        concurrentKafkaListenerContainerFactory.setConsumerFactory(consumerFactory);
        concurrentKafkaListenerContainerFactory.getContainerProperties().setEosMode(ContainerProperties.EOSMode.V2);
        concurrentKafkaListenerContainerFactory.setBatchListener(false);
        concurrentKafkaListenerContainerFactory.setCommonErrorHandler(new DefaultErrorHandler(new FixedBackOff(100, 1)));
        concurrentKafkaListenerContainerFactory.setAfterRollbackProcessor(new DefaultAfterRollbackProcessor<>(new FixedBackOff(0, 0)));
        return concurrentKafkaListenerContainerFactory;
    }
}
