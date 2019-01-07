package com.tcps.origin.appcore.utils;

import java.util.UUID;

public class GetId {

    private GetId() {

    }

    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        String uuidStr = str.replace("-", "");
        return uuidStr;
    }
}
