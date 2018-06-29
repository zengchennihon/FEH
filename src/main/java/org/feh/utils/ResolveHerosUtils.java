package org.feh.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.feh.model.functions.HeroSource;
import org.json.JSONObject;

import com.google.gson.Gson;

public class ResolveHerosUtils {
	
	private static Gson gson = new Gson();
	
	public static String heroHead = "https://gamewith.akamaized.net/article_tools/fireemblem/gacha/i_";
	
	public static void main(String[] args) {
		List<HeroSource> sources = getHeroSourcesByFile();
		sources.forEach(s -> {
			System.err.println(s.getId() + "---" + s.getAid());
		});
	}
	
	public static List<HeroSource> getHeroSourcesByFile(){
		List<HeroSource> sources = new ArrayList<>();
		InputStream in = ResolveHerosUtils.class.getResourceAsStream("/common/hero-source.json");
		BufferedReader br = new BufferedReader(new InputStreamReader(in));

		StringBuffer sb = new StringBuffer();
		try {
			String _sb = null;
			while ((_sb = br.readLine()) != null) {
				sb.append(_sb);
			}
			if(sb.length() > 0) {
				JSONObject object = new JSONObject(sb.toString());
				Iterator<String> keys = object.keys();
				while (keys.hasNext()) {
					String key = (String) keys.next();
					JSONObject heorSource = object.getJSONObject(key);
					HeroSource source = gson.fromJson(heorSource.toString(), HeroSource.class);
					sources.add(source);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sources;
	}

}
