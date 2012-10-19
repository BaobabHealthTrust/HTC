package com.baobab.htc.business.utils;

import net.sf.microlog.midp.file.*;
import net.sf.microlog.core.format.PatternFormatter;

/**
 * This utility class provides a uniform appender to any consumer thoughout the entire application
 *
 * @author Yamiko J. Msosa
 * @version 1.0
 * Date written: 04th May 2010
 */
public class LoggingUtil {

    /**
     * Method to return an appropriate appender for the loggers of this application
     *
     * @return FileAppender for the application
     */
    public static FileAppender getFileAppender() {
        FileAppender appender = new FileAppender();
        appender.setFileName(ConstantHelper.LOG_FILE);
        PatternFormatter formatter = new PatternFormatter();
        formatter.setPattern("%d{ISO8601} %P %c{1} - %m");
        appender.setFormatter(formatter);
        return appender;
    }
}
