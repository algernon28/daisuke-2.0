package enumerations;

public interface CommonEnumerations {
    public enum SONAR_BOOLEAN {
	TRUE("true"), FALSE("false"), YES("yes"), NO("no");
	@SuppressWarnings("unused")
	private String description;

	SONAR_BOOLEAN(String description) {
	    this.description = description;
	}
    }

    public enum INHERITANCE {
	NONE, INHERITED, OVERRIDES
    }

    public enum SONARSOURCE_SECURITY {
	SQL_INJECTION("sql-injection"), COMMAND_INJECTION("command-injection"),
	PATH_TRAVERSAL_INJECTION("path-traversal-injection"), LDAP_INJECTION("ldap-injection"),
	XPATH_INJECTION("xpath-injection"), RCE("rce"), DOS("dos"), SSRF("ssrf"), CSRF("csrf"), XSS("xss"),
	LOG_INJECTION("log-injection"), HTTP_RESPONSE_SPLITTING("http-response-splitting"),
	OPEN_REDIRECT("open-redirect"), XXE("xxe"), OBJECT_INJECTION("object-injection"),
	WEAK_CRYPTOGRAPHY("weak-cryptography"), AUTH("auth"), INSECURE_CONF("insecure-conf"),
	FILE_MANIPOLATION("file-manipulation"), OTHERS("others");

	@SuppressWarnings("unused")
	private String description;

	SONARSOURCE_SECURITY(String description) {
	    this.description = description;
	}
    }

    public enum SANSTOP25 {
	INSECURE_INTERACTION("insecure-interaction"), RISKY_RESOURCE("risky-resource"),
	POROUS_DEFENSE("porous-defense");

	@SuppressWarnings("unused")
	private String description;

	SANSTOP25(String description) {
	    this.description = description;
	}
    }

    public enum OWASPTOP10 {
	A1("a1"), A2("a2"), A3("a3"), A4("a4"), A5("a5"), A6("a6"), A7("a7"), A8("a8"), A9("a9"), A10("a10");
	@SuppressWarnings("unused")
	private String description;

	OWASPTOP10(String description) {
	    this.description = description;
	}
    }
}
