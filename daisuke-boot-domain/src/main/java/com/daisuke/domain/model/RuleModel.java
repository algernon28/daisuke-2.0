package com.daisuke.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true, fluent = false)
public class RuleModel {
    private String key;
    private String description;
    private TypeEnum type;
    private SeverityEnum severity;
    private Integer occurrencies; 
}
