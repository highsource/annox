package org.jvnet.annox.parser;

import japa.parser.ast.visitor.GenericVisitor;

import org.jvnet.annox.model.annotation.value.XAnnotationValue;
import org.jvnet.annox.model.annotation.value.XShortAnnotationValue;
import org.jvnet.annox.parser.java.visitor.ShortExpressionVisitor;

public class XShortArrayAnnotationFieldParser extends
		XArrayAnnotationFieldParser<Short, Short> {

	@Override
	public XAnnotationValue<Short> construct(Short value, Class<?> type) {
		return new XShortAnnotationValue(value);
	}

	@Override
	protected GenericVisitor<Short, Void> createExpressionVisitor(Class<?> type) {
		return new ShortExpressionVisitor(type);
	}
}