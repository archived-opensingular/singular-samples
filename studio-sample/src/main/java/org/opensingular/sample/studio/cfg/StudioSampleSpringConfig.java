package org.opensingular.sample.studio.cfg;

import org.opensingular.studio.app.spring.StudioSpringConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = "org.opensingular.sample.studio")
public class StudioSampleSpringConfig extends StudioSpringConfiguration {

}