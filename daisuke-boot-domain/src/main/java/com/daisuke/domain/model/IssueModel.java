package com.daisuke.domain.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true, fluent = false)
public class IssueModel {
    private String ruleName;
    private String key;
    private String ruleKey;
    private SeverityEnum severity;
    private int line;
    private String message;
    private TypeEnum type;
    private String component;
    private String project;
    private String resolution;
    private String status;
    private String effort;
    private String assignee;
    private String author;
    private String creationDate;
    private String updateDate;
    private String closeDate;
    private String organization;
}
