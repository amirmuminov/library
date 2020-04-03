package kz.muminov.iitu.library.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan("kz.muminov.iitu.library")
@PropertySource("application.properties")
@EnableScheduling
public class AnnotationConfiguration {
}
