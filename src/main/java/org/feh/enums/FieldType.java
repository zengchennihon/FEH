package org.feh.enums;
public enum FieldType{
	BYTE,
	SHORT,
	INTEGER,
	LANG,
	FLOAT,
	DOUBLE,
	CHARACTER,
	BOOLEAN,
	DATE,
	STRING,
	OBJECT,
	
	DEFAULT,
	
	;
	
	public static FieldType getKeyByType(String type) {
		for (FieldType fieldType : FieldType.values()) {
			if(fieldType.getName().equalsIgnoreCase(type)) {
				return fieldType;
			}
		}
		return DEFAULT;
	}
	
	public String getName() {
		return this.name();
	}
	
}