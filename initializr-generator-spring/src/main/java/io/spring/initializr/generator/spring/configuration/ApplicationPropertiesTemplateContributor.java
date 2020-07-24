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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

import io.spring.initializr.generator.buildsystem.Dependency;
import io.spring.initializr.generator.io.template.TemplateRenderer;
import io.spring.initializr.generator.project.ProjectDescription;
import io.spring.initializr.generator.project.contributor.ProjectContributor;

import org.springframework.util.FileCopyUtils;

/**
 * {@link ProjectContributor} that contributes application.properties file to a generated
 * project.
 *
 * @author Jayce Ma
 */
public class ApplicationPropertiesTemplateContributor implements ProjectContributor {

	private final String relativePath = "src/main/resources/application.properties";

	private final String resourcePattern = "configuration/application.properties";

	private final ProjectDescription description;

	private final TemplateRenderer templateRenderer;

	public ApplicationPropertiesTemplateContributor(ProjectDescription description, TemplateRenderer templateRenderer) {

		this.description = description;
		this.templateRenderer = templateRenderer;
	}

	@Override
	public void contribute(Path projectRoot) throws IOException {

		Path output = projectRoot.resolve(this.relativePath);
		if (!Files.exists(output)) {
			Files.createDirectories(output.getParent());
			Files.createFile(output);
		}
		String template = this.resourcePattern;
		Map<String, Object> params = new HashMap<>();
		Map<String, Dependency> dependencies = this.description.getRequestedDependencies();
		if (dependencies.containsKey("mysql")) {
			params.put("hasMysqlDependency", true);
		}
		if (dependencies.containsKey("mybatis")) {
			params.put("hasMybatisDependency", true);
		}
		String code = this.templateRenderer.render(template, params);

		FileCopyUtils.copy(code.getBytes("UTF-8"), Files.newOutputStream(output, StandardOpenOption.APPEND));
	}

}
