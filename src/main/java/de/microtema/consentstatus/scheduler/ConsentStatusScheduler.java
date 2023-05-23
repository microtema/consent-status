package de.microtema.consentstatus.scheduler;

import de.microtema.consentstatus.service.ConsentStatusSynchronizationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ConsentStatusScheduler {

    private final ConsentStatusSynchronizationService consentStatusSynchronizationService;

    public ConsentStatusScheduler(ConsentStatusSynchronizationService consentStatusSynchronizationService) {
        this.consentStatusSynchronizationService = consentStatusSynchronizationService;
    }

    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void execute() {

        consentStatusSynchronizationService.synchronizeConsentStatus();
    }
}
