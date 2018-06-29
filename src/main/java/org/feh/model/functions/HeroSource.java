package org.feh.model.functions;

public class HeroSource {

	private String id;

	private String aid;

	private String name;

	private HeroSourceAttr st3;

	private HeroSourceAttr st3_s;

	private HeroSourceAttr st4;

	private HeroSourceAttr st4_s;

	private HeroSourceAttr st5;

	private HeroSourceAttr st5_s;

	private String reco;

	private String remark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HeroSourceAttr getSt3() {
		return st3;
	}

	public void setSt3(HeroSourceAttr st3) {
		this.st3 = st3;
	}

	public HeroSourceAttr getSt3_s() {
		return st3_s;
	}

	public void setSt3_s(HeroSourceAttr st3_s) {
		this.st3_s = st3_s;
	}

	public HeroSourceAttr getSt4() {
		return st4;
	}

	public void setSt4(HeroSourceAttr st4) {
		this.st4 = st4;
	}

	public HeroSourceAttr getSt4_s() {
		return st4_s;
	}

	public void setSt4_s(HeroSourceAttr st4_s) {
		this.st4_s = st4_s;
	}

	public HeroSourceAttr getSt5() {
		return st5;
	}

	public void setSt5(HeroSourceAttr st5) {
		this.st5 = st5;
	}

	public HeroSourceAttr getSt5_s() {
		return st5_s;
	}

	public void setSt5_s(HeroSourceAttr st5_s) {
		this.st5_s = st5_s;
	}

	public String getReco() {
		return reco;
	}

	public void setReco(String reco) {
		this.reco = reco;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}

class HeroSourceAttr {

	private String HP[];

	private String ATK[];

	private String SPD[];

	private String DEF[];

	private String RES[];

	public String[] getHP() {
		return HP;
	}

	public void setHP(String[] hP) {
		HP = hP;
	}

	public String[] getATK() {
		return ATK;
	}

	public void setATK(String[] aTK) {
		ATK = aTK;
	}

	public String[] getSPD() {
		return SPD;
	}

	public void setSPD(String[] sPD) {
		SPD = sPD;
	}

	public String[] getDEF() {
		return DEF;
	}

	public void setDEF(String[] dEF) {
		DEF = dEF;
	}

	public String[] getRES() {
		return RES;
	}

	public void setRES(String[] rES) {
		RES = rES;
	}

}