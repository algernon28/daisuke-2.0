package com.daisuke.adapters.sonarqube;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.sonarqube.ws.Common.RuleType;
import org.sonarqube.ws.Rules.Rule;

import com.daisuke.domain.model.LanguageEnum;
import com.daisuke.domain.model.MeasureEnum;
import com.daisuke.domain.model.SeverityEnum;
import com.daisuke.domain.model.TypeEnum;
import enumerations.CommonEnumerations.SONAR_BOOLEAN;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Utils {
    public static final String SONAR_DATE_PATTERN = "yyyy-MM-dd";

    public static String randomSonarBOOL() {
	@SuppressWarnings("rawtypes")
	Enum[] values = SONAR_BOOLEAN.values();
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
	return randomDate(startYear, endYear, SONAR_DATE_PATTERN);
    }

    public static String randomDate(int startYear, int endYear, String pattern) {
	Calendar startDate = new Calendar.Builder().setDate(startYear, 1, 1).build();
	Calendar endDate = new Calendar.Builder().setDate(endYear, 12, 31).build();
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
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

    public static String randomStringWithTemplate(String template, int count) {
	Optional<String> opt = Optional.ofNullable(template);
	return (opt.isPresent() && !template.isEmpty()) ? opt.get() : randomString(count, true);
    }

    public static List<String> randomStringList(int size, boolean isAlphanumeric) {
	List<String> result = new ArrayList<>();
	for (int i = 0; i < size; i++) {
	    int stringSize = RandomUtils.nextInt(1, 20);
	    result.add(randomString(stringSize, isAlphanumeric));
	}
	return result;
    }

    public static List<String> randomUUIDList(int size) {
	List<String> result = new ArrayList<>();
	for (int i = 0; i < size; i++) {
	    result.add(UUID.randomUUID().toString());
	}
	return result;
    }

    public static SeverityEnum randomSeverity() {
	SeverityEnum[] values = SeverityEnum.values();
	int index = randomNumber(0, values.length);
	return values[index];
    }

    public static TypeEnum randomType() {
	TypeEnum[] values = TypeEnum.values();
	int index = randomNumber(0, values.length);
	return values[index];
    }

    public static MeasureEnum randomMeasure() {
	MeasureEnum[] values = MeasureEnum.values();
	int index = randomNumber(0, values.length);
	return values[index];
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
	int index = randomNumber(0, enums.length);
	return enums[index].name();
    }

    @SuppressWarnings("rawtypes")
    public final static List<Enum> randomEnumList(int size, Enum[] enums) {
	List<Enum> result = new ArrayList<>();
	for (int i = 1; i <= size; i++) {
	    int index = randomNumber(0, enums.length);
	    result.add(enums[index]);
	}
	return Collections.unmodifiableList(result);
    }

    /**
     * 
     * @param size
     * @param enums
     * @return a list of random elements from {@code enums}. This list is immutable.
     */
    public final static List<String> randomEnumStringList(int size, @SuppressWarnings("rawtypes") Enum[] enums) {
	List<String> result = new ArrayList<>();
	for (int i = 1; i <= size; i++) {
	    int index = randomNumber(0, enums.length);
	    result.add(enums[index].name());
	}
	return Collections.unmodifiableList(result);
    }

    public static Rule randomRule() {
	Rule.Builder builder;
	String description = randomString(15, true);
	String language = randomEnumString(LanguageEnum.values());
	String key = randomString(20, true);
	RuleType type = RuleType.valueOf(randomEnumString(RuleType.values()));
	String severity = randomSeverity().name();
	builder = Rule.newBuilder().setHtmlDesc(description).setLang(language).setKey(key).setSeverity(severity)
		.setType(type);
	return builder.build();
    }

    public static LanguageEnum randomLanguage() {
	int index = RandomUtils.nextInt(0, LanguageEnum.values().length);
	return LanguageEnum.values()[index];
    }

    public static Rule randomRule(Rule template) {
	Rule.Builder builder;
	String description = randomStringWithTemplate(template.getHtmlDesc(), 15);
	String language = randomLanguage().name();
	String languageName = randomLanguage().getDescription();
	String key = randomStringWithTemplate(template.getKey(), 20);
	String name = randomStringWithTemplate(template.getName(), 20);
	RuleType type = Optional.ofNullable(template.getType()).orElse(RuleType.valueOf(randomType().name()));
	Optional<String> optSeverity = Optional.ofNullable(template.getSeverity());
	String severity = (optSeverity.isPresent() && !optSeverity.get().isEmpty()) ? optSeverity.get()
		: randomSeverity().name();
	builder = Rule.newBuilder().setHtmlDesc(description).setLang(language).setLangName(languageName).setKey(key)
		.setSeverity(severity).setType(type).setName(name);
	return builder.build();
    }
}
