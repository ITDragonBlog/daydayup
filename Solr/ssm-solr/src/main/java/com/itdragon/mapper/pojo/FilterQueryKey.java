package com.itdragon.mapper.pojo;

public enum FilterQueryKey {
	
	PRICE("price"), // 价格
	CATALOG_NAME("catalog_name"), // 类目名
	SORT("sort"); // 价格排序
	
	private String value;

	private FilterQueryKey(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static FilterQueryKey fromValue(String v) {
        for (FilterQueryKey c: FilterQueryKey.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
