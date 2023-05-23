package de.microtema.consentstatus.docms.service;

import de.microtema.consentstatus.enums.ConsentStatus;
import org.springframework.stereotype.Service;

@Service
public class ConsentStatusDocMSService {

    public ConsentStatus getConsentStatus(String customerId) {

        return ConsentStatus.random();
    }
}
