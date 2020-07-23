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

package io.spring.initializr.generator.spring.code.java;

import io.spring.initializr.generator.condition.ConditionalOnLanguage;
import io.spring.initializr.generator.condition.ConditionalOnRequestedDependency;
import io.spring.initializr.generator.io.template.TemplateRenderer;
import io.spring.initializr.generator.language.java.JavaLanguage;
import io.spring.initializr.generator.project.ProjectDescription;
import io.spring.initializr.generator.project.ProjectGenerationConfiguration;

import org.springframework.context.annotation.Bean;

/**
 * Configuration for contributions specific to the generation of a project that will use
 * Java as its language and web dependency.
 *
 * @author Jayce Ma
 */
@ProjectGenerationConfiguration
@ConditionalOnLanguage(JavaLanguage.ID)
public class TemplateJavaProjectGenerationConfiguration {

	private final ProjectDescription description;

	private final TemplateRenderer templateRenderer;

	public TemplateJavaProjectGenerationConfiguration(ProjectDescription description,
			TemplateRenderer templateRenderer) {
		this.description = description;
		this.templateRenderer = templateRenderer;
	}

	@Bean
	@ConditionalOnRequestedDependency("web")
	public HelloControllerJavaSourceCodeTemplateContributor helloControllerContributor() {
		return new HelloControllerJavaSourceCodeTemplateContributor(this.description, this.templateRenderer);
	}

	@Bean
	public UserModelJavaSourceCodeTemplateContributor userModelContributor() {
		return new UserModelJavaSourceCodeTemplateContributor(this.description, this.templateRenderer);
	}

	@Bean
	public UserServiceJavaSourceCodeTemplateContributor userServiceContributor() {
		return new UserServiceJavaSourceCodeTemplateContributor(this.description, this.templateRenderer);
	}

	@Bean
	public UserServiceImplJavaSourceCodeTemplateContributor userServiceImplContributor() {
		return new UserServiceImplJavaSourceCodeTemplateContributor(this.description, this.templateRenderer);
	}

	@Bean
	@ConditionalOnRequestedDependency("data-jpa")
	public UserRepositoryJavaSourceCodeTemplateContributor userRepositoryContributor() {
		return new UserRepositoryJavaSourceCodeTemplateContributor(this.description, this.templateRenderer);
	}

	@Bean
	public UserDOJavaSourceCodeTemplateContributor userDOContributor() {
		return new UserDOJavaSourceCodeTemplateContributor(this.description, this.templateRenderer);
	}

	@Bean
	@ConditionalOnRequestedDependency("mybatis")
	public UserMapperJavaSourceCodeTemplateContributor userMapperContributor() {
		return new UserMapperJavaSourceCodeTemplateContributor(this.description, this.templateRenderer);
	}

}
