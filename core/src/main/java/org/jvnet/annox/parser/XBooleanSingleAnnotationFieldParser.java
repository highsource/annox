package org.jvnet.annox.parser;

import org.jvnet.annox.model.annotation.value.XAnnotationValue;
import org.jvnet.annox.model.annotation.value.XBooleanAnnotationValue;
import org.jvnet.annox.parser.java.visitor.BooleanExpressionVisitor;

public class XBooleanSingleAnnotationFieldParser extends
		XSingleAnnotationFieldParser<Boolean, Boolean> {

	@Override
	public XAnnotationValue<Boolean> construct(Boolean value, Class<?> type) {
		return new XBooleanAnnotationValue(value);
	}

	@Override
	protected org.jvnet.annox.parser.java.visitor.ExpressionVisitor<Boolean> createExpressionVisitor(
			Class<?> type) {
		return new BooleanExpressionVisitor(type);
	}
}