package org.jvnet.annox.japa.parser;

import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.TypeDeclaration;
import japa.parser.ast.expr.AnnotationExpr;

import java.io.StringReader;
import java.text.MessageFormat;
import java.util.List;

import org.apache.commons.lang3.Validate;

public class AnnotationExprParser {

	public List<AnnotationExpr> parse(String text) throws ParseException {
		Validate.notNull(text);
		final String classText = text + "\n" + "public class Dummy{}";
		final StringReader reader = new StringReader(classText);
		final CompilationUnit compilationUnit = JavaParser.parse(reader, true);
		final List<TypeDeclaration> typeDeclarations = compilationUnit
				.getTypes();
		if (typeDeclarations.size() > 1) {
			throw new IllegalArgumentException(
					MessageFormat
							.format("Annotation [{0}] could not be parsed, it contains an unexpected type declaration.",
									text));
		}
		final TypeDeclaration typeDeclaration = typeDeclarations.get(0);
		assert typeDeclaration instanceof ClassOrInterfaceDeclaration : MessageFormat
				.format("Expected [{0}] as type declaration.",
						ClassOrInterfaceDeclaration.class.getName());
		final ClassOrInterfaceDeclaration classDeclaration = (ClassOrInterfaceDeclaration) typeDeclaration;
		assert "Dummy".equals(classDeclaration.getName()) : "Expected [Dummy] as class name.";
		final List<AnnotationExpr> annotations = typeDeclaration
				.getAnnotations();
		return annotations;
	}
}
