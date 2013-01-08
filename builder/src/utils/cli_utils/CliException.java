package utils.cli_utils;

public class CliException extends Exception {
	private static final long serialVersionUID = 1L;
	public static final String WRONG_NUM_ARGS_FORMAT = "%s has wrong numbers of args";
	public static final String WRONG_TYPE_ARGS_FORMAT = "%s has wrong number type args";
	
	public CliException(String option, String errorFormat){
		super(String.format(errorFormat, option));
	}
}
