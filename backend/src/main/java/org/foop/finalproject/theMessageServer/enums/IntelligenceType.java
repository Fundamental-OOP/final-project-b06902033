package org.foop.finalproject.theMessageServer.enums;

public enum IntelligenceType {
    DIRECT_MSG("直達"),
    ENCRYPTED_MSG("密電"),
    CORPUS_MSG("文本");

    public String name;

    IntelligenceType(String name) {
        this.name = name;
    }
}
