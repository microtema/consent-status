package de.microtema.consentstatus.event;

import de.microtema.consentstatus.enums.ConsentStatus;

public class ConsentStatusEvent {

    private String uuid;

    private ConsentStatus consentStatus;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public ConsentStatus getConsentStatus() {
        return consentStatus;
    }

    public void setConsentStatus(ConsentStatus consentStatus) {
        this.consentStatus = consentStatus;
    }
}
