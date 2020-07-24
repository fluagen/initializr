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
 * {@link ProjectContributor} for the web controller source code in java language.
 *
 * @author Jayce Ma
 */
public class HelloControllerJavaSourceCodeTemplateContributor implements ProjectContributor {

	private final String subPackage = "controller";

	private final String sourceCodeName = "HelloController";

	private final String template = "java/controller/HelloController.java";

	private final ProjectDescription description;

	private final TemplateRenderer templateRenderer;

	public HelloControllerJavaSourceCodeTemplateContributor(ProjectDescription description,
			TemplateRenderer templateRenderer) {
		this.description = description;
		this.templateRenderer = templateRenderer;
	}

	@Override
	public void contribute(Path projectRoot) throws IOException {

		SourceStructure sourceStructure = this.description.getBuildSystem().getMainSource(projectRoot,
				this.description.getLanguage());
		String packaging = this.description.getPackageName() + "." + this.subPackage;
		Path sourceCodePath = sourceStructure.createSourceFile(packaging, this.sourceCodeName);

		Map<String, Object> params = new HashMap<>();
		params.put("rootPackage", this.description.getPackageName());

		String code = this.templateRenderer.render(this.template, params);
		Files.write(sourceCodePath, code.getBytes("UTF-8"));

	}

}
