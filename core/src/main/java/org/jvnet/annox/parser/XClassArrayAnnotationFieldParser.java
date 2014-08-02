package org.jvnet.annox.parser;

import japa.parser.ast.visitor.GenericVisitor;

import java.text.MessageFormat;

import org.jvnet.annox.model.annotation.value.XAnnotationValue;
import org.jvnet.annox.model.annotation.value.XClassAnnotationValue;
import org.jvnet.annox.model.annotation.value.XClassByNameAnnotationValue;
import org.jvnet.annox.parser.java.visitor.ClassExpressionVisitor;
import org.jvnet.annox.util.ValueParseException;

public class XClassArrayAnnotationFieldParser extends
		XArrayAnnotationFieldParser<Class<?>, Object> {

	@Override
	public Object[] parse(String[] draft, Class<?> type)
			throws ValueParseException {
		try {
			return super.parse(draft, type);
		} catch (ValueParseException ex) {
			return draft;
		}
	}

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
	protected GenericVisitor<Object, Void> createExpressionVisitor(Class<?> type) {
		return new ClassExpressionVisitor(type);
	}
}