package com.daisuke.domain.model;

import java.util.EnumMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static com.daisuke.domain.model.SeverityEnum.*;
import static com.daisuke.domain.model.TypeEnum.*;
import static org.assertj.core.api.Assertions.assertThat;
import static com.daisuke.domain.model.MeasureEnum.*;

import com.daisuke.domain.model.ComponentDTO.Measure;

class EdisonCQITest {
    private ComponentDTO bean;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
	EnumMap<MeasureEnum, ComponentDTO.Measure> measures = new EnumMap<MeasureEnum, ComponentDTO.Measure>(
		MeasureEnum.class);
	measures.put(BUG_BLOCKER, new Measure(BUG, BLOCKER, 0.0f, 0L));
	measures.put(BUG_CRITICAL, new Measure(BUG, CRITICAL, 0.0f, 18L));
	measures.put(BUG_MAJOR, new Measure(BUG, MAJOR, 0.0f, 250L));
	measures.put(BUG_MINOR, new Measure(BUG, MINOR, 0.0f, 33L));
	measures.put(BUG_INFO, new Measure(BUG, INFO, 0.0f, 2540L));
	measures.put(VULNERABILITY_BLOCKER, new Measure(VULNERABILITY, BLOCKER, 0.0f, 6L));
	measures.put(VULNERABILITY_CRITICAL, new Measure(VULNERABILITY, CRITICAL, 0.0f, 24L));
	measures.put(VULNERABILITY_MAJOR, new Measure(VULNERABILITY, MAJOR, 0.0f, 310L));
	measures.put(VULNERABILITY_MINOR, new Measure(VULNERABILITY, MINOR, 0.0f, 257L));
	measures.put(VULNERABILITY_INFO, new Measure(VULNERABILITY, INFO, 0.0f, 747L));
	measures.put(CODE_SMELL_BLOCKER, new Measure(CODE_SMELL, BLOCKER, 0.0f, 0L));
	measures.put(CODE_SMELL_CRITICAL, new Measure(CODE_SMELL, CRITICAL, 0.0f, 17L));
	measures.put(CODE_SMELL_MAJOR, new Measure(CODE_SMELL, MAJOR, 0.0f, 0L));
	measures.put(CODE_SMELL_MINOR, new Measure(CODE_SMELL, MINOR, 0.0f, 5426L));
	measures.put(CODE_SMELL_INFO, new Measure(CODE_SMELL, INFO, 0.0f, 1L));
	bean = new ComponentDTO().setMeasures(measures).setName("FOO").setKey("foo.key");
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void testAccept() {
	int loc = 18444;
	bean.setLinesOfCode(loc);
	EdisonCQI ec = new EdisonCQI();
	ec.accept(bean);
	double cqi = bean.getCodeQualityIndex().doubleValue();
	double expected = 5.02;
	assertThat(cqi).isEqualTo(expected);
    }

}
