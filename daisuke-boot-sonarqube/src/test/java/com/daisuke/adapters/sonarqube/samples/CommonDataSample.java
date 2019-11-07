package com.daisuke.adapters.sonarqube.samples;

import static com.daisuke.adapters.sonarqube.Utils.randomNumber;
import static com.daisuke.adapters.sonarqube.Utils.randomString;

public abstract class CommonDataSample {

    public static final String randomOrganization = String.format("org-%s", randomString(15, false));
    public static final String randomPage = String.valueOf(randomNumber(1, 10));
    public static final String randomPageSize = String.valueOf(randomNumber(1, 500));

}
