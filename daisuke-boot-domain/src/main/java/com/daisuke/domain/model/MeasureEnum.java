package com.daisuke.domain.model;

public enum MeasureEnum {
    BUG_BLOCKER("bugBlocker"), BUG_CRITICAL("bugCritical"), BUG_MAJOR("bugMajor"), BUG_MINOR("bugMinor"),
    BUG_INFO("bugInfo"), VULNERABILITY_BLOCKER("vulnerabilityBlocker"), VULNERABILITY_CRITICAL("vulnerabilityCritical"),
    VULNERABILITY_MAJOR("vulnerabilityMajor"), VULNERABILITY_MINOR("vulnerabilityMinor"),
    VULNERABILITY_INFO("vulnerabilityInfo"), CODE_SMELL_BLOCKER("maintanibilityBlocker"),
    CODE_SMELL_CRITICAL("maintanibilityCritical"), CODE_SMELL_MAJOR("maintanibilityMajor"),
    CODE_SMELL_MINOR("maintanibilityMinor"), CODE_SMELL_INFO("maintanibilityInfo"),
    OWASPTOP10_BLOCKER("owaspTop10Blocker"), OWASPTOP10_CRITICAL("owaspTop10Critical"),
    OWASPTOP10_MAJOR("owaspTop10Major"), OWASPTOP10_MINOR("owaspTop10Minor"), OWASPTOP10_INFO("owaspTop10Info");
    private String label;

    private MeasureEnum(String label) {
	this.label = label;
    }

    public String getLabel() {
	return label;
    }
}
