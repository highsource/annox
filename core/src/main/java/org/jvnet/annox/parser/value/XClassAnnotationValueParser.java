package org.jvnet.annox.parser.value;

import org.apache.commons.lang3.ClassUtils;
import org.jvnet.annox.model.annotation.value.XAnnotationValue;
import org.jvnet.annox.model.annotation.value.XClassAnnotationValue;
import org.jvnet.annox.parser.exception.ValueParseException;
import org.jvnet.annox.parser.java.visitor.ClassExpressionVisitor;
import org.jvnet.annox.parser.java.visitor.ExpressionVisitor;

public class XClassAnnotationValueParser extends
		XAnnotationValueParser<Class<?>, Class<?>> {

	@Override
	public XAnnotationValue<Class<?>> parse(String value, Class<?> type)
			throws ValueParseException {
		try {
			final Class<?> _class = ClassUtils.getClass(value);
			@SuppressWarnings({ "unchecked", "rawtypes" })
			final XAnnotationValue<Class<?>> annotationValue = new XClassAnnotationValue(
					_class);
			return annotationValue;
		} catch (ClassNotFoundException cnfex) {
			// TODO parse string
			throw new UnsupportedOperationException(cnfex);
		}
	}

	@Override
	public XAnnotationValue<Class<?>> construct(Class<?> value, Class<?> type) {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		final XAnnotationValue<Class<?>> annotationValue = new XClassAnnotationValue(
				value);
		return annotationValue;
	}

	@Override
	public ExpressionVisitor<XAnnotationValue<Class<?>>> createExpressionVisitor(
			Class<?> type) {
		return new ClassExpressionVisitor(type);
	}

}
