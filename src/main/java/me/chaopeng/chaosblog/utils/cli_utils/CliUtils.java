package me.chaopeng.chaosblog.utils.cli_utils;

import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

/**
 * 命令行参数类
 *
 * @author chao
 */
public class CliUtils {

    private static final Logger logger = LoggerFactory.getLogger(CliUtils.class);

    private static Options opts = new Options();
    private static HashMap<String, CliOptionHandler> handlers = new HashMap<String, CliOptionHandler>();

    public static void setOption(String opt, Boolean hasArg, String description, CliOptionHandler handler) {
        opts.addOption(opt, hasArg, description);
        handlers.put(opt, handler);
    }

    public static void parser(String[] args) {
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cl = parser.parse(opts, args);
            Option[] options = cl.getOptions();
            for (Option option : options) {
                CliOptionHandler handler = handlers.get(option.getOpt());
                if (handler != null) {
                    try {
                        handler.call(option.getValues());
                    } catch (CliException e) {
                        logger.error("!!!", e);
                    }
                }
            }
        } catch (ParseException e) {
            logger.error("!!!", e);
        }

    }

    public static void help() {
        HelpFormatter hf = new HelpFormatter();
        hf.printHelp("Options", opts);
    }
}
