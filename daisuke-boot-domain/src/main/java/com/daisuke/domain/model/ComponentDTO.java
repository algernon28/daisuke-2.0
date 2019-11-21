package com.daisuke.domain.model;

import java.math.BigDecimal;
import java.util.EnumMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Accessors(fluent = false, chain = true)
public class ComponentDTO {
    @NonNull
    private String key;
    private String refKey;
    private String organization;
    private String name;
    private String description;
    private String projectId;
    @NonNull
    private String qualifier;
    private String path;
    private String language;
    private int linesOfCode;
    private BigDecimal codeQualityIndex;
    private EnumMap<MeasureEnum, Measure> measures;


    public ComponentDTO addMeasure(MeasureEnum key, Measure measure) {
	if (measures == null) {
	    measures = new EnumMap<>(MeasureEnum.class);
	}
	measures.put(key, measure);
	return this;
    }

    @Data 
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Measure {
	private TypeEnum issueType;
	private SeverityEnum issueSeverity;
	private float effort;
	private Long numIssues;
    }
    
}
