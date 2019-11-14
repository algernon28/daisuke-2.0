package enumerations;

public interface IssueEnumerations extends CommonEnumerations {
    public enum FACETS {
	PROJECTS("projects"), MODULE_UUIDS("moduleUuids"), FILE_UUIDS("fileUuids"), ASSIGNED_TO_ME("assigned_to_me"),
	SEVERITIES("severities"), STATUSES("statuses"), RESOLUTIONS("resolutions"), RULES("rules"),
	ASSIGNEES("assignees"), AUTHORS("authors"), AUTHOR("author"), DIRECTORY("directories"), LANGUAGES("languages"),
	TAGS("tags"), TYPES("types"), OWASPTOP10("owaspTop10"), SANSTOP25("sansTop25"), CWE("cwe"),
	CREATED_AT("createdAt"), SONARSOURCE_SECURITY("sonarsourceSecurity");

	@SuppressWarnings("unused")
	private String description;

	FACETS(String description) {
	    this.description = description;
	}
    }

    public enum ADDITIONAL_FIELDS {
	ALL("_all"), COMMENTS("comments"), LANGUAGES("languages"), ACTION_PLANS("actionPlans"), RULES("rules"),
	TRANSITIONS("transitions"), ACTIONS("actions"), USERS("users");

	@SuppressWarnings("unused")
	private String description;

	ADDITIONAL_FIELDS(String description) {
	    this.description = description;
	}
    }

    public enum SORT_FIELD {
	CREATION_DATE, UPDATE_DATE, CLOSE_DATE, ASSIGNEE, SEVERITY, STATUS, FILE_LINE
    }

    public enum STATUSES {
	OPEN, CONFIRMED, REOPENED, RESOLVED, CLOSED, TO_REVIEW, IN_REVIEW, REVIEWED
    }

    public enum RESOLUTION {
	FALSE_POSITIVE("FALSE-POSITIVE"), WONT_FIX("WONTFIX"), FIXED("FIXED"), REMOVED("REMOVED");
	@SuppressWarnings("unused")
	private String description;

	RESOLUTION(String description) {
	    this.description = description;
	}
    }
}
