package com.bobacadodl.JSONChatLib;

/**
 * User: bobacadodl
 * Date: 10/27/13
 * Time: 8:05 PM
 */
public enum JSONChatFormat {
    BOLD("bold"),
    UNDERLINED("underlined"),
    STRIKETHROUGH("strikethrough"),
    ITALIC("italic"),
    OBFUSCATED("obfuscated");
    private final String format;

    JSONChatFormat(String format) {
        this.format = format;
    }

    public String getFormatString() {
        return format;
    }
}