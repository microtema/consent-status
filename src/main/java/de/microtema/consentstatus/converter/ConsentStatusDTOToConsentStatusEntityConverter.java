package de.microtema.consentstatus.converter;

import de.microtema.consentstatus.integration.repository.ConsentStatusEntity;
import de.microtema.consentstatus.event.ConsentStatusEvent;
import de.microtema.model.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ConsentStatusDTOToConsentStatusEntityConverter implements Converter<ConsentStatusEntity, ConsentStatusEvent> {
}
