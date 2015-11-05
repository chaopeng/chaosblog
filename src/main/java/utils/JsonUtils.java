package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.esotericsoftware.reflectasm.FieldAccess;

/**
 * @author chao
 */
public class JsonUtils {
	public static void createrJsonConf(String name, Object o) throws Exception {
		FileWriter fw = new FileWriter(name);
		JSONObject jo = new JSONObject();
		FieldAccess access = FieldAccess.get(o.getClass());
		String[] fields = access.getFieldNames();
		for (int i = 0; i < fields.length; i++) {
			Object value = access.get(o, i);
			String key = fields[i];
			jo.put(key, value);
		}
		fw.write(jo.toString(4));
		fw.close();
	}

	public static void readJsonConf(String filename, Object o) throws Exception {
		FileReader fr = new FileReader(filename);
		JSONTokener jt = new JSONTokener(fr);
		JSONObject jo = new JSONObject(jt);
		parseJson(jo, o);
		fr.close();
	}

	/**
	 * 简单解释json，暂时只支持String int JSONArray<String> 需要更复杂的再扩展吧，暂时是用来解释配置文件的
	 * 
	 * @param jo
	 * @param o
	 * @throws Exception
	 */
	public static void parseJson(JSONObject jo, Object o) throws Exception {
		FieldAccess access = FieldAccess.get(o.getClass());
		String[] fields = access.getFieldNames();
		for (int i = 0; i < fields.length; i++) {
			String key = fields[i];
			if(!jo.has(key)){
				continue;
			}
			Object val = jo.get(key);
			Object value = null;
			// 需要特殊处理array
			if (val instanceof JSONArray) {
				JSONArray arr = (JSONArray) val;
				value = new String[arr.length()];
				for (int j = 0; j < arr.length(); j++) {
					String str = arr.getString(j);
					((String[]) value)[j] = str;
				}
			} else {
				if(val instanceof String && ((String) val).length()==0){
					continue;
				}
				value = val;
			}
			access.set(o, i, value);
		}
	}
	
	private static Pattern pattern = Pattern.compile("^\\s*#");
	public static HashMap<String, JSONObject> readMergerFile(String filename) throws Exception{
		HashMap<String, JSONObject> res = new HashMap<String, JSONObject>();
		FileReader fr = new FileReader(filename);
		BufferedReader br = new BufferedReader(fr);
		String s = "";
		String name = "";
		StringBuilder sb = null;
		boolean readJson = false;
		
		while((s=br.readLine())!=null){
			if(s.length()==0) continue;
			else if(pattern.matcher(s).find()) continue;
			else if(s.equals("{")) {
				sb = new StringBuilder("{");
				readJson = true;
			}
			else if(s.equals("}")) {
				sb.append("}");
				JSONObject jo = new JSONObject(sb.toString());
				res.put(name, jo);
				readJson = false;
			}
			else{
				if(readJson) sb.append(s);
				else name = s;
			}
		}
		
		br.close();
		fr.close();
		return res;
	}
	
	public static JSONObject readSingleJson(BufferedReader br) throws Exception{
		
		String s = "";
		StringBuilder sb = null;
		
		while((s=br.readLine())!=null){
			if(s.length()==0) continue;
			else if(s.equals("{")) {
				sb = new StringBuilder("{");
			}
			else if(s.equals("}")) {
				sb.append("}");
				break;
			}
			else{
				sb.append(s);
			}
		}
		
		JSONObject res = new JSONObject(sb.toString());
		return res;
	}
}
