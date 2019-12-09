package com.daisuke.adapters.sonarqube;

/**
 * 
 * @author Andrea M. Wrapper around
 *         {@code org.sonarqube.ws.client.components.SearchRequest}, look at official
 *         documentation for the meaning and the possible values of the fields.
 * @see <a href=
 *      "https://next.sonarqube.com/sonarqube/web_api/api/components/search">/api/components/search</a>
 */

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class ShowComponent {
    /**
     * Example value: "my_project"
     */
    private String componentKey;
}
