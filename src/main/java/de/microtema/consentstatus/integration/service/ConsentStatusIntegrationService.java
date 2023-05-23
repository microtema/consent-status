package de.microtema.consentstatus.integration.service;

import de.microtema.consentstatus.enums.ConsentStatus;
import de.microtema.consentstatus.integration.repository.ConsentStatusEntity;
import de.microtema.consentstatus.integration.repository.ConsentStatusRepository;
import de.microtema.consentstatus.updater.ConsentStatusEntityUpdater;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsentStatusIntegrationService {

    private final static Logger log = LoggerFactory.getLogger(ConsentStatusIntegrationService.class);

    private final ConsentStatusRepository repository;
    private final ConsentStatusEntityUpdater consentStatusEntityUpdater;

    public ConsentStatusIntegrationService(ConsentStatusRepository repository,
                                           ConsentStatusEntityUpdater consentStatusEntityUpdater) {
        this.repository = repository;
        this.consentStatusEntityUpdater = consentStatusEntityUpdater;
    }

    public ConsentStatus getConsentStatus(String customerId) {

        return repository.getReferenceById(customerId).getConsentStatus();
    }

    public void updateConsentStatus(String customerId, ConsentStatus consentStatus) {

        var consentStatusEntity = repository.getReferenceById(customerId);

        log.info("Update consentStatus {} from {} -> {}", customerId, consentStatusEntity.getConsentStatus(), consentStatusEntity);

        consentStatusEntity.setConsentStatus(consentStatus);

        repository.save(consentStatusEntity);
    }

    public List<ConsentStatusEntity> getConsentStatusList(int pageNumber, int pageSize) {

        return repository.findAll(Pageable.ofSize(pageSize).withPage(pageNumber)).getContent();
    }

    public void saveConsentStatus(ConsentStatusEntity consentStatusEntity) {

        var consentStatusEntityOptional = repository.findById(consentStatusEntity.getUuid());

        if (consentStatusEntityOptional.isPresent()) {
            updateConsentStatus(consentStatusEntityOptional.get(), consentStatusEntity);
        } else {
            repository.save(consentStatusEntity);
            log.info("Create ConsentStatus {} within status {}", consentStatusEntity.getUuid(), consentStatusEntity.getConsentStatus());
        }
    }

    private void updateConsentStatus(ConsentStatusEntity recent, ConsentStatusEntity current) {

        consentStatusEntityUpdater.update(recent, current);

        repository.save(recent);

        log.info("Update ConsentStatus {} within status {}", recent.getUuid(), recent.getConsentStatus());
    }
}
