/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.builder.script.springboot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.CamelContext;
import org.apache.camel.CamelContextAware;
import org.apache.camel.builder.script.RubyLanguage;
import org.apache.camel.spi.LanguageCustomizer;
import org.apache.camel.spring.boot.CamelAutoConfiguration;
import org.apache.camel.spring.boot.LanguageConfigurationProperties;
import org.apache.camel.spring.boot.util.GroupCondition;
import org.apache.camel.util.IntrospectionSupport;
import org.apache.camel.util.ObjectHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.AllNestedConditions;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Generated by camel-package-maven-plugin - do not edit this file!
 */
@Generated("org.apache.camel.maven.packaging.SpringBootAutoConfigurationMojo")
@Configuration
@Conditional(RubyLanguageAutoConfiguration.Condition.class)
@AutoConfigureAfter(CamelAutoConfiguration.class)
@EnableConfigurationProperties({LanguageConfigurationProperties.class,
        RubyLanguageConfiguration.class})
public class RubyLanguageAutoConfiguration extends AllNestedConditions {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(RubyLanguageAutoConfiguration.class);
    @Autowired
    private CamelContext camelContext;
    @Autowired(required = false)
    private List<LanguageCustomizer<RubyLanguage>> customizers;
    @Autowired
    private LanguageConfigurationProperties globalConfiguration;
    @Autowired
    private RubyLanguageConfiguration languageConfiguration;

    public RubyLanguageAutoConfiguration() {
        super(ConfigurationPhase.REGISTER_BEAN);
    }

    @ConditionalOnBean(CamelContext.class)
    public static class OnCamelContext {
    }

    @ConditionalOnBean(CamelAutoConfiguration.class)
    public static class OnCamelAutoConfiguration {
    }

    @ConditionalOnBean(CamelAutoConfiguration.class)
    public static class Condition extends GroupCondition {
        public Condition() {
            super("camel.component", "camel.component.ruby");
        }
    }

    @Bean(name = "ruby-language")
    @Scope("prototype")
    @ConditionalOnClass(CamelContext.class)
    @ConditionalOnMissingBean(RubyLanguage.class)
    public RubyLanguage configureRubyLanguage() throws Exception {
        RubyLanguage language = new RubyLanguage();
        if (CamelContextAware.class.isAssignableFrom(RubyLanguage.class)) {
            CamelContextAware contextAware = CamelContextAware.class
                    .cast(language);
            if (contextAware != null) {
                contextAware.setCamelContext(camelContext);
            }
        }
        Map<String, Object> parameters = new HashMap<>();
        IntrospectionSupport.getProperties(languageConfiguration, parameters,
                null, false);
        IntrospectionSupport.setProperties(camelContext,
                camelContext.getTypeConverter(), language, parameters);
        boolean useCustomizers = globalConfiguration.getCustomizer()
                .isEnabled()
                && languageConfiguration.getCustomizer().isEnabled();
        if (useCustomizers && ObjectHelper.isNotEmpty(customizers)) {
            for (LanguageCustomizer<RubyLanguage> customizer : customizers) {
                LOGGER.debug("Configure language {}, with customizer {}",
                        language, customizer);
                customizer.customize(language);
            }
        }
        return language;
    }
}