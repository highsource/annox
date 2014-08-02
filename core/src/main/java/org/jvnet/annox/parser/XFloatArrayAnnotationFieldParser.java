package org.jvnet.annox.parser;

import japa.parser.ast.visitor.GenericVisitor;

import org.jvnet.annox.model.annotation.value.XAnnotationValue;
import org.jvnet.annox.model.annotation.value.XFloatAnnotationValue;
import org.jvnet.annox.parser.java.visitor.FloatExpressionVisitor;

public class XFloatArrayAnnotationFieldParser extends
		XArrayAnnotationFieldParser<Float, Float> {

	@Override
	public XAnnotationValue<Float> construct(Float value, Class<?> type) {
		return new XFloatAnnotationValue(value);
	}

	@Override
	protected GenericVisitor<Float, Void> createExpressionVisitor(Class<?> type) {
		return new FloatExpressionVisitor(type);
	}
}