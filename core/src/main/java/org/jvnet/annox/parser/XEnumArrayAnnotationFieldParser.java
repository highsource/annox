package org.jvnet.annox.parser;

import japa.parser.ast.visitor.GenericVisitor;

import org.jvnet.annox.model.annotation.value.XAnnotationValue;
import org.jvnet.annox.model.annotation.value.XEnumAnnotationValue;
import org.jvnet.annox.parser.java.visitor.EnumExpressionVisitor;

public class XEnumArrayAnnotationFieldParser extends
		XArrayAnnotationFieldParser<Enum<?>, Enum<?>> {

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public XAnnotationValue<Enum<?>> construct(Enum<?> value, Class<?> type) {
		final XEnumAnnotationValue annotationValue = new XEnumAnnotationValue(
				value);
		return annotationValue;
	}

	@Override
	protected GenericVisitor<Enum<?>, Void> createExpressionVisitor(Class<?> type) {
		return new EnumExpressionVisitor(type);
	}

}