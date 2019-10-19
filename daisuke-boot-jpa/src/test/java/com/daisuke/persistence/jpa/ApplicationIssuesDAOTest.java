package com.daisuke.persistence.jpa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.daisuke.persistence.jpa.ApplicationIssuesEntity.MaintainabilityData;
import com.daisuke.persistence.jpa.ApplicationIssuesEntity.ReliabilityData;
import com.daisuke.persistence.jpa.ApplicationIssuesEntity.VulnerabilityData;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;

import lombok.extern.slf4j.Slf4j;

@Transactional
@SpringBootTest
@ContextConfiguration
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
	TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
@DatabaseSetup("/META-INF/dbtest/applications-data.xml")
@ExpectedDatabase("/META-INF/dbtest/expected-data.xml")
@Slf4j
class ApplicationIssuesDAOTest {
    @Autowired
    private ApplicationIssuesDAO dao;
    
    private ApplicationIssuesEntity testEntity;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
	ReliabilityData bugs = new ReliabilityData().setBlocker(1).setCritical(3).setMajor(35).setMinor(88).setInfo(0);
	VulnerabilityData vulnerabilities = new VulnerabilityData().setBlocker(46).setCritical(10).setMajor(99).setMinor(412).setInfo(9);
	MaintainabilityData codeSmells = new MaintainabilityData().setBlocker(1999).setCritical(7421).setMajor(117000).setMinor(9444).setInfo(145112);
	testEntity = new ApplicationIssuesEntity().setId(113).setKey("fooKey").setName("Mickey Mouse")
		.setLinesOfCode(1130000).setQualifier("VW").setBugs(bugs).setVulnerabilities(vulnerabilities).setCodeSmells(codeSmells);
    }

    @AfterEach
    void tearDown() throws Exception {
	   }

    @Test
    void testFindOne() {
	ApplicationIssuesEntity actual = dao.findByKey("k2");
	assertThat(actual).isNotNull();
	assertThat(actual.getName()).isEqualTo("two");
	assertThat(actual.getOwaspTop10().getMajor()).isEqualTo(21);
    }

    @Test
    void testFindAll() {
	List<ApplicationIssuesEntity> actual = dao.findAll();
	assertThat(actual).isNotEmpty();
	assertThat(actual.size()).isEqualTo(4);
    }

    @Test
    void testCreate() {
	dao.create(testEntity);
	ApplicationIssuesEntity actual = dao.findByKey("fooKey");
	assertThat(actual).isNotNull();
	assertThat(actual).isEqualTo(testEntity);
    }

    @Test
    void testUpdate() {
	fail("Not yet implemented");
    }

    @Test
    void testDelete() {
	fail("Not yet implemented");
    }

    @Test
    void testDeleteById() {
	fail("Not yet implemented");
    }

}
