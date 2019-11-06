package com.daisuke.adapters.sonarqube;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Utils {
    public static final String SONAR_DATE_PATTERN = "yyyy-MM-dd";

    public static enum SONAR_BOOL {
	TRUE("true"), FALSE("false"), YES("yes"), NO("no");
	@SuppressWarnings("unused")
	private String description;

	private SONAR_BOOL(String description) {
	    this.description = description;
	};
    }

    public static String randomSonarBOOL() {
	@SuppressWarnings("rawtypes")
	Enum[] values = SONAR_BOOL.values();
	return values[randomNumber(0, values.length - 1)].name();
    }

    /**
     * 
     * @param startYear the beginning year of the interval
     * @param endYear   the end year of the interval
     * @return a random date between 1/1/{@code <startYear>} and
     *         31/12/{@code <endYear>}
     */
    public static String randomDate(int startYear, int endYear) {
	Calendar startDate = new Calendar.Builder().setDate(startYear, 1, 1).build();
	Calendar endDate = new Calendar.Builder().setDate(endYear, 12, 31).build();
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern(SONAR_DATE_PATTERN);
	long start = startDate.getTimeInMillis();
	long end = endDate.getTimeInMillis();
	Instant instant = Instant.ofEpochSecond(start + (long) Math.random() * (end - start));
	LocalDateTime result = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
	return result.format(dtf);
    }

    public static int getYear(Calendar cal) {
	return cal.get(Calendar.YEAR);
    }

    public static String randomString(int size, boolean isAlphanumeric) {
	String result = (isAlphanumeric) ? RandomStringUtils.randomAlphanumeric(size)
		: RandomStringUtils.randomAlphabetic(size);
	log.debug("random value: {}", result);
	return result;
    }

    /**
     * 
     * @return current year
     */
    public static int getYear() {
	return getYear(Calendar.getInstance());
    }

    public static int randomNumber(int startInclusive, int endExclusive) {
	int result = RandomUtils.nextInt(startInclusive, endExclusive);
	log.debug("random int: {}", result);
	return result;
    }

    public static String randomEnumString(@SuppressWarnings("rawtypes") Enum[] enums) {
	return randomEnumList(1, enums).get(0);
    }

    /**
     * 
     * @param size
     * @param enums
     * @return a list of random elements from {@code enums}. This list is immutable.
     */
    public final static List<String> randomEnumList(int size, @SuppressWarnings("rawtypes") Enum[] enums) {
	List<String> result = new ArrayList<>();
	for (int i = 1; i <= size; i++) {
	    result.add(enums[randomNumber(0, enums.length)].name());
	}
	return Collections.unmodifiableList(result);
    }
}
