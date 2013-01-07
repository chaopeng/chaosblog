package utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.logging.LogFactory;

import config.SystemInfo;

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

			InputStream stdout = proc.getInputStream();
			InputStreamReader osr = new InputStreamReader(stdout, SystemInfo.chatset);
			BufferedReader obr = new BufferedReader(osr);
			
			while ((line = obr.readLine()) != null)
				sb.append(line + "\n");

		} catch (Exception e) {
			LogFactory.getLog(ExecUtils.class).error("!!!", e);
		}

		return sb.toString();
	}
}
