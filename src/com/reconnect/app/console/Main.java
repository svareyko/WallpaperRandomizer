package com.reconnect.app.console;

import com.reconnect.app.console.actions.Randomizer;
import com.reconnect.app.console.enums.Command;
import com.reconnect.app.console.utils.StringUtils;

public class Main {

    /*public static void main(final String[] args) {
        main2(new String[]{"randomize", "temp"});
    }*/

    public static void main(final String[] args) {

        final Command command = Command.define(StringUtils.safeArg(args, 0));

        switch (command) {
            case RANDOMIZE:
                new Randomizer(StringUtils.safeArg(args, 1)).run();
                break;
            case HELP:
            default:
                Command.print();
                break;
        }
    }
}
