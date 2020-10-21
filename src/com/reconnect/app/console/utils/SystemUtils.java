package com.reconnect.app.console.utils;

/**
 * @author s.vareyko
 * @since 16.10.2020
 */
public final class SystemUtils {

    public static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().startsWith("win");
    }
}
