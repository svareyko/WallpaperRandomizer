package com.reconnect.app.console.enums;

/**
 * @author s.vareyko
 * @since 16.10.2020
 */
public enum  SupportedFormats {
    JPG,
    PNG,
    GIF,
    JPEG,
    BMP;

    public static String asRegexpPart() {
        final StringBuilder builder = new StringBuilder("(");

        for (final SupportedFormats format : SupportedFormats.values()) {
            builder.append(format.name().toLowerCase()).append("|");
        }

        return builder.append(")").toString();
    }
}
