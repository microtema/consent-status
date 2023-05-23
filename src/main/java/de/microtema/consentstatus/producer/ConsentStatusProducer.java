package de.microtema.consentstatus.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.microtema.consentstatus.enums.ConsentStatus;
import de.microtema.consentstatus.event.ConsentStatusEvent;
import de.microtema.consentstatus.integration.service.ConsentStatusIntegrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ConsentStatusProducer {

    private final static Logger log = LoggerFactory.getLogger(ConsentStatusProducer.class);

    @Value("${integration.kafka.consumer.topics:consent-status-topic}")
    private String topicName = "consent-status-topic";

    private final ObjectMapper objectMapper;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public ConsentStatusProducer(ObjectMapper objectMapper,
                                 KafkaTemplate<String, String> kafkaTemplate) {
        this.objectMapper = objectMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void execute() {

        for (int index = 0; index < 100; index++) {

            var statusEvent = new ConsentStatusEvent();

            statusEvent.setUuid(UUID.randomUUID().toString());
            statusEvent.setConsentStatus(ConsentStatus.random());

            var event = writeValueAsString(statusEvent);

            kafkaTemplate.send(topicName, event);
        }
    }

    private String writeValueAsString(Object object) {

        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("Unable to write Value As String!", e);

            throw new IllegalStateException("Unable to write Value As String!", e);
        }
    }
}
