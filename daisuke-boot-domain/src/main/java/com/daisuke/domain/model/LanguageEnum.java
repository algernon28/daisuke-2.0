package com.daisuke.domain.model;

public enum LanguageEnum {

    ABAP("abap", "ABAP"), APEX("apex", "Apex"), CSHARP("cs", "C#"), CPP("cpp", "C++"), COBOL("cobol", "COBOL"),
    CSS("css", "CSS"), FLEX("flex", "Flex"), GO("go", "Go"), HTML("web", "HTML"), JSP("jsp", "JSP"),
    JAVA("java", "Java"), JAVASCRIPT("js", "JavaScript"), KOTLIN("kotlin", "Kotlin"), OBJECTIVEC("objc", "Objective-C"),
    PHP("php", "PHP"), PLI("pli", "PL/I"), PLSQL("plsql", "PL/SQL"), PYTHON("py", "Python"), RPG("rpg", "RPG"),
    RUBY("ruby", "Ruby"), SCALA("scala", "Scala"), SWIFT("swift", "Swift"), TSQL("tsql", "T-SQL"),
    TYPESCRIPT("ts", "TypeScript"), VBNET("vbnet", "VB.NET"), XML("xml", "XML");
    private String key;

    private String description;

    LanguageEnum(String key, String description) {
	this.key = key;
	this.description = description;
    }

    public String key() {
	return key;
    }

    public String description() {
	return description;
    }

}
