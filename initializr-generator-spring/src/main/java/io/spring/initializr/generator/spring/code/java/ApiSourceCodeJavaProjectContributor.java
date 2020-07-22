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
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import io.spring.initializr.generator.io.template.TemplateRenderer;
import io.spring.initializr.generator.language.SourceStructure;
import io.spring.initializr.generator.project.ProjectDescription;
import io.spring.initializr.generator.project.contributor.ProjectContributor;

/**
 * {@link ProjectContributor} for the data-jpa source code in java language.
 *
 * @author Jayce Ma
 */
public class ApiSourceCodeJavaProjectContributor implements ProjectContributor {

	private final ProjectDescription description;

	private final TemplateRenderer templateRenderer;

	private final String templateRoot = "java/provider";

	public ApiSourceCodeJavaProjectContributor(ProjectDescription description, TemplateRenderer templateRenderer) {

		this.description = description;
		this.templateRenderer = templateRenderer;
	}

	@Override
	public void contribute(Path projectRoot) throws IOException {
		buildModel(projectRoot);

		buildApi(projectRoot);
	}

	private void buildModel(Path projectRoot) throws IOException {
		String subPackageName = "model";
		String templateName = "UserModel";
		render(this.description, this.templateRenderer, projectRoot, subPackageName, templateName);
	}

	private void buildApi(Path projectRoot) throws IOException {
		String subPackageName = "api";
		String templateName = "UserService";
		render(this.description, this.templateRenderer, projectRoot, subPackageName, templateName);
	}

	private void render(ProjectDescription description, TemplateRenderer templateRenderer, Path projectRoot,
			String subPackageName, String templateName) throws IOException {

		SourceStructure sourceStructure = description.getBuildSystem().getMainSource(projectRoot,
				description.getLanguage());
		String packaging = description.getPackageName() + "." + subPackageName;
		Path sourceCodePath = sourceStructure.createSourceFile(packaging, templateName);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("packageName", description.getPackageName());
		String template = this.templateRoot + "/" + subPackageName.replace(".", "/") + "/" + templateName + ".java";
		// 渲染模板
		String code = templateRenderer.render(template, params);

		Files.write(sourceCodePath, code.getBytes("UTF-8"));
	}

}
