package org.jvnet.annox.parser;

import japa.parser.ast.visitor.GenericVisitor;

import org.jvnet.annox.model.annotation.value.XAnnotationValue;
import org.jvnet.annox.model.annotation.value.XIntAnnotationValue;
import org.jvnet.annox.parser.java.visitor.IntegerExpressionVisitor;

public class XIntArrayAnnotationFieldParser extends
		XArrayAnnotationFieldParser<Integer, Integer> {

	@Override
	public XAnnotationValue<Integer> construct(Integer value, Class<?> type) {
		return new XIntAnnotationValue(value);
	}

	@Override
	protected GenericVisitor<Integer, Void> createExpressionVisitor(
			Class<?> type) {
		return new IntegerExpressionVisitor(type);
	}
}