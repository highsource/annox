package org.jvnet.annox.parser;

import org.jvnet.annox.model.annotation.value.XAnnotationValue;
import org.jvnet.annox.model.annotation.value.XEnumAnnotationValue;
import org.jvnet.annox.parser.java.visitor.EnumExpressionVisitor;
import org.jvnet.annox.parser.java.visitor.ExpressionVisitor;

public class XEnumSingleAnnotationFieldParser extends
		XSingleAnnotationFieldParser<Enum<?>, Enum<?>> {

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public XAnnotationValue<Enum<?>> construct(Enum<?> value, Class<?> type) {
		final XEnumAnnotationValue annotationValue = new XEnumAnnotationValue(
				value);
		return annotationValue;
	}

	@Override
	protected ExpressionVisitor<Enum<?>> createExpressionVisitor(Class<?> type) {
		return new EnumExpressionVisitor(type);
	}
}