package utils;

import config.SystemInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author chao
 */
public class ExecUtils {

    private static final Logger logger = LoggerFactory.getLogger(ExecUtils.class);

    public static String run(String cmd) {
        StringBuilder sb = new StringBuilder();

        try {
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec(cmd);
            String line = null;

            InputStream errorOut = proc.getErrorStream();
            InputStreamReader eosr = new InputStreamReader(errorOut, SystemInfo.CHARSET);
            BufferedReader eobr = new BufferedReader(eosr);
            while ((line = eobr.readLine()) != null)
                sb.append(line + "\n");

            if (sb.length() != 0) {
                String error = sb.toString();
                logger.error(error);
                return error;
            }

            InputStream stdout = proc.getInputStream();
            InputStreamReader osr = new InputStreamReader(stdout, SystemInfo.CHARSET);
            BufferedReader obr = new BufferedReader(osr);

            while ((line = obr.readLine()) != null)
                sb.append(line + "\n");

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return sb.toString();
    }
}
