package org.feh.consts;

import java.util.Arrays;
import java.util.List;

public class HerosPropertyConsts {

	public final static String HP = "HP";
	public final static String ATK = "ATK";
	public final static String SPD = "SPD";
	public final static String DEF = "DEF";
	public final static String RES = "RES";

	public static List<String> fingAllConsts(){
		return Arrays.asList(HP, ATK, SPD, DEF, RES);
	}
	
}
