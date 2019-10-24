package com.daisuke.persistence;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import com.daisuke.persistence.jpa.config.JPAConfiguration;

@SpringBootConfiguration
@EnableAutoConfiguration
public class JpaTestConfig extends JPAConfiguration {

}
