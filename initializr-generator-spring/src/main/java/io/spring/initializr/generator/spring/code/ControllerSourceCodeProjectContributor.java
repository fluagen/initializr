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
package io.spring.initializr.generator.spring.code;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.nio.file.Path;

import io.spring.initializr.generator.language.Annotation;
import io.spring.initializr.generator.language.java.JavaCompilationUnit;
import io.spring.initializr.generator.language.java.JavaExpression;
import io.spring.initializr.generator.language.java.JavaMethodDeclaration;
import io.spring.initializr.generator.language.java.JavaReturnStatement;
import io.spring.initializr.generator.language.java.JavaSourceCode;
import io.spring.initializr.generator.language.java.JavaSourceCodeWriter;
import io.spring.initializr.generator.language.java.JavaTypeDeclaration;
import io.spring.initializr.generator.project.ProjectDescription;
import io.spring.initializr.generator.project.contributor.ProjectContributor;

/**
 * {@link ProjectContributor} for the controller source code.
 *
 * @author Jayce Ma
 */
public class ControllerSourceCodeProjectContributor implements ProjectContributor {

	private final ProjectDescription description;

	private final JavaSourceCode sourceCode;

	private final JavaSourceCodeWriter sourceCodeWriter;

	public ControllerSourceCodeProjectContributor(ProjectDescription description, JavaSourceCode sourceCode,
			JavaSourceCodeWriter sourceCodeWriter) {
		this.description = description;
		this.sourceCode = sourceCode;
		this.sourceCodeWriter = sourceCodeWriter;
	}

	@Override
	public void contribute(Path projectRoot) throws IOException {

		String applicationName = "HelloController";
		String packaging = this.description.getPackageName() + ".controller";
		JavaCompilationUnit compilationUnit = this.sourceCode.createCompilationUnit(packaging, applicationName);
		JavaTypeDeclaration typeDeclaration = compilationUnit.createTypeDeclaration(applicationName);

		typeDeclaration.annotate(Annotation.name("org.springframework.web.bind.annotation.RestController"));
		typeDeclaration.modifiers(Modifier.PUBLIC);
		typeDeclaration.addMethodDeclaration(JavaMethodDeclaration.method("greeting").modifiers(Modifier.PUBLIC)
				.returning("String").body(new JavaReturnStatement(new JavaExpression())));

		this.sourceCodeWriter.writeTo(
				this.description.getBuildSystem().getMainSource(projectRoot, this.description.getLanguage()),
				this.sourceCode);
	}

}
