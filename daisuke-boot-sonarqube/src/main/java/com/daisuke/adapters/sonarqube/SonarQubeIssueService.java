package com.daisuke.adapters.sonarqube;

import java.util.Arrays;
import java.util.List;

import org.sonarqube.ws.client.issues.IssuesService;

import com.daisuke.domain.adapters.IssuesAdapter;
import com.daisuke.domain.adapters.RulesAdapter;
import com.daisuke.domain.adapters.SearchException;
import com.daisuke.domain.model.IssueDTO;

public class SonarQubeIssueService implements IssuesAdapter<SearchIssue> {
    private SonarQubeClient client;
    private RulesAdapter rulesAdapter;
    private IssuesService issuesService;
    private static final String FACET_NAME = "severities";
    private static final List<String> FACET_LIST = Arrays.asList(FACET_NAME);
    /**
     * SonarQube currently has a maximum result size hard capped to 10k, requesting
     * for more will produce a HTTP 400 error.
     */
    public static final long RESULT_MAX_SIZE = 10000;
    
    public SonarQubeIssueService(SonarQubeClient client) {
	this.client = client;
	this.rulesAdapter = new SonarQubeRulesService(client);
	this.issuesService = client.getIssuesService();
    }
    
    @Override
    public List<IssueDTO> findIssues(SearchIssue search) throws SearchException {
	// TODO Auto-generated method stub
	return null;
    }

    

}
