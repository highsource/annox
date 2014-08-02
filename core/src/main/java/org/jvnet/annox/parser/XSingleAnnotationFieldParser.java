package org.jvnet.annox.parser;

import japa.parser.ast.expr.Expression;

import java.lang.annotation.Annotation;

import org.apache.commons.lang3.Validate;
import org.jvnet.annox.annotation.NoSuchAnnotationFieldException;
import org.jvnet.annox.model.annotation.field.XAnnotationField;
import org.jvnet.annox.model.annotation.field.XSingleAnnotationField;
import org.jvnet.annox.model.annotation.value.XAnnotationValue;
import org.jvnet.annox.parser.java.visitor.ExpressionVisitor;
import org.jvnet.annox.util.AnnotationElementUtils;
import org.jvnet.annox.util.ObjectUtils;
import org.jvnet.annox.util.ValueParseException;
import org.w3c.dom.Element;

public abstract class XSingleAnnotationFieldParser<T, V> extends
		XAnnotationFieldParser<T, V> {

	@Override
	public XAnnotationField<T> parse(Element element, String name, Class<?> type)
			throws AnnotationElementParseException {
		Validate.notNull(element, "Element must not be null.");
		Validate.notNull(name, "Field name must not be null.");
		Validate.notNull(type, "Type must not be null.");
		final String draft = AnnotationElementUtils
				.getFieldValue(element, name);

		if (draft == null) {
			return null;
		} else {
			try {
				final V value = parse(draft, type);
				return construct(name, value, type);
			} catch (ValueParseException vpex) {
				throw new AnnotationElementParseException(element, vpex);
			}
		}
	}

	public XAnnotationField<T> parse(Annotation annotation, String name,
			Class<?> type) throws NoSuchAnnotationFieldException {
		final V value = this.<V> getAnnotationFieldValue(annotation, name);
		return construct(name, value, type);
	}

	public V parse(String draft, Class<?> type) throws ValueParseException {
		try {
			@SuppressWarnings("unchecked")
			final V value = (V) ObjectUtils.valueOf(type, draft);
			return value;
		} catch (ClassNotFoundException cnfex) {
			throw new ValueParseException(draft, type, cnfex);
		} catch (IllegalArgumentException iaex) {
			throw new ValueParseException(draft, type, iaex);
		}

	}

	public final XAnnotationField<T> construct(String name, V value,
			Class<?> type) {
		final XAnnotationValue<T> fieldValue = construct(value, type);
		return new XSingleAnnotationField<T>(name, type, fieldValue);
	}

	public abstract XAnnotationValue<T> construct(V value, Class<?> type);

	@Override
	public XAnnotationField<T> parse(Expression expression, String name,
			Class<?> type) throws AnnotationExpressionParseException {
		final ExpressionVisitor<V> expressionVisitor = createExpressionVisitor(type);
		try {

			final V value = expression.accept(expressionVisitor, null);
			return construct(name, value, type);
		} catch (RuntimeException rex) {
			if (rex.getCause() != null) {
				throw new AnnotationExpressionParseException(expression,
						rex.getCause());
			} else {
				throw new AnnotationExpressionParseException(expression, rex);
			}
		}
	}

	protected abstract ExpressionVisitor<V> createExpressionVisitor(
			Class<?> type);

}