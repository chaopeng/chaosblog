package utils;

import config.SystemInfo;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author chao
 */
public class ExecUtils {
    public static String run(String cmd) {
        StringBuilder sb = new StringBuilder();

        try {
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec(cmd);
            String line = null;

            InputStream errorOut = proc.getErrorStream();
            InputStreamReader eosr = new InputStreamReader(errorOut, SystemInfo.charset);
            BufferedReader eobr = new BufferedReader(eosr);
            while ((line = eobr.readLine()) != null)
                sb.append(line + "\n");

            if (sb.length() != 0) {
                String error = sb.toString();
                LogFactory.getLog(ExecUtils.class).error(error);
                return error;
            }

            InputStream stdout = proc.getInputStream();
            InputStreamReader osr = new InputStreamReader(stdout, SystemInfo.charset);
            BufferedReader obr = new BufferedReader(osr);

            while ((line = obr.readLine()) != null)
                sb.append(line + "\n");

        } catch (Exception e) {
            LogFactory.getLog(ExecUtils.class).error("!!!", e);
        }

        return sb.toString();
    }
}
