package com.daisuke.adapters.sonarqube.samples;

import static com.daisuke.adapters.sonarqube.Utils.randomEnumStringList;
import static com.daisuke.adapters.sonarqube.Utils.randomNumber;
import static com.daisuke.adapters.sonarqube.Utils.randomString;

import java.util.List;

import com.daisuke.adapters.sonarqube.samples.RuleData.LANGUAGES_VALUES;

public abstract class CommonDataSample {

    protected static String randomOrganization = String.format("org-%s", randomString(15, false));
    protected static String randomPage = String.valueOf(randomNumber(1, 10));
    protected static String randomPageSize = String.valueOf(randomNumber(1, 500));
    protected static List<String> randomLanguages = randomEnumStringList(2, LANGUAGES_VALUES.values());
}
