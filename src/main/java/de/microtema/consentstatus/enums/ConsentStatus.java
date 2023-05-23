package de.microtema.consentstatus.enums;

import java.util.concurrent.ThreadLocalRandom;

public enum ConsentStatus {

    YES, REVOKE, NO;

    public static ConsentStatus random() {

        int randomNum = ThreadLocalRandom.current().nextInt(0, ConsentStatus.values().length);

        return ConsentStatus.values()[randomNum];
    }
}
