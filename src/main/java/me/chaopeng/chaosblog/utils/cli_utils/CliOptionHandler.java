package me.chaopeng.chaosblog.utils.cli_utils;

public interface CliOptionHandler {
    void call(String[] cliArgs) throws CliException;
}
