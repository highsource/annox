package org.jvnet.annox.parser;

import java.text.MessageFormat;

import org.jvnet.annox.model.annotation.value.XAnnotationValue;
import org.jvnet.annox.model.annotation.value.XClassAnnotationValue;
import org.jvnet.annox.model.annotation.value.XClassByNameAnnotationValue;
import org.jvnet.annox.parser.java.visitor.ClassExpressionVisitor;
import org.jvnet.annox.parser.java.visitor.ExpressionVisitor;
import org.jvnet.annox.util.ValueParseException;

public class XClassSingleAnnotationFieldParser extends
		XSingleAnnotationFieldParser<Class<?>, Object> {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public XAnnotationValue<Class<?>> construct(Object value, Class<?> type) {
		if (value instanceof Class<?>) {
			return new XClassAnnotationValue((Class<?>) value);
		} else if (value instanceof String) {
			return new XClassByNameAnnotationValue((String) value);
		} else {
			throw new IllegalArgumentException(MessageFormat.format(
					"Unexpected value [{0}].", value));
		}
	}

	@Override
	public Object parse(String draft, Class<?> type) {
		try {
			return super.parse(draft, type);
		} catch (ValueParseException vpex) {
			return draft;
		}
	}

	@Override
	protected ExpressionVisitor<Object> createExpressionVisitor(
			final Class<?> type) {
		return new ClassExpressionVisitor(type);
	}
}