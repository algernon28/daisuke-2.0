package com.daisuke.domain.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.EnumMap;
import java.util.Optional;
import org.springframework.lang.NonNull;

import com.daisuke.domain.model.ComponentDTO.Measure;

import static com.daisuke.domain.model.MeasureEnum.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EdisonCQI implements CQI {

    @Override
    public void accept(@NonNull ComponentDTO bean) {
	EnumMap<MeasureEnum, ComponentDTO.Measure> map = Optional.of(bean.getMeasures()).orElseThrow();
	// default is 1 to avoid division by zero later on
	int loc = 1;
	if (bean.getLinesOfCode() != 0) {
	    loc = bean.getLinesOfCode();
	}
	// (nBlk∗200 + nCrt∗80 +nMaj∗20 + mMinS∗5 + nInf∗0)/LOC
	long bugBlocker = numIssues(map.get(BUG_BLOCKER));
	long bugCritical = numIssues(map.get(BUG_CRITICAL));
	long bugMajor = numIssues(map.get(BUG_MAJOR));
	long bugMinor = numIssues(map.get(BUG_MINOR));
	long bugInfo = numIssues(map.get(BUG_INFO));
	double reliabilityIR = (bugBlocker * 200.0 + bugCritical * 80.0 + bugMajor * 20.0 + bugMinor * 5.0
		+ bugInfo * 0.0) / loc;
	// (nBlk∗200 + nCrt∗80 +nMaj∗20 + mMinS∗5 + nInf∗0)/LOC
	long vulnerabilityBlocker = numIssues(map.get(VULNERABILITY_BLOCKER));
	long vulnerabilityCritical = numIssues(map.get(VULNERABILITY_CRITICAL));
	long vulnerabilityMajor = numIssues(map.get(VULNERABILITY_MAJOR));
	long vulnerabilityMinor = numIssues(map.get(VULNERABILITY_MINOR));
	long vulnerabilityInfo = numIssues(map.get(VULNERABILITY_INFO));
	double securityIR = (vulnerabilityBlocker * 200.0 + vulnerabilityCritical * 80.0 + vulnerabilityMajor * 20.0
		+ vulnerabilityMinor * 5.0 + vulnerabilityInfo * 0.0) / loc;
	// (nBlk∗100 + nCrt∗40 +nMaj∗10 + mMinS∗2 + nInf∗0 )/LOC
	long maintainabilityBlocker = numIssues(map.get(CODE_SMELL_BLOCKER));
	long maintainabilityCritical = numIssues(map.get(CODE_SMELL_CRITICAL));
	long maintainabilityMajor = numIssues(map.get(CODE_SMELL_MAJOR));
	long maintainabilityMinor = numIssues(map.get(CODE_SMELL_MINOR));
	long maintainabilityInfo = numIssues(map.get(CODE_SMELL_INFO));
	double maintanabilityIR = (maintainabilityBlocker * 100.0 + maintainabilityCritical * 40.0
		+ maintainabilityMajor * 20.0 + maintainabilityMinor * 2.0 + maintainabilityInfo * 0.0) / loc;
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

}
