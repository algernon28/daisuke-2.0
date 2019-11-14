package enumerations;

public interface RuleEnumerations extends CommonEnumerations{
	public enum FIELDS_TO_BE_RETURNED {
	    ACTIVES("actives"), CREATED_AT("createdAt"), EFFORT_TOFIX_DESCRIPTION("effortToFixDescription"),
	    GAP_DESCRIPTION("gapDescription"), HTML_DESCRIPTION("htmlDesc"), HTML_NOTE("htmlNote"),
	    LANGUAGE_KEY("lang"), LANGUAGE_NAME("langName"), NAME("name"), SEVERITY("severity"), STATUS("status"),
	    TAGS("tags"), UPDATE_AT("updatedAt");
	    @SuppressWarnings("unused")
	    private String description;

	    FIELDS_TO_BE_RETURNED(String description) {
		this.description = description;
	    }
	}

	public enum FACETS {
	    LANGUAGES("languages"), REPOSITORIES("repositories"), TAGS("tags"), SEVERITIES("severities"),
	    ACTIVE_SEVERITIES("active_severities"), STATUSES("statuses"), TYPES("types"), TRUE("true"), CWE("cwe"),
	    OWASPTOP10("owaspTop10"), SANSTOP25("sansTop25"), SONARSOURCE_SECURITY("sonarsourceSecurity");

	    @SuppressWarnings("unused")
	    private String description;

	    FACETS(String description) {
		this.description = description;
	    }
	}

	public enum REPOSITORIES {
	    CHECKSTYLE("checkstyle"), FINDBUGS("findbugs"), SONARLINT("sonarlint"), PYCHARM("pycharm"),
	    STYLECOP("stylecop"), CODACY("codacy");
	    @SuppressWarnings("unused")
	    private String description;

	    REPOSITORIES(String description) {
		this.description = description;
	    }
	}

	public enum SORT_FIELD {
	    NAME("name"), UPDATE_AT("updatedAt"), CREATED_AT("createdAt"), KEY("key");
	    @SuppressWarnings("unused")
	    private String description;

	    SORT_FIELD(String description) {
		this.description = description;
	    }
	}

	public enum STATUSES {
	    BETA, DEPRECATED, READY, REMOVED
	}
    }