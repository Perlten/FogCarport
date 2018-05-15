/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.logging;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author adamlass
 */
public class Logging {

    static final Logger LOGGER = Logger.getLogger(Logger.class.getName());

    public static void main(String[] args) throws IOException {
        Logging lg = new Logging();
        lg.run();
    }

    public void run() throws IOException {
        addHandlers();
        LOGGER.log(Level.OFF, "Only this message will be logged");
    }

    public void addHandlers() throws IOException {
        LOGGER.addHandler(new ConsoleHandler());

        String path = "devLog.log";

        if (new File(Conf.LOGFILEPATH).exists()) {
            path = Conf.LOGFILEPATH;
        }

        FileHandler handler = new FileHandler(path, true);

        handler.setFormatter(new SimpleFormatter());
        LOGGER.addHandler(handler);
    }

}
