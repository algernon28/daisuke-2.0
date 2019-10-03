package com.daisuke.domain.model;

import java.util.EnumMap;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Accessors(chain = true, fluent = false)
public class ComponentModel {
    @NonNull
    private String key;
    private String organization;
    @NonNull
    private String id;
    private String name;
    private String projectId;
    @NonNull
    private String qualifier;
    private String path;
    private String analisysDate;
    private String language;
    private int linesOfCode;
    private double codeQualityIndex;
    private EnumMap<MeasureKey, Measure> measures;

    @Setter(value = AccessLevel.NONE)
    public enum MeasureKey {

    }

    public ComponentModel putMeasure(MeasureKey key, Measure measure) {
	if (measures == null) {
	    measures = new EnumMap<>(MeasureKey.class);
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
