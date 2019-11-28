package com.daisuke.adapters.sonarqube;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.sonarqube.ws.Issues.Issue;
import org.sonarqube.ws.Issues.SearchWsResponse;
import org.sonarqube.ws.client.issues.IssuesService;
import org.sonarqube.ws.client.issues.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daisuke.domain.adapters.IssuesAdapter;
import com.daisuke.domain.adapters.RulesAdapter;
import com.daisuke.domain.adapters.SearchException;
import com.daisuke.domain.model.IssueDTO;
import com.daisuke.domain.model.IssueNotFoundException;
import com.daisuke.domain.model.RuleDTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Data
@Slf4j
public class SonarQubeIssueService implements IssuesAdapter<SearchIssue> {
    private static final String ISSUE_NOT_FOUND = "The search did not return any issue";
    /**
     * SonarQube currently has a maximum result size hard capped to 10k, requesting
     * for more will produce a HTTP 400 error.
     */
    public static final long RESULT_MAX_SIZE = 10000;
    private SonarQubeClient client;
    private RulesAdapter<SearchRule> rulesAdapter;
    private IssuesService issuesService;
    private IssueMapper issueMapper;
    private RuleMapper ruleMapper;

    public SonarQubeIssueService(SonarQubeClient client) {
	this.client = client;
	this.rulesAdapter = new SonarQubeRulesService(client);
	this.issuesService = client.getIssuesService();

    }

    @Autowired
    public void setMapper(IssueMapper mapper) {
	this.issueMapper = mapper;
    }

    @Autowired
    public void setRulesAdapter(RulesAdapter<SearchRule> adapter) {
	this.rulesAdapter = adapter;
    }

    @Override
    public List<IssueDTO> findIssues(SearchIssue search) throws SearchException {
	client.refreshConnection();
	SearchRequest request = issueMapper.toWsSearchRequest(search);
	SearchWsResponse response = issuesService.search(request);
	Optional<List<Issue>> wsIssues = Optional.ofNullable(response.getIssuesList());
	List<IssueDTO> result = new ArrayList<>();
	if (wsIssues.isPresent() && !wsIssues.isEmpty()) {
	    result = issueMapper.toIssueDTOList(wsIssues.get());
	    log.debug("returning list: {}", result);
	} else {
	    log.debug("{}, returning empty list", ISSUE_NOT_FOUND);
	}
	return result;
    }

    @Override
    public IssueDTO findIssueByKey(String key) throws SearchException {
	client.refreshConnection();
	List<String> keys = Arrays.asList(key);
	SearchRequest request = new SearchRequest().setIssues(keys);
	SearchWsResponse response = issuesService.search(request);
	Optional<Issue> wsIssue = Optional.ofNullable(response.getIssues(0));
	String ruleKey = null;
	IssueDTO result = null;
	if (wsIssue.isPresent() && !wsIssue.isEmpty()) {
	    result = issueMapper.toIssueDTO(wsIssue.get());
	    ruleKey = result.getRuleKey();
	    // find the rule description and set it
	    RuleDTO rule = rulesAdapter.findRuleByKey(ruleKey);
	    result.setRuleName(rule.getDescription());
	} else {
	    String msg = String.format("%s [key=%s]", ISSUE_NOT_FOUND, key);
	    throw new IssueNotFoundException(msg);
	}
	return result;
    }
}
