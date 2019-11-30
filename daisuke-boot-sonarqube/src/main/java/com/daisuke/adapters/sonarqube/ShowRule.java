/**
 * 
 */
package com.daisuke.adapters.sonarqube;

import org.springframework.lang.NonNull;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 
 * @author Andrea M. Wrapper around
 *         {@code org.sonarqube.ws.client.rules.ShowRequest}, look at official
 *         documentation for the meaning and the possible values of the fields.
 * @see <a href=
 *      "https://next.sonarqube.com/sonarqube/web_api/api/rules/show">/api/rules/show</a>
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class ShowRule {
    /**
     * This is a mandatory parameter Example value: "javascript:EmptyBlock"
     */
    @NonNull
    private String key;
    private String actives;

}
