package com.itdragon.common;

public enum SortType {
	
	AUTO("id"),
	CREATE_DATE("createDate"), 
	TITLE("title");
	
	private String value;

	private SortType(String value) {
		this.value = value;
	}
	public String getValue() {
		return value;
	}
	public static SortType fromValue(String v) {
        for (SortType s: SortType.values()) {
            if (s.value.equals(v)) {
                return s;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
