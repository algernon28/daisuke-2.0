/**
 * 
 */
package com.daisuke.application.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.daisuke.adapters.sonarqube.SearchComponent;
import com.daisuke.domain.adapters.ComponentsAdapter;
import com.daisuke.domain.adapters.SearchException;
import com.daisuke.domain.model.ComponentDTO;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Andrea M.
 *
 */
@Slf4j
@RestController
@RequestMapping("/report/components")
public class SonarQubeComponentController {
    private ComponentsAdapter<SearchComponent> componentsAdapter;

    /**
     * Default constructor, the parameter is injected by Spring
     * 
     * @param componentsAdapter The Spring Service retrieving components
     */
    public SonarQubeComponentController(ComponentsAdapter<SearchComponent> componentAdapter) {
	super();
	this.componentsAdapter = componentAdapter;
    }

    @GetMapping("/search")
    @ResponseBody
    public ResponseEntity<List<ComponentDTO>> getComponents(
	    @RequestParam(name = "language", required = false) String language,
	    @RequestParam(name = "p", required = false) String page,
	    @RequestParam(name = "ps", required = false) String pageSize,
	    @RequestParam(name = "q", required = false) String utf8Query,
	    @RequestParam(name = "qualifiers", required = true) List<String> qualifiers) throws SearchException {
	SearchComponent search = new SearchComponent().setLanguage(language).setPage(page).setPageSize(pageSize)
		.setQualifiers(qualifiers);
	List<ComponentDTO> result = componentsAdapter.findComponents(search);
	log.debug("components found: {}", result);
	return ResponseEntity.ok(result);
    }

    @GetMapping("/show")
    @ResponseBody
    public ResponseEntity<ComponentDTO> getComponent(
	    @RequestParam(name = "component", required = true) String componentKey) throws SearchException {
	ComponentDTO result = componentsAdapter.findComponentByKey(componentKey);
	log.debug("component found: {}", result);
	return ResponseEntity.ok(result);
    }
}
