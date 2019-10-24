package com.daisuke.persistence.jpa.entities;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity
@Table(name = "APPLICATION_ISSUES")
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class ApplicationIssues implements Serializable {
    private static final long serialVersionUID = -2222016595512253787L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    @EqualsAndHashCode.Exclude
    private Integer id;
    @Column(name = "COMPONENT_NAME")
    private String name;
    @Column(name = "COMPONENT_QUALIFIER")
    private String qualifier;
    @Column(name = "COMPONENT_KEY")
    private String key;
    @Column(name = "COMPONENT_LOC")
    private Integer linesOfCode;

    @Embedded
    @AttributeOverride(name = "blocker", column = @Column(name = "BUG_BLOCKER"))
    @AttributeOverride(name = "critical", column = @Column(name = "BUG_CRITICAL"))
    @AttributeOverride(name = "major", column = @Column(name = "BUG_MAJOR"))
    @AttributeOverride(name = "minor", column = @Column(name = "BUG_MINOR"))
    @AttributeOverride(name = "info", column = @Column(name = "BUG_INFO"))
    private ReliabilityData bugs;
    @Embedded
    @AttributeOverride(name = "blocker", column = @Column(name = "VULNERABILITY_BLOCKER"))
    @AttributeOverride(name = "critical", column = @Column(name = "VULNERABILITY_CRITICAL"))
    @AttributeOverride(name = "major", column = @Column(name = "VULNERABILITY_MAJOR"))
    @AttributeOverride(name = "minor", column = @Column(name = "VULNERABILITY_MINOR"))
    @AttributeOverride(name = "info", column = @Column(name = "VULNERABILITY_INFO"))
    private VulnerabilityData vulnerabilities;
    @Embedded
    @AttributeOverride(name = "blocker", column = @Column(name = "MAINTAINABILITY_BLOCKER"))
    @AttributeOverride(name = "critical", column = @Column(name = "MAINTAINABILITY_CRITICAL"))
    @AttributeOverride(name = "major", column = @Column(name = "MAINTAINABILITY_MAJOR"))
    @AttributeOverride(name = "minor", column = @Column(name = "MAINTAINABILITY_MINOR"))
    @AttributeOverride(name = "info", column = @Column(name = "MAINTAINABILITY_INFO"))
    private MaintainabilityData codeSmells;

    @Embedded
    @AttributeOverride(name = "blocker", column = @Column(name = "OWASP_BLOCKER"))
    @AttributeOverride(name = "critical", column = @Column(name = "OWASP_CRITICAL"))
    @AttributeOverride(name = "major", column = @Column(name = "OWASP_MAJOR"))
    @AttributeOverride(name = "minor", column = @Column(name = "OWASP_MINOR"))
    @AttributeOverride(name = "info", column = @Column(name = "OWASP_INFO"))
    private OwaspTop10 owaspTop10;

    @Column(name = "DT_INSERT", insertable = false, updatable = false)
    @Temporal(value = TemporalType.DATE)
    private java.util.Date date;

    @Data
    @NoArgsConstructor
    @Accessors(chain = true)
    @Embeddable
    public static class ReliabilityData implements Serializable {
	private static final long serialVersionUID = 7523715489799053196L;
	private Integer blocker;
	private Integer critical;
	private Integer major;
	private Integer minor;
	private Integer info;
    }

    @Data
    @NoArgsConstructor
    @Accessors(chain = true)
    @Embeddable
    public static class VulnerabilityData implements Serializable {
	private static final long serialVersionUID = -9104190399310505307L;
	private Integer blocker;
	private Integer critical;
	private Integer major;
	private Integer minor;
	private Integer info;
    }

    @Data
    @NoArgsConstructor
    @Accessors(chain = true)
    @Embeddable
    public static class MaintainabilityData implements Serializable {
	private static final long serialVersionUID = -441331599282786010L;
	private Integer blocker;
	private Integer critical;
	private Integer major;
	private Integer minor;
	private Integer info;
    }

    @Data
    @NoArgsConstructor
    @Accessors(chain = true)
    public static class OwaspTop10 implements Serializable {
	private static final long serialVersionUID = -444193283679563898L;
	private Integer blocker;
	private Integer critical;
	private Integer major;
	private Integer minor;
	private Integer info;
    }
}
