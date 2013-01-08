package utils.cli_utils;

import java.util.HashMap;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.logging.LogFactory;

/**
 * 命令行参数类
 * @author chao
 *
 */
public class CliUtils {
	private static Options opts = new Options();
	private static HashMap<String, CliOptionHandler> handlers = new HashMap<String, CliOptionHandler>();
	
	public static void setOption(String opt, Boolean hasArg, String description, CliOptionHandler handler){
		opts.addOption(opt, hasArg, description);
		handlers.put(opt, handler);
	}
	
	public static void parser(String[] args){
		BasicParser parser = new BasicParser();
		try {
			CommandLine cl = parser.parse(opts, args);
			Option[] options = cl.getOptions();
			for (Option option : options) {
				CliOptionHandler handler = handlers.get(option.getOpt());
				if(handler!=null){
					try {
						handler.call(option.getValues());
					} catch (CliException e) {
						LogFactory.getLog(CliUtils.class).error("!!!", e);
					}
				}
			}
		} catch (ParseException e) {
			LogFactory.getLog(CliUtils.class).error("!!!", e);
		}
		
	}
	
	public static void help(){
		HelpFormatter hf = new HelpFormatter();
		hf.printHelp("Options", opts);
	}
}
