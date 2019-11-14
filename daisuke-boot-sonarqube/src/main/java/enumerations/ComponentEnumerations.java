package enumerations;

public interface ComponentEnumerations extends CommonEnumerations {
    public enum QUALIFIERS_VALUES {
	APP("Applications"), BRC("Sub-projects"), DIR("Directories"), FIL("Files"), SVW("Portfolios"), TRK("Projects"),
	UTS("Test Files"), VW("Portfolios");

	@SuppressWarnings("unused")
	private String description;

	QUALIFIERS_VALUES(String description) {
	    this.description = description;
	}
    }
}
