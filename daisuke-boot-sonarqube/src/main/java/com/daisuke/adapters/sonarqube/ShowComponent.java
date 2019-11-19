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
 *      "https://next.sonarqube.com/sonarqube/web_api/api/components/search">/api/components/search</a>
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class ShowComponent {
    /**
     * This is a mandatory parameter Example value: "my-project"
     */
    @NonNull
    private String componentKey;
}
