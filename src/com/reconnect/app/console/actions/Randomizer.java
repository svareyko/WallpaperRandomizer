package com.reconnect.app.console.actions;

import com.reconnect.app.console.enums.SupportedFormats;
import com.reconnect.app.console.filters.ImageNamesFilter;
import com.reconnect.app.console.utils.StringUtils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * @author s.vareyko
 * @since 16.10.2020
 */
public class Randomizer {

    private final static String RESULT_FILE_PATTERN = "%s_%s.%s";

    private final File dir;
    private final FilenameFilter filter;

    public Randomizer(final String path) {
        this.dir = Optional.ofNullable(path).map(File::new)
                .orElseGet(() -> new File(new File("").getAbsolutePath()));
        if (!dir.exists() || !dir.isDirectory()) {
            System.err.println("Provided path is not exists or not a folder");
            System.exit(404);
            filter = null;
            return;
        }
        final String regexp = String.format(".*\\.%s$", SupportedFormats.asRegexpPart());
        final Pattern filePattern = Pattern.compile(regexp, Pattern.CASE_INSENSITIVE);
        filter = new ImageNamesFilter(filePattern);
    }

    public void run() {
        System.out.println("Running randomizer in: " + dir.getAbsolutePath());
        final File[] files = dir.listFiles(filter);

        if (Objects.isNull(files) || files.length == 0) {
            System.err.println("Images not found");
            System.exit(404);
            return;
        }
        System.out.println("Will be processed following files:");
        System.out.println(Arrays.toString(files));

        final long now = System.currentTimeMillis();
        for (final File file : files) {
            final File target = generateTarget(file, now);
            final boolean result = file.renameTo(target);
            System.out.println(String.format("result %b of rename %s to %s",
                    result, file.getAbsolutePath(), target.getAbsolutePath()));
        }

        System.out.println("Finished");
        System.exit(0);
    }

    private File generateTarget(final File file, final long stamp) {

        final String ext = StringUtils.getFileExtension(file.getName());
        final int randVal = Math.abs(new Random().nextInt());
        final String name = String.format(RESULT_FILE_PATTERN, stamp, randVal, ext);
        final File target = new File(file.getParent() + File.separator + name);
        if (target.exists()) {
            return generateTarget(file, stamp);
        }
        return target;
    }
}
