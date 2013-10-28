package com.bobacadodl.JSONChatLib;

/**
 * User: bobacadodl
 * Date: 10/27/13
 * Time: 9:07 PM
 */
public enum JSONChatClickEventType {
    RUN_COMMAND("run_command"),
    SUGGEST_COMMAND("suggest_command"),
    OPEN_URL("open_url");
    private final String type;

    JSONChatClickEventType(String type) {
        this.type = type;
    }

    public String getTypeString() {
        return type;
    }
}
