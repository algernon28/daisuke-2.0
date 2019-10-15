package com.daisuke.persistence.jpa;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
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
public class ApplicationIssuesEntity implements Serializable {
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

    @Data
    @NoArgsConstructor
    @Accessors(chain = true)
    public static class ReliabilityData {
	@Column(name = "BUG_BLOCKER")
	private BigInteger blocker;
	@Column(name = "BUG_CRITICAL")
	private BigInteger critical;
	@Column(name = "BUG_MAJOR")
	private BigInteger major;
	@Column(name = "BUG_MINOR")
	private BigInteger minor;
	@Column(name = "BUG_INFO")
	private BigInteger info;
    }

    @Data
    @NoArgsConstructor
    @Accessors(chain = true)
    public static class VulnerabilityData {
	@Column(name = "VULNERABILITY_BLOCKER")
	private BigInteger blocker;
	@Column(name = "VULNERABILITY_CRITICAL")
	private BigInteger critical;
	@Column(name = "VULNERABILITY_MAJOR")
	private BigInteger major;
	@Column(name = "VULNERABILITY_MINOR")
	private BigInteger minor;
	@Column(name = "VULNERABILITY_INFO")
	private BigInteger info;
    }

    @Data
    @NoArgsConstructor
    @Accessors(chain = true)
    public static class MaintainabilityData {
	@Column(name = "MAINTAINABILITY_BLOCKER")
	private BigInteger blocker;
	@Column(name = "MAINTAINABILITY_CRITICAL")
	private BigInteger critical;
	@Column(name = "MAINTAINABILITY_MAJOR")
	private BigInteger major;
	@Column(name = "MAINTAINABILITY_MINOR")
	private BigInteger minor;
	@Column(name = "MAINTAINABILITY_INFO")
	private BigInteger info;
    }

    @Data
    @NoArgsConstructor
    @Accessors(chain = true)
    public static class OwaspTop10 {
	@Column(name = "OWASP_BLOCKER")
	private BigInteger blocker;
	@Column(name = "OWASP_CRITICAL")
	private BigInteger critical;
	@Column(name = "OWASP_MAJOR")
	private BigInteger major;
	@Column(name = "OWASP_MINOR")
	private BigInteger minor;
	@Column(name = "OWASP_INFO")
	private BigInteger info;
    }

    @Column(name = "DT_INSERT", insertable = false, updatable = false)
    @Temporal(value = TemporalType.DATE)
    private java.util.Date date;

}
