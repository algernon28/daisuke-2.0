package com.daisuke.adapters.sonarqube;

import java.util.ArrayList;
import java.util.List;

import org.springframework.lang.NonNull;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 
 * @author Andrea M. Wrapper around
 *         {@code org.sonarqube.ws.client.rules.SearchRequest}, look at official
 *         documentation for the meaning and the possible values of the fields.
 * @see <a href=
 *      "https://next.sonarqube.com/sonarqube/web_api/api/components/search">/api/components/search</a>
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class SearchComponent {
    /**
     * This is a mandatory parameter. Possible values:
     * <ul>
     * <li>APP - Applications</li>
     * <li>BRC - Sub-projects (SonarQube Enterprise only)</li>
     * <li>DIR - Directories</li>
     * <li>FIL - Files</li>
     * <li>SVW - Portfolios Subview (SonarQube Enterprise only)</li>
     * <li>TRK - Projects</li>
     * <li>UTS - Test Files</li>
     * <li>VW - Portfolios View (SonarQube Enterprise only)</li>
     * </ul>
     */
    @NonNull
    private List<String> qualifiers;
    /**
     * Example value: "java,js"
     */
    private String language;
    /**
     * Example value: "42"
     */
    private String page;
    /**
     * Number of items allowed for each page. Max items = 500<br/>
     * 
     * Example value: "20"
     */
    private String pageSize; 
    /**
     * Example value: "my-org"
     */
    private String organization;
    /**
     * Example value: "xpath"
     */
    private String utf8Query;

    public SearchComponent addQualifier(String qualifier) {
	if (qualifiers == null) {
	    qualifiers = new ArrayList<>();
	}
	qualifiers.add(qualifier);
	return this;
    }
}
