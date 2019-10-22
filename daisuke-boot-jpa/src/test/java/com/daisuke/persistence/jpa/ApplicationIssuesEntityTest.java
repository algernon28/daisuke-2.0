package com.daisuke.persistence.jpa;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.daisuke.persistence.jpa.ApplicationIssuesEntity.MaintainabilityData;
import com.daisuke.persistence.jpa.ApplicationIssuesEntity.ReliabilityData;
import com.daisuke.persistence.jpa.ApplicationIssuesEntity.VulnerabilityData;
import com.daisuke.persistence.jpa.repositories.ApplicationRepository;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import lombok.extern.slf4j.Slf4j;

@Transactional
@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
	TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
@DatabaseSetup("/META-INF/dbtest/applications-data.xml")
@Slf4j
class ApplicationIssuesEntityTest {

    private ApplicationIssuesEntity testEntity;
    @Autowired
    ApplicationRepository repository;

    @BeforeAll
    void setUpBeforeClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
	ReliabilityData bugs = new ReliabilityData().setBlocker(1).setCritical(3).setMajor(35).setMinor(88).setInfo(0);
	VulnerabilityData vulnerabilities = new VulnerabilityData().setBlocker(46).setCritical(10).setMajor(99)
		.setMinor(412).setInfo(9);
	MaintainabilityData codeSmells = new MaintainabilityData().setBlocker(1999).setCritical(7421).setMajor(117000)
		.setMinor(9444).setInfo(145112);
	testEntity = new ApplicationIssuesEntity().setId(113).setKey("fooKey").setName("Mickey Mouse")
		.setLinesOfCode(1130000).setQualifier("VW").setBugs(bugs).setVulnerabilities(vulnerabilities)
		.setCodeSmells(codeSmells);
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void checkSetup() {
	List<ApplicationIssuesEntity> entities = repository.findAll();
	assertThat(entities).isNotEmpty();
    }

    @Test
    void testFindOne() {
	List<ApplicationIssuesEntity> list = repository.findByKey("k2");
	assertThat(list).isNotEmpty();
	ApplicationIssuesEntity actual = list.get(0);
	assertThat(actual).isNotNull();
	assertThat(actual.getName()).isEqualTo("two");
	assertThat(actual.getOwaspTop10().getMajor()).isEqualTo(21);
    }

    @Test
    void testFindAll() {
	List<ApplicationIssuesEntity> actual = repository.findAll();
	assertThat(actual).isNotEmpty();
	assertThat(actual.size()).isEqualTo(3);
    }

    @Test
    void testCreate() {
	repository.save(testEntity);
	ApplicationIssuesEntity actual = repository.findByKey("fooKey").get(0);
	assertThat(actual).isNotNull();
	assertThat(actual).isEqualTo(testEntity);
	assertThat(repository.count()).isEqualTo(4);
    }

    @Test
    void testUpdate() {
	ApplicationIssuesEntity actual = repository.findByKey("k2").get(0);
	assertThat(repository.findByKey("k2").get(0).getBugs().getBlocker()).isEqualTo(12);
	actual.setBugs(new ReliabilityData().setBlocker(999));
	repository.save(actual);
	assertThat(repository.findByKey("k2").get(0).getBugs().getBlocker()).isEqualTo(999);
    }

    @Test
    void testDelete() {
	ApplicationIssuesEntity actual = repository.findByKey("k2").get(0);
	repository.delete(actual);
	assertThat(repository.findByKey("k2")).isEmpty();
	assertThat(repository.count()).isEqualTo(2);
    }

    @Test
    void testDeleteById() {
	ApplicationIssuesEntity actual = repository.findByKey("k1").get(0);
	Integer id = actual.getId();
	log.debug("Entity name: {}, key: {}, id: {}", actual.getName(), actual.getKey(), actual.getId());
	repository.deleteById(id);
	assertThat(repository.findById(id)).isEmpty();
	assertThat(repository.count()).isEqualTo(2);
    }

}
