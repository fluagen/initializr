/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.spring.initializr.generator.spring.configuration;

import io.spring.initializr.generator.io.template.TemplateRenderer;
import io.spring.initializr.generator.project.ProjectDescription;
import io.spring.initializr.generator.project.ProjectGenerationConfiguration;

import org.springframework.context.annotation.Bean;

/**
 * Temaplate configuration for application-related contributions to a generated project.
 *
 * @author Stephane Nicoll
 */
@ProjectGenerationConfiguration
public class TemplateConfiguationProjectGenerationConfiguration {

	private final ProjectDescription description;

	private final TemplateRenderer templateRenderer;

	public TemplateConfiguationProjectGenerationConfiguration(ProjectDescription description,
			TemplateRenderer templateRenderer) {
		this.description = description;
		this.templateRenderer = templateRenderer;
	}

	@Bean
	ApplicationPropertiesTemplateContributor applicationContributor() {
		return new ApplicationPropertiesTemplateContributor(this.description, this.templateRenderer);
	}

	@Bean
	MybatisConfigXMLTemplateContributor mybatisConfigXMLContributor() {
		return new MybatisConfigXMLTemplateContributor(this.description, this.templateRenderer);
	}

	@Bean
	GeneratorConfigXMLTemplateContributor mybatisGeneratorConfigXMLContributor() {
		return new GeneratorConfigXMLTemplateContributor(this.description, this.templateRenderer);
	}

}
