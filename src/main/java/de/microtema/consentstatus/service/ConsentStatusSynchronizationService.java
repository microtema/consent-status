package de.microtema.consentstatus.service;

import de.microtema.consentstatus.docms.service.ConsentStatusDocMSService;
import de.microtema.consentstatus.integration.service.ConsentStatusIntegrationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ConsentStatusSynchronizationService {

    private int pageNumber = 0;

    @Value("${integration.max-page-size:100}")
    private int pageSize = 100;

    private final ConsentStatusDocMSService consentStatusDocMSService;
    private final ConsentStatusIntegrationService consentStatusIntegrationService;

    public ConsentStatusSynchronizationService(ConsentStatusDocMSService consentStatusDocMSService,
                                               ConsentStatusIntegrationService consentStatusIntegrationService) {
        this.consentStatusDocMSService = consentStatusDocMSService;
        this.consentStatusIntegrationService = consentStatusIntegrationService;
    }

    /**
     * Make sure that pageNumber is not held in memory, so that on new container start should not restart from begin.
     */
    public void synchronizeConsentStatus() {

        var consentStatusList = consentStatusIntegrationService.getConsentStatusList(pageNumber++, pageSize);

        if (consentStatusList.isEmpty()) {
            return;
        }

        consentStatusList.forEach(it -> synchronizeConsentStatus(it.getUuid()));
    }

    public void synchronizeConsentStatus(String customerId) {

        var consentStatusInDocMS = consentStatusDocMSService.getConsentStatus(customerId);
        var consentStatusInIntegrationSystem = consentStatusIntegrationService.getConsentStatus(customerId);

        if (!Objects.equals(consentStatusInDocMS, consentStatusInIntegrationSystem)) {
            consentStatusIntegrationService.updateConsentStatus(customerId, consentStatusInDocMS);
        }
    }
}