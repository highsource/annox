package org.jvnet.annox.parser;

import japa.parser.ast.expr.Expression;

import java.lang.annotation.Annotation;

import org.jvnet.annox.annotation.NoSuchAnnotationFieldException;
import org.jvnet.annox.model.annotation.field.XAnnotationField;
import org.w3c.dom.Element;

public class XGenericFieldParser<T, V> extends XAnnotationFieldParser<T, V> {

	@Override
	public XAnnotationField<T> parse(Element element, String name, Class<?> type)
			throws AnnotationElementParseException {

		@SuppressWarnings("unchecked")
		final XAnnotationFieldParser<T, V> parser = (XAnnotationFieldParser<T, V>) detectType(type);
		return parser.parse(element, name, type);
	}

	@Override
	public XAnnotationField<T> parse(Annotation annotation, String name,
			Class<?> type) throws NoSuchAnnotationFieldException {
		@SuppressWarnings("unchecked")
		final XAnnotationFieldParser<T, V> parser = (XAnnotationFieldParser<T, V>) detectType(type);
		return parser.parse(annotation, name, type);
	}

	@Override
	public XAnnotationField<T> construct(String name, V value, Class<?> type) {
		@SuppressWarnings("unchecked")
		final XAnnotationFieldParser<T, V> parser = (XAnnotationFieldParser<T, V>) detectType(type);
		return parser.construct(name, value, type);
	}

	@Override
	public XAnnotationField<T> parse(Expression expression, String name,
			Class<?> type) throws AnnotationExpressionParseException {
		@SuppressWarnings("unchecked")
		final XAnnotationFieldParser<T, V> parser = (XAnnotationFieldParser<T, V>) detectType(type);
		return parser.parse(expression, name, type);
	}

}