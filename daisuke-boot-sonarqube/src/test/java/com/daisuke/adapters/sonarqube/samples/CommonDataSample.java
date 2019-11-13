package com.daisuke.adapters.sonarqube.samples;

import static com.daisuke.adapters.sonarqube.Utils.randomEnumStringList;
import static com.daisuke.adapters.sonarqube.Utils.randomNumber;
import static com.daisuke.adapters.sonarqube.Utils.randomString;

import java.util.List;

public abstract class CommonDataSample {

    protected static String randomOrganization = String.format("org-%s", randomString(15, false));
    protected static String randomPage = String.valueOf(randomNumber(1, 10));
    protected static String randomPageSize = String.valueOf(randomNumber(1, 500));
    protected static List<String> randomLanguages = randomEnumStringList(2, LANGUAGES_VALUES.values());

    public enum OWASPTOP10_VALUES {
	a1, a2, a3, a4, a5, a6, a7, a8, a9, a10
    };

    public enum LANGUAGES_VALUES {
	ABAP("abap", "ABAP"), APEX("apex", "Apex"), CSHARP("cs", "C#"), CPP("cpp", "C++"), COBOL("cobol", "COBOL"),
	CSS("css", "CSS"), FLEX("flex", "Flex"), GO("go", "Go"), HTML("web", "HTML"), JSP("jsp", "JSP"),
	JAVA("java", "Java"), JAVASCRIPT("js", "JavaScript"), KOTLIN("kotlin", "Kotlin"),
	OBJECTIVEC("objc", "Objective-C"), PHP("php", "PHP"), PLI("pli", "PL/I"), PLSQL("plsql", "PL/SQL"),
	PYTHON("py", "Python"), RPG("rpg", "RPG"), RUBY("ruby", "Ruby"), SCALA("scala", "Scala"),
	SWIFT("swift", "Swift"), TSQL("tsql", "T-SQL"), TYPESCRIPT("ts", "TypeScript"), VBNET("vbnet", "VB.NET"),
	XML("xml", "XML");

	@SuppressWarnings("unused")
	private String key;
	@SuppressWarnings("unused")
	private String description;

	private LANGUAGES_VALUES(String key, String description) {
	    this.key = key;
	    this.description = description;
	}
    }

    public enum SONARSOURCE_SECURITY_VALUES {
	SQL_INJECTION("sql-injection"), COMMAND_INJECTION("command-injection"),
	PATH_TRAVERSAL_INJECTION("path-traversal-injection"), LDAP_INJECTION("ldap-injection"),
	XPATH_INJECTION("xpath-injection"), RCE("rce"), DOS("dos"), SSRF("ssrf"), CSRF("csrf"), XSS("xss"),
	LOG_INJECTION("log-injection"), HTTP_RESPONSE_SPLITTING("http-response-splitting"),
	OPEN_REDIRECT("open-redirect"), XXE("xxe"), OBJECT_INJECTION("object-injection"),
	WEAK_CRYPTOGRAPHY("weak-cryptography"), AUTH("auth"), INSECURE_CONF("insecure-conf"),
	FILE_MANIPOLATION("file-manipulation"), OTHERS("others");

	@SuppressWarnings("unused")
	private String description;

	private SONARSOURCE_SECURITY_VALUES(String description) {
	    this.description = description;
	}
    }

    public enum SANSTOP25_VALUES {
	INSECURE_INTERACTION("insecure-interaction"), RISKY_RESOURCE("risky-resource"),
	POROUS_DEFENSE("porous-defense");

	@SuppressWarnings("unused")
	private String description;

	private SANSTOP25_VALUES(String description) {
	    this.description = description;
	}
    }
}
