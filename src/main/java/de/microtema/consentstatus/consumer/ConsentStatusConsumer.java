package de.microtema.consentstatus.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.microtema.consentstatus.converter.ConsentStatusDTOToConsentStatusEntityConverter;
import de.microtema.consentstatus.event.ConsentStatusEvent;
import de.microtema.consentstatus.integration.service.ConsentStatusIntegrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsentStatusConsumer {

    private final static Logger log = LoggerFactory.getLogger(ConsentStatusConsumer.class);

    private final ObjectMapper objectMapper;

    private final ConsentStatusIntegrationService consentStatusIntegrationService;
    private final ConsentStatusDTOToConsentStatusEntityConverter consentStatusEntityConverter;

    public ConsentStatusConsumer(ObjectMapper objectMapper,
                                 ConsentStatusIntegrationService consentStatusIntegrationService,
                                 ConsentStatusDTOToConsentStatusEntityConverter consentStatusEntityConverter) {
        this.objectMapper = objectMapper;
        this.consentStatusIntegrationService = consentStatusIntegrationService;
        this.consentStatusEntityConverter = consentStatusEntityConverter;
    }

    @KafkaListener(topics = "consent-status-topic", groupId = "consent-status-group")
    public void processConsentStatus(String event) {

        var ConsentStatusEvent = readValue(event);

        log.info("ConsentStatus Event received {}", ConsentStatusEvent.getUuid());

        var consentStatusEntity = consentStatusEntityConverter.convert(ConsentStatusEvent);

        consentStatusIntegrationService.saveConsentStatus(consentStatusEntity);
    }

    private ConsentStatusEvent readValue(String event) {

        try {
            return objectMapper.readValue(event, ConsentStatusEvent.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}