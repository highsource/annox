package org.jvnet.annox.parser;

import org.jvnet.annox.model.annotation.value.XAnnotationValue;
import org.jvnet.annox.model.annotation.value.XShortAnnotationValue;
import org.jvnet.annox.parser.java.visitor.ShortExpressionVisitor;

public class XShortSingleAnnotationFieldParser extends
		XSingleAnnotationFieldParser<Short, Short> {

	@Override
	public XAnnotationValue<Short> construct(Short value, Class<?> type) {
		return new XShortAnnotationValue(value);
	}

	@Override
	protected org.jvnet.annox.parser.java.visitor.ExpressionVisitor<Short> createExpressionVisitor(
			Class<?> type) {
		return new ShortExpressionVisitor(type);
	}
}