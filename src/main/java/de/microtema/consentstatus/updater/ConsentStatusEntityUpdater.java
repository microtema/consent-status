package de.microtema.consentstatus.updater;

import de.microtema.consentstatus.integration.repository.ConsentStatusEntity;
import de.microtema.model.converter.Updater;
import org.springframework.stereotype.Component;

@Component
public class ConsentStatusEntityUpdater implements Updater<ConsentStatusEntity, ConsentStatusEntity> {

    @Override
    public void update(ConsentStatusEntity dest, ConsentStatusEntity orig) {

        dest.setConsentStatus(orig.getConsentStatus());
    }
}
