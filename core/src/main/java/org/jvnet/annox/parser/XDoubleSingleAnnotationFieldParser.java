package org.jvnet.annox.parser;

import org.jvnet.annox.model.annotation.value.XAnnotationValue;
import org.jvnet.annox.model.annotation.value.XDoubleAnnotationValue;
import org.jvnet.annox.parser.java.visitor.DoubleExpressionVisitor;

public class XDoubleSingleAnnotationFieldParser extends
		XSingleAnnotationFieldParser<Double, Double> {

	@Override
	public XAnnotationValue<Double> construct(Double value, Class<?> type) {
		return new XDoubleAnnotationValue(value);
	}

	@Override
	protected org.jvnet.annox.parser.java.visitor.ExpressionVisitor<Double> createExpressionVisitor(
			Class<?> type) {
		return new DoubleExpressionVisitor(type);
	}
}