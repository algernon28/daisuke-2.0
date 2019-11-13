package com.daisuke.adapters.sonarqube;

import static com.daisuke.adapters.sonarqube.samples.IssueData.IssueSample.getIssueDTO;
import static com.daisuke.adapters.sonarqube.samples.IssueData.IssueSample.getWsIssue;
import static com.daisuke.adapters.sonarqube.samples.IssueData.IssueSample.randomIssueDTOList;
import static com.daisuke.adapters.sonarqube.samples.IssueData.IssueSample.randomIssueList;
import static com.daisuke.adapters.sonarqube.samples.IssueData.SearchSample.getSearchIssue;
import static com.daisuke.adapters.sonarqube.samples.IssueData.SearchSample.getWsSearch;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mapstruct.factory.Mappers;
import org.sonarqube.ws.Issues.Issue;
import org.sonarqube.ws.client.issues.SearchRequest;

import com.daisuke.domain.model.IssueDTO;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class IssueMapperTest {
    private IssueMapper mapper;
    private SearchRequest expectedWsSearch;
    private SearchIssue expectedSearchIssue;
    private Issue expectedWsIssue;
    private IssueDTO expectedIssueDTO;
    private List<IssueDTO> expectedIssueDTOList;
    private List<Issue> expectedIssueList;

    @BeforeAll
     void setUpBeforeClass() throws Exception {
	mapper = Mappers.getMapper(IssueMapper.class);
	expectedWsSearch = getWsSearch();
	expectedSearchIssue = getSearchIssue();
	expectedWsIssue = getWsIssue();
	expectedIssueDTO = getIssueDTO();
	expectedIssueDTOList = randomIssueDTOList(50);
	expectedIssueList = randomIssueList(50);
    }

    @BeforeEach
    void setUp() throws Exception {
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    final void testToSearchIssue() {
	SearchIssue actual = mapper.toSearchIssue(expectedWsSearch);
	assertThat(expectedSearchIssue).isEqualToComparingFieldByField(actual);
    }

    @Test
    final void testToWsSearchRequest() {
	SearchRequest actual = mapper.toWsSearchRequest(expectedSearchIssue);
	assertThat(expectedWsSearch).isEqualToComparingFieldByField(actual);
    }

    @Test
    final void testToIssueDTO() {
	IssueDTO actual = mapper.toIssueDTO(expectedWsIssue);
	assertThat(expectedIssueDTO).isEqualToComparingFieldByField(actual);
    }

    @Test
    final void testToIssueDTOList() {
	List<IssueDTO> actual = mapper.toIssueDTOList(expectedIssueList);
	assertThat(expectedIssueDTOList).isEqualTo(actual);
	
    }

    @Test
    final void testToWsIssue() {
	Issue actual = mapper.toWsIssue(expectedIssueDTO);
	assertThat(expectedWsIssue).isEqualToComparingFieldByField(actual);
    }

}
