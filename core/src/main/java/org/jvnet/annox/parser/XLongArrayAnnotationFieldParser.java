package org.jvnet.annox.parser;

import japa.parser.ast.visitor.GenericVisitor;

import org.jvnet.annox.model.annotation.value.XAnnotationValue;
import org.jvnet.annox.model.annotation.value.XLongAnnotationValue;
import org.jvnet.annox.parser.java.visitor.LongExpressionVisitor;

public class XLongArrayAnnotationFieldParser extends
		XArrayAnnotationFieldParser<Long, Long> {

	@Override
	public XAnnotationValue<Long> construct(Long value, Class<?> type) {
		return new XLongAnnotationValue(value);
	}

	@Override
	protected GenericVisitor<Long, Void> createExpressionVisitor(Class<?> type) {
		return new LongExpressionVisitor(type);
	}
}