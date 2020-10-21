package com.reconnect.app.console.utils;

import java.util.Objects;

/**
 * @author s.vareyko
 * @since 16.10.2020
 */
public final class StringUtils {

    public static String createSpaces(final int count) {
        return new String(new char[count]).replace('\0', ' ');
    }

    public static String getFileExtension(final String name) {
        return name.substring(name.lastIndexOf(".") + 1);
    }

    public static String safeArg(final String[] args, final int index) {
        if (Objects.isNull(args) || args.length <= index) {
            return null;
        }
        return args[index];
    }
}
