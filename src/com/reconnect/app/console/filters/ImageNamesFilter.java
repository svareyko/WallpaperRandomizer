package com.reconnect.app.console.filters;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author s.vareyko
 * @since 16.10.2020
 */
public class ImageNamesFilter implements FilenameFilter {

    private final Pattern filePattern;

    public ImageNamesFilter(final Pattern filePattern) {
        this.filePattern = filePattern;
    }

    @Override
    public boolean accept(final File dir, final String name) {
        final Matcher matcher = filePattern.matcher(name);
        return matcher.find();
    }
}
