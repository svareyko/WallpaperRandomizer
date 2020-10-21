package com.reconnect.app.console.enums;

import com.reconnect.app.console.utils.StringUtils;
import com.reconnect.app.console.utils.SystemUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author s.vareyko
 * @since 16.10.2020
 */
public enum Command {
    HELP("help", Collections.singletonList("Show this help")),
    RANDOMIZE("randomize", Arrays.asList(
            "Starts randomization process in the specified folder",
            "or in the folder where was started the app.",
            "Available argument: absolute or relative path, examples:",
            "<executable> randomize wallpapers/old",
            "<executable> randomize " + (SystemUtils.isWindows() ? "\"C:\\Users\\Documents\\My Wallpapers\"" : "\"/usr/my wallpapers\"")
    ));

    private static final String COMMAND_PATTERN = " - %s%s      %s";
    private static final int COMMAND_FIXED_CHARS_NUM = 9;

    private final String name;
    private final List<String> desc;

    Command(final String name, final List<String> desc) {
        this.name = name;
        this.desc = desc;
    }

    public static Command define(final String command) {
        if (Objects.isNull(command)) {
            return HELP;
        }

        for (final Command value : Command.values()) {
            if (command.endsWith(value.getName())) {
                return value;
            }
        }

        return HELP;
    }

    public static void print() {
        final StringBuilder builder = new StringBuilder("Tool that renames pictures to change their order randomly\n\n");
        builder.append("Usage: <executable> <command> <argument>\n");
        final int longestName = longestName();
        final int commandPadding = longestName + COMMAND_FIXED_CHARS_NUM;
        for (final Command cmd : Command.values()) {
            final int spacesToAdd = longestName - cmd.getName().length();
            builder.append(String.format(COMMAND_PATTERN,
                    cmd.getName(),
                    StringUtils.createSpaces(spacesToAdd),
                    buildDescription(commandPadding, cmd.getDesc())));
        }

        builder.append("\nEnjoy!");
        System.out.println(builder.toString());
    }

    private static String buildDescription(final int padding, final List<String> rows) {
        final StringBuilder builder = new StringBuilder(rows.get(0)).append("\n");
        final String spaces = StringUtils.createSpaces(padding);
        for (int i = 1; i < rows.size(); i++) {
            builder.append(spaces).append(rows.get(i)).append("\n");
        }
        return builder.toString();
    }

    private static int longestName() {
        int length = 0;
        for (final Command cmd : Command.values()) {
            final int currentLength = cmd.getName().length();
            if (currentLength > length) {
                length = currentLength;
            }
        }
        return length;
    }

    public String getName() {
        return name;
    }

    public List<String> getDesc() {
        return desc;
    }
}
