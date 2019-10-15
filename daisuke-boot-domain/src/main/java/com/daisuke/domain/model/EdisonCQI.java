package com.daisuke.domain.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.lang.NonNull;

import com.daisuke.domain.model.ComponentDTO.Measure;

import static com.daisuke.domain.model.MeasureEnum.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@NoArgsConstructor
@Slf4j
public class EdisonCQI implements CQI {
    private static final String BLOCKER = "BLOCKER";
    private static final String CRITICAL = "CRITICAL";
    private static final String MAJOR = "MAJOR";
    private static final String MINOR = "MINOR";
    private static final String INFO = "INFO";

    @Override
    public void accept(@NonNull ComponentDTO bean) {
	EnumMap<MeasureEnum, ComponentDTO.Measure> map = Optional.of(bean.getMeasures()).orElseThrow();
	// default is 1 to avoid division by zero later on
	int loc = 1;
	if (bean.getLinesOfCode() != 0) {
	    loc = bean.getLinesOfCode();
	}
	Map<String, Measure> params = new HashMap<>();
	// (nBlk∗200 + nCrt∗80 +nMaj∗20 + mMinS∗5 + nInf∗0)/LOC
	params.put(BLOCKER, map.get(BUG_BLOCKER));
	params.put(CRITICAL, map.get(BUG_CRITICAL));
	params.put(MAJOR, map.get(BUG_MAJOR));
	params.put(MINOR, map.get(BUG_MINOR));
	params.put(INFO, map.get(BUG_INFO));
	double reliabilityIR = getIR(params, 200.0, 80, 20.0, 5.0, 0.0, loc);

	// (nBlk∗200 + nCrt∗80 +nMaj∗20 + mMinS∗5 + nInf∗0)/LOC
	params.clear();
	params.put(BLOCKER, map.get(VULNERABILITY_BLOCKER));
	params.put(CRITICAL, map.get(VULNERABILITY_CRITICAL));
	params.put(MAJOR, map.get(VULNERABILITY_MAJOR));
	params.put(MINOR, map.get(VULNERABILITY_MINOR));
	params.put(INFO, map.get(VULNERABILITY_INFO));
	double securityIR = getIR(params, 200.0, 80, 20.0, 5.0, 0.0, loc);
	// (nBlk∗100 + nCrt∗40 +nMaj∗10 + mMinS∗2 + nInf∗0 )/LOC
	params.clear();
	params.put(BLOCKER, map.get(CODE_SMELL_BLOCKER));
	params.put(CRITICAL, map.get(CODE_SMELL_CRITICAL));
	params.put(MAJOR, map.get(CODE_SMELL_MAJOR));
	params.put(MINOR, map.get(CODE_SMELL_MINOR));
	params.put(INFO, map.get(CODE_SMELL_INFO));
	double maintanabilityIR = getIR(params, 100.0, 40, 10.0, 2.0, 0.0, loc);
	final int RELIABILITY_WEIGHT = 10;
	final int SECURITY_WEIGHT = 10;
	final int MAINTANABILITY_WEIGHT = 5;
	final int DIVISOR = 25;
	final int GLOBAL_WEIGHT = 10;
	double bugsWeighted = reliabilityIR * RELIABILITY_WEIGHT;
	double securityWeighted = securityIR * SECURITY_WEIGHT;
	double smellsWeighted = maintanabilityIR * MAINTANABILITY_WEIGHT;
	// (1 − (reliabilityIR∗10 + securityIR∗10 + maintanabilityIR∗5)/(25))*10
	double cqiDouble = (1 - (bugsWeighted + securityWeighted + smellsWeighted) / DIVISOR) * GLOBAL_WEIGHT;
	BigDecimal cqi = BigDecimal.valueOf(cqiDouble).setScale(2, RoundingMode.HALF_UP);
	bean.setCodeQualityIndex(cqi);
    }

    private static long numIssues(Measure measure) {
	if (Optional.ofNullable(measure).isPresent()) {
	    return measure.getNumIssues();
	}
	return 0L;
    }

    private static double getIR(Map<String, Measure> map, double blkWeight, double crtWeight, double majWeight,
	    double minWeight, double infWeight, int loc) {
	long blocker = numIssues(map.get(BLOCKER));
	long critical = numIssues(map.get(CRITICAL));
	long major = numIssues(map.get(MAJOR));
	long minor = numIssues(map.get(MINOR));
	long info = numIssues(map.get(INFO));
	double result = (blocker * blkWeight + critical * crtWeight + major * majWeight + minor * minWeight
		+ info * infWeight) / loc;
	log.debug("returning IR = {}", result);
	return result;
    }

}
