package com.daisuke.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = false, chain = true)
public class RuleDTO {
    private String key;
    private String description;
    private TypeEnum type;
    private SeverityEnum severity;
    private Integer occurrencies;
    private LanguageEnum language;
}
