package org.jvnet.annox.parser;

import japa.parser.ast.visitor.GenericVisitor;

import org.jvnet.annox.model.annotation.value.XAnnotationValue;
import org.jvnet.annox.model.annotation.value.XStringAnnotationValue;
import org.jvnet.annox.parser.java.visitor.StringExpressionVisitor;

public class XStringArrayAnnotationFieldParser extends
		XArrayAnnotationFieldParser<String, String> {

	@Override
	public XAnnotationValue<String> construct(String value, Class<?> type) {
		return new XStringAnnotationValue(value);
	}

	@Override
	protected GenericVisitor<String, Void> createExpressionVisitor(Class<?> type) {
		return new StringExpressionVisitor(type);
	}
}