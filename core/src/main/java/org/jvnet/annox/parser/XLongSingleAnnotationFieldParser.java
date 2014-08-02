package org.jvnet.annox.parser;

import org.jvnet.annox.model.annotation.value.XAnnotationValue;
import org.jvnet.annox.model.annotation.value.XLongAnnotationValue;
import org.jvnet.annox.parser.java.visitor.LongExpressionVisitor;

public class XLongSingleAnnotationFieldParser extends
		XSingleAnnotationFieldParser<Long, Long> {

	@Override
	public XAnnotationValue<Long> construct(Long value, Class<?> type) {
		return new XLongAnnotationValue(value);
	}

	@Override
	protected org.jvnet.annox.parser.java.visitor.ExpressionVisitor<Long> createExpressionVisitor(
			Class<?> type) {
		return new LongExpressionVisitor(type);
	}
}