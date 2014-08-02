package org.jvnet.annox.parser;

import org.jvnet.annox.model.annotation.value.XAnnotationValue;
import org.jvnet.annox.model.annotation.value.XFloatAnnotationValue;
import org.jvnet.annox.parser.java.visitor.FloatExpressionVisitor;

public class XFloatSingleAnnotationFieldParser extends
		XSingleAnnotationFieldParser<Float, Float> {

	@Override
	public XAnnotationValue<Float> construct(Float value, Class<?> type) {
		return new XFloatAnnotationValue(value);
	}

	@Override
	protected org.jvnet.annox.parser.java.visitor.ExpressionVisitor<Float> createExpressionVisitor(
			Class<?> type) {
		return new FloatExpressionVisitor(type);
	}
}