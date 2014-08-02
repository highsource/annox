package org.jvnet.annox.parser;

import japa.parser.ast.visitor.GenericVisitor;

import org.jvnet.annox.model.annotation.value.XAnnotationValue;
import org.jvnet.annox.model.annotation.value.XBooleanAnnotationValue;
import org.jvnet.annox.parser.java.visitor.BooleanExpressionVisitor;

public class XBooleanArrayAnnotationFieldParser extends
		XArrayAnnotationFieldParser<Boolean, Boolean> {

	@Override
	public XAnnotationValue<Boolean> construct(Boolean value, Class<?> type) {
		return new XBooleanAnnotationValue(value);
	}

	@Override
	protected GenericVisitor<Boolean, Void> createExpressionVisitor(
			Class<?> type) {
		return new BooleanExpressionVisitor(type);
	}
}