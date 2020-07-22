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

/**
 * Callback for customizing the java's template.
 *
 * @author Jayce Ma
 */
@FunctionalInterface
public interface JavaTemplateRenderer {

	String customize();

	default void render(ProjectDescription description, TemplateRenderer templateRenderer, Path projectRoot,
			String subPackage, String templateName) throws IOException {
		SourceStructure sourceStructure = description.getBuildSystem().getMainSource(projectRoot,
				description.getLanguage());
		String packaging = description.getPackageName() + "." + subPackage;
		Path sourceCodePath = sourceStructure.createSourceFile(packaging, templateName);

		String templatePath = customize();

		Map<String, Object> params = new HashMap<>();
		params.put("rootPackage", description.getPackageName());
		String template = "java/" + templatePath + "/" + subPackage.replaceAll("\\.", "/") + "/" + templateName
				+ ".java";

		String code = templateRenderer.render(template, params);

		Files.write(sourceCodePath, code.getBytes("UTF-8"));
	}

}
