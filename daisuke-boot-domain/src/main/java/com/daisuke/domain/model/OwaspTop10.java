package com.daisuke.domain.model;

public enum OwaspTop10 {
    A1("a1"), A2("a2"), A3("a3"), A4("a4"), A5("a5"), A6("a6"), A7("a7"), A8("a8"), A9("a9"), A10("a10"),
    ANY("a1,a2,a3,a4,a5,a6,a7,a8,a9,a10");
    private String description;

    private OwaspTop10(String description) {
	this.description = description;
    }

    public String getDescription() {
	return this.description;
    }
}
