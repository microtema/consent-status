package de.microtema.consentstatus.integration.repository;

import de.microtema.consentstatus.enums.ConsentStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;


@Entity
@Table(name = "CONSENT_STATUS")
public class ConsentStatusEntity {

    @Id
    @UuidGenerator
    @GeneratedValue
    @Column(name = "UUID")
    private String uuid;

    @Column(name = "STATUS")
    @Enumerated(EnumType.ORDINAL)
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
