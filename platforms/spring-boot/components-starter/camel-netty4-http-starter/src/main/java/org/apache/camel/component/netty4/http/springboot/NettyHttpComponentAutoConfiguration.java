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
package org.apache.camel.component.netty4.http.springboot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.CamelContext;
import org.apache.camel.component.netty4.http.NettyHttpComponent;
import org.apache.camel.spi.ComponentCustomizer;
import org.apache.camel.spring.boot.CamelAutoConfiguration;
import org.apache.camel.spring.boot.ComponentConfigurationProperties;
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
import org.springframework.context.annotation.Lazy;

/**
 * Generated by camel-package-maven-plugin - do not edit this file!
 */
@Generated("org.apache.camel.maven.packaging.SpringBootAutoConfigurationMojo")
@Configuration
@Conditional(NettyHttpComponentAutoConfiguration.Condition.class)
@AutoConfigureAfter(CamelAutoConfiguration.class)
@EnableConfigurationProperties({ComponentConfigurationProperties.class,
        NettyHttpComponentConfiguration.class})
public class NettyHttpComponentAutoConfiguration extends AllNestedConditions {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(NettyHttpComponentAutoConfiguration.class);
    @Autowired
    private CamelContext camelContext;
    @Autowired(required = false)
    private List<ComponentCustomizer<NettyHttpComponent>> customizers;
    @Autowired
    private ComponentConfigurationProperties globalConfiguration;
    @Autowired
    private NettyHttpComponentConfiguration componentConfiguration;

    public NettyHttpComponentAutoConfiguration() {
        super(ConfigurationPhase.REGISTER_BEAN);
    }

    @ConditionalOnBean(CamelContext.class)
    public static class OnCamelContext {
    }

    @ConditionalOnBean(CamelAutoConfiguration.class)
    public static class OnCamelAutoConfiguration {
    }

    public static class Condition extends GroupCondition {
        public Condition() {
            super("camel.component", "camel.component.netty4-http");
        }
    }

    @Lazy
    @Bean(name = "netty4-http-component")
    @ConditionalOnClass(CamelContext.class)
    @ConditionalOnMissingBean(NettyHttpComponent.class)
    public NettyHttpComponent configureNettyHttpComponent() throws Exception {
        NettyHttpComponent component = new NettyHttpComponent();
        component.setCamelContext(camelContext);
        Map<String, Object> parameters = new HashMap<>();
        IntrospectionSupport.getProperties(componentConfiguration, parameters,
                null, false);
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            Object value = entry.getValue();
            Class<?> paramClass = value.getClass();
            if (paramClass.getName().endsWith("NestedConfiguration")) {
                Class nestedClass = null;
                try {
                    nestedClass = (Class) paramClass.getDeclaredField(
                            "CAMEL_NESTED_CLASS").get(null);
                    HashMap<String, Object> nestedParameters = new HashMap<>();
                    IntrospectionSupport.getProperties(value, nestedParameters,
                            null, false);
                    Object nestedProperty = nestedClass.newInstance();
                    IntrospectionSupport.setProperties(camelContext,
                            camelContext.getTypeConverter(), nestedProperty,
                            nestedParameters);
                    entry.setValue(nestedProperty);
                } catch (NoSuchFieldException e) {
                }
            }
        }
        IntrospectionSupport.setProperties(camelContext,
                camelContext.getTypeConverter(), component, parameters);
        boolean useCustomizers = globalConfiguration.getCustomizer()
                .isEnabled()
                && componentConfiguration.getCustomizer().isEnabled();
        if (useCustomizers && ObjectHelper.isNotEmpty(customizers)) {
            for (ComponentCustomizer<NettyHttpComponent> customizer : customizers) {
                LOGGER.debug("Configure component {}, with customizer {}",
                        component, customizer);
                customizer.customize(component);
            }
        }
        return component;
    }
}