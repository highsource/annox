package org.jvnet.annox.parser;

import japa.parser.ast.visitor.GenericVisitor;

import org.jvnet.annox.model.annotation.value.XAnnotationValue;
import org.jvnet.annox.model.annotation.value.XDoubleAnnotationValue;
import org.jvnet.annox.parser.java.visitor.DoubleExpressionVisitor;

public class XDoubleArrayAnnotationFieldParser extends
		XArrayAnnotationFieldParser<Double, Double> {

	@Override
	public XAnnotationValue<Double> construct(Double value, Class<?> type) {
		return new XDoubleAnnotationValue(value);
	}

	@Override
	protected GenericVisitor<Double, Void> createExpressionVisitor(Class<?> type) {
		return new DoubleExpressionVisitor(type);
	}
}