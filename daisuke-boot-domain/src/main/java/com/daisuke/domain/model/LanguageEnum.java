package com.daisuke.domain.model;

import lombok.Getter;

public enum LanguageEnum {

    abap("ABAP"), apex("Apex"), cs("C#"), CPP("C++"), cobol("COBOL"), css("CSS"), flex("Flex"), go("Go"), HTML("HTML"),
    jsp("JSP"), java("Java"), js("JavaScript"), kotlin("Kotlin"), objc("Objective-C"), php("PHP"), pli("PL/I"),
    plsql("PL/SQL"), py("Python"), RPG("RPG"), ruby("Ruby"), scala("Scala"), swift("Swift"), tsql("T-SQL"),
    ts("TypeScript"), vbnet("VB.NET"), xml("XML");

    @Getter
    private String description;

    LanguageEnum(String description) {
	this.description = description;
    }
}
