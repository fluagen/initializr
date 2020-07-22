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

import java.io.IOException;
import java.nio.file.Path;

import io.spring.initializr.generator.io.template.TemplateRenderer;
import io.spring.initializr.generator.project.ProjectDescription;
import io.spring.initializr.generator.project.contributor.ProjectContributor;

/**
 * {@link ProjectContributor} for the data-jpa source code in java language.
 *
 * @author Jayce Ma
 */
public class DataJpaSourceCodeJavaProjectContributor implements ProjectContributor {

	private final ProjectDescription description;

	private final TemplateRenderer templateRenderer;

	private final JavaTemplateRenderer javaTemplateRenderer = () -> "jpa";

	public DataJpaSourceCodeJavaProjectContributor(ProjectDescription description, TemplateRenderer templateRenderer) {
		this.description = description;
		this.templateRenderer = templateRenderer;
	}

	@Override
	public void contribute(Path projectRoot) throws IOException {

		buildEntity(projectRoot);

		buildRepository(projectRoot);

		buildService(projectRoot);
	}

	private void buildEntity(Path projectRoot) throws IOException {
		String subPackage = "dao.dataobject";
		String templateName = "UserDO";
		this.javaTemplateRenderer.render(this.description, this.templateRenderer, projectRoot, subPackage,
				templateName);
	}

	private void buildRepository(Path projectRoot) throws IOException {
		String subPackage = "repository";
		String templateName = "UserRepository";
		this.javaTemplateRenderer.render(this.description, this.templateRenderer, projectRoot, subPackage,
				templateName);
	}

	private void buildService(Path projectRoot) throws IOException {
		String subPackage = "service";
		String templateName = "UserServiceImpl";
		this.javaTemplateRenderer.render(this.description, this.templateRenderer, projectRoot, subPackage,
				templateName);
	}

}
