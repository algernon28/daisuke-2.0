package com.daisuke.application;

import java.lang.management.ManagementFactory;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication(scanBasePackages = { "com.daisuke.application", "com.daisuke.persistence",
	"com.daisuke.adapters.sonarqube" })
@Slf4j
public class DaisukeApplication implements ApplicationRunner {
    private static final String USAGE = "usage: java  -jar ./app.jar --spring.config.location=file://<path>/myfile.yml";
    private static ApplicationContext applicationContext;

    /**
     * usage:
     * {@code java -jar ./app.jar --spring.config.location=file://<path>/myfile.yaml}
     * 
     * @param args application arguments
     */
    public static void main(String[] args) {
	String mode = args != null && args.length > 0 ? args[0] : null;
	if (args == null || args.length < 1) {
	    log.error("No configuration path parameter passed! -> {}", USAGE);
	    System.exit(-1);
	}
	log.debug("PID:" + ManagementFactory.getRuntimeMXBean().getName() + " Application mode:" + mode + " context:"
		+ applicationContext);

	if (applicationContext != null && mode != null && "stop".equals(mode)) {
	    System.exit(SpringApplication.exit(applicationContext, new ExitCodeGenerator() {
		@Override
		public int getExitCode() {
		    return 0;
		}
	    }));
	} else {
	    ConfigurableApplicationContext ctx = SpringApplication.run(DaisukeApplication.class, args);
	    log.info("ctxenv: {}", ctx.getEnvironment());
	    if (log.isDebugEnabled()) {
		log.debug("PID:" + ManagementFactory.getRuntimeMXBean().getName() + " Application started context:"
			+ applicationContext);
	    }
	}
	log.info("args: {}", Arrays.asList(args).stream().collect(Collectors.joining("|")));
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
	List<String> locations = args.getOptionValues("spring.config.location");
	String pathArg = null;
	if (!locations.isEmpty()) {
	    pathArg = locations.stream().findFirst().orElse("classpath:/");
	}
	URI configURI = null;
	try {
	    configURI = new URI(pathArg);
	} catch (URISyntaxException e) {
	    log.error("Path [{}] not valid!", pathArg, e);
	    throw new IllegalArgumentException(e);
	    // System.exit(-1);
	}
	log.debug("URI found: {}", configURI);
    }
}
