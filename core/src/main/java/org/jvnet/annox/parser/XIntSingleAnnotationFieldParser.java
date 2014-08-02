package org.jvnet.annox.parser;

import org.jvnet.annox.model.annotation.value.XAnnotationValue;
import org.jvnet.annox.model.annotation.value.XIntAnnotationValue;
import org.jvnet.annox.parser.java.visitor.IntegerExpressionVisitor;

public class XIntSingleAnnotationFieldParser extends
		XSingleAnnotationFieldParser<Integer, Integer> {

	@Override
	public XAnnotationValue<Integer> construct(Integer value, Class<?> type) {
		return new XIntAnnotationValue(value);
	}

	@Override
	protected org.jvnet.annox.parser.java.visitor.ExpressionVisitor<Integer> createExpressionVisitor(
			Class<?> type) {
		return new IntegerExpressionVisitor(type);
	}

}