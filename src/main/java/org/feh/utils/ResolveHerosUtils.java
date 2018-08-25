package org.feh.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.feh.model.functions.HeroSource;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

public class ResolveHerosUtils {
	
	private static Logger logger = LoggerFactory.getLogger(ResolveHerosUtils.class);
	private static Gson gson = new Gson();
	
	public static String HEROHEAD = "https://gamewith.akamaized.net/article_tools/fireemblem/gacha/i_";
	
	public static String FEH_SOURCE = "https://gamewith.jp/fireemblem/article/show/51352";
	
	public static void main(String[] args) {
		List<HeroSource> sources = getHeroSourcesByFile();
		sources.forEach(s -> {
			System.err.println(s.getId() + "---" + s.getAid());
		});
	}
	
	public static List<HeroSource> getHeroSourcesByUrl(){
		List<HeroSource> sources = new ArrayList<>();
		String fehSource = UrlUtils.sendGet(FEH_SOURCE, "", "");
		Pattern pattern = Pattern.compile("[var]+\\s[FEH_IV_DATA]+=.+?[var]{3}");
		Matcher matcher = pattern.matcher(fehSource);
		List<String> strs = new ArrayList<>();
		if(matcher.find()) {
			for (int i = 0; i <= matcher.groupCount(); i++) {
				logger.info(matcher.group(i));
				strs.add(matcher.group(i).substring("var FEH_IV_DATA=".length(), matcher.group(i).length() - 4));
			}
			strs.forEach(str -> {
				JSONObject jsonObject = new JSONObject(str);
				Iterator<String> keys = jsonObject.keys();
				while (keys.hasNext()) {
					String key = keys.next();
					Object obj = jsonObject.get(key);
					HeroSource heroSource = gson.fromJson(obj.toString(), HeroSource.class);
					sources.add(heroSource);
				}
			});
		}
		return sources;
	}
	
	public static List<HeroSource> getHeroSourcesByFile(){
		List<HeroSource> sources = new ArrayList<>();
		InputStream in = ResolveHerosUtils.class.getResourceAsStream("/common/hero-source.json");
		BufferedReader br = new BufferedReader(new InputStreamReader(in));

		StringBuffer sb = new StringBuffer();
		try {
			String _sb;
			while ((_sb = br.readLine()) != null) {
				sb.append(_sb);
			}
			if(sb.length() > 0) {
				JSONObject object = new JSONObject(sb.toString());
				Iterator<String> keys = object.keys();
				while (keys.hasNext()) {
					String key = keys.next();
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
