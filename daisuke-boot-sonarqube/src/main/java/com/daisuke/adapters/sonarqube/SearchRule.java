package com.daisuke.adapters.sonarqube;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 
 * @author Andrea M. Wrapper around
 *         {@code org.sonarqube.ws.client.rules.SearchRequest}, look at official
 *         documentation for the meaning and the possible values of the fields.
 * @see <a href=
 *      "https://next.sonarqube.com/sonarqube/web_api/api/rules/search">/api/rules/search</a>
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class SearchRule {
    /**
     * Possible values:
     * <ul>
     * <li>"true"</li>
     * <li>"false"</li>
     * <li>"yes"</li>
     * <li>"no"</li>
     * </ul>
     */
    private String activation;
    /**
     * Example value: "CRITICAL,BLOCKER" Possible values:
     * <ul>
     * <li>"INFO"</li>
     * <li>"MINOR"</li>
     * <li>"MAJOR"</li>
     * <li>"CRITICAL"</li>
     * <li>"BLOCKER"</li>
     * </ul>
     */
    private List<String> activeSeverities;
    /**
     * Possible values: true/false/yes/no
     */
    private String ascendingSort;
    /**
     * Example value: "2014-06-22"
     */
    private String availableSince;
    /**
     * Example value: "AU-TpxcA-iU5OvuD2FLz"
     */
    private String compareToProfile;
    /**
     * Example value: "12,125,unknown"
     */
    private List<String> cwe;
    /**
     * Example value: "repo,name" Possible values:
     * <ul>
     * <li>"actives"</li>
     * <li>"createdAt"</li>
     * <li>"debtOverloaded"</li>
     * <li>"debtRemFn"</li>
     * <li>"defaultDebtRemFn"</li>
     * <li>"defaultRemFn"</li>
     * <li>"effortToFixDescription"</li>
     * <li>"gapDescription"</li>
     * <li>"htmlDesc"</li>
     * <li>"htmlNote"</li>
     * <li>"internalKey"</li>
     * <li>"isExternal"</li>
     * <li>"isTemplate"</li>
     * <li>"lang"</li>
     * <li>"langName"</li>
     * <li>"mdDesc"</li>
     * <li>"mdNote"</li>
     * <li>"name"</li>
     * <li>"noteLogin"</li>
     * <li>"params"</li>
     * <li>"remFn"</li>
     * <li>"remFnOverloaded"</li>
     * <li>"repo"</li>
     * <li>"scope"</li>
     * <li>"severity"</li>
     * <li>"status"</li>
     * <li>"sysTags"</li>
     * <li>"tags"</li>
     * <li>"templateKey"</li>
     * <li>"updatedAt"</li>
     * </ul>
     */
    private List<String> fieldsToBeReturned;
    /**
     * Example value: "languages,repositories" Possible values:
     * <ul>
     * <li>"languages"</li>
     * <li>"repositories"</li>
     * <li>"tags"</li>
     * <li>"severities"</li>
     * <li>"active_severities"</li>
     * <li>"statuses"</li>
     * <li>"types"</li>
     * <li>"true"</li>
     * <li>"cwe"</li>
     * <li>"owaspTop10"</li>
     * <li>"sansTop25"</li>
     * <li>"sonarsourceSecurity"</li>
     * </ul>
     */
    private List<String> facets;
    private String includeExternal;
    private List<String> inheritance;
    /**
     * Possible values: true/false/yes/no
     */
    private String isTemplate;
    /**
     * Example value: "java,js"
     */
    private List<String> languages;
    private String organization;
    /**
     * Possible values:
     * <ul>
     * <li>"a1"</li>
     * <li>"a2"</li>
     * <li>"a3"</li>
     * <li>"a4"</li>
     * <li>"a5"</li>
     * <li>"a6"</li>
     * <li>"a7"</li>
     * <li>"a8"</li>
     * <li>"a9"</li>
     * <li>"a10"</li>
     * <li>"unknown"</li>
     * </ul>
     */
    private List<String> owaspTop10;
    /**
     * Example value: "42"
     */
    private String page;
    /**
     * Number of items allowed for each page. Max size = 500.<br/>
     * Example value: "20"
     */
    private String pageSize;
    /**
     * Example value: "xpath"
     */
    private String utf8Query;
    /**
     * Example value: "AU-Tpxb--iU5OvuD2FLy"
     */
    private String qprofile;
    /**
     * Example value: "checkstyle,findbugs"
     */
    private List<String> repositories;
    /**
     * Example value: "squid:S001"
     */
    private String ruleKey;
    /**
     * Example value: "name" Possible values:
     * <ul>
     * <li>"name"</li>
     * <li>"updatedAt"</li>
     * <li>"createdAt"</li>
     * <li>"key"</li>
     * </ul>
     */
    private String sortField;
    /**
     * Possible values:
     * <ul>
     * <li>"insecure-interaction"</li>
     * <li>"risky-resource"</li>
     * <li>"porous-defenses"</li>
     * </ul>
     */
    private List<String> sansTop25;
    /**
     * Example value: "CRITICAL,BLOCKER" Possible values:
     * <ul>
     * <li>"INFO"</li>
     * <li>"MINOR"</li>
     * <li>"MAJOR"</li>
     * <li>"CRITICAL"</li>
     * <li>"BLOCKER"</li>
     * </ul>
     */
    private List<String> severities;
    /**
     * Example value: "sql-injection,command-injection" Possible values:
     * <ul>
     * <li>"sql-injection"</li>
     * <li>"command-injection"</li>
     * <li>"path-traversal-injection"</li>
     * <li>"ldap-injection"</li>
     * <li>"xpath-injection"</li>
     * <li>"expression-lang-injection"</li>
     * <li>"rce"</li>
     * <li>"dos"</li>
     * <li>"ssrf"</li>
     * <li>"csrf"</li>
     * <li>"xss"</li>
     * <li>"log-injection"</li>
     * <li>"http-response-splitting"</li>
     * <li>"open-redirect"</li>
     * <li>"xxe"</li>
     * <li>"object-injection"</li>
     * <li>"weak-cryptography"</li>
     * <li>"auth"</li>
     * <li>"insecure-conf"</li>
     * <li>"file-manipulation"</li>
     * </ul>
     */
    private List<String> sonarsourceSecurity;
    /**
     * Example value: "READY" Possible values:
     * <ul>
     * <li>"BETA"</li>
     * <li>"DEPRECATED"</li>
     * <li>"READY"</li>
     * <li>"REMOVED"</li>
     * </ul>
     */
    private List<String> statuses;

    /**
     * Example value: "java:S001"
     */
    private String templateKey;
    /**
     * Example value: "BUG" Possible values:
     * <ul>
     * <li>"CODE_SMELL"</li>
     * <li>"BUG"</li>
     * <li>"VULNERABILITY"</li>
     * <li>"SECURITY_HOTSPOT"</li>
     * </ul>
     */
    private List<String> types;

    public SearchRule addFieldToBeReturned(String field) {
	if (fieldsToBeReturned == null) {
	    fieldsToBeReturned = new ArrayList<>();
	}
	fieldsToBeReturned.add(field);
	return this;
    }
   
}
