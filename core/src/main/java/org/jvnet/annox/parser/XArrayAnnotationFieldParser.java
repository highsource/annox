package org.jvnet.annox.parser;

import japa.parser.ast.expr.ArrayInitializerExpr;
import japa.parser.ast.expr.Expression;
import japa.parser.ast.visitor.GenericVisitor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.text.MessageFormat;
import java.util.List;

import org.jvnet.annox.annotation.NoSuchAnnotationFieldException;
import org.jvnet.annox.model.annotation.field.XAnnotationField;
import org.jvnet.annox.model.annotation.field.XArrayAnnotationField;
import org.jvnet.annox.model.annotation.value.XAnnotationValue;
import org.jvnet.annox.parser.java.visitor.ExpressionVisitor;
import org.jvnet.annox.util.AnnotationElementUtils;
import org.jvnet.annox.util.ClassUtils;
import org.jvnet.annox.util.ObjectUtils;
import org.jvnet.annox.util.ValueParseException;
import org.w3c.dom.Element;

public abstract class XArrayAnnotationFieldParser<T, V> extends
		XAnnotationFieldParser<T[], V[]> {

	@Override
	public XAnnotationField<T[]> parse(Element element, String name,
			Class<?> type) throws AnnotationElementParseException {
		final String[] draft = AnnotationElementUtils.getFieldValues(element,
				name);
		try {
			final V[] value = parse(draft, type.getComponentType());
			return construct(name, value, type);
		} catch (ValueParseException vpex) {
			throw new AnnotationElementParseException(element, vpex);
		}
	}

	@SuppressWarnings("unchecked")
	public XAnnotationField<T[]> parse(Annotation annotation, String name,
			Class<?> type) throws NoSuchAnnotationFieldException {

		final Object values = getAnnotationFieldValue(annotation, name);
		final int length = Array.getLength(values);

		final V[] value = (V[]) Array.newInstance(
				ClassUtils.primitiveToWrapper(type.getComponentType()), length);
		for (int index = 0; index < length; index++) {
			Object object = Array.get(values, index);
			value[index] = (V) object;
		}
		return construct(name, value, type);
	}

	public final XAnnotationField<T[]> construct(String name, V[] value,
			Class<?> type) {
		if (!type.isArray()) {
			throw new IllegalArgumentException(MessageFormat.format(
					"Type [{0}] is expected to be an array type.", type));
		}
		final Class<?> componentType = type.getComponentType();
		@SuppressWarnings("unchecked")
		final XAnnotationValue<T>[] fieldValues = (XAnnotationValue<T>[]) new XAnnotationValue[value.length];
		for (int index = 0; index < value.length; index++) {
			final V item = value[index];
			fieldValues[index] = construct(item, componentType);
		}
		final XArrayAnnotationField<T> annotationField = new XArrayAnnotationField<T>(
				name, type, fieldValues);
		return annotationField;
	}

	public abstract XAnnotationValue<T> construct(V value,
			Class<?> componentType);

	@SuppressWarnings("unchecked")
	public V[] parse(String[] draft, Class<?> type) throws ValueParseException {

		try {
			return (V[]) ObjectUtils.valueOf(type, draft);
		} catch (ClassNotFoundException cnfex) {
			throw new ValueParseException(draft, type, cnfex);
		} catch (IllegalArgumentException iaex) {
			throw new ValueParseException(draft, type, iaex);
		}
	}

	@Override
	public XAnnotationField<T[]> parse(final Expression expression,
			final String name, final Class<?> type)
			throws AnnotationExpressionParseException {
		final GenericVisitor<V, Void> expressionVisitor = createExpressionVisitor(type
				.getComponentType());
		try {
			return expression.accept(
					new ExpressionVisitor<XAnnotationField<T[]>>(type) {

						@Override
						public XAnnotationField<T[]> visit(
								ArrayInitializerExpr n, Void arg) {
							final List<Expression> expressions = n.getValues();

							@SuppressWarnings("unchecked")
							final V[] values = (V[]) Array.newInstance(
									ClassUtils
											.primitiveToWrapper(this.targetClass
													.getComponentType()),
									expressions.size());

							for (int index = 0; index < expressions.size(); index++) {
								values[index] = expressions.get(index).accept(
										expressionVisitor, null);
							}
							return construct(name, values, type);
						}

					}, null);
		} catch (RuntimeException rex) {
			if (rex.getCause() != null) {
				throw new AnnotationExpressionParseException(expression,
						rex.getCause());
			} else {
				throw new AnnotationExpressionParseException(expression, rex);
			}
		}
	}

	protected abstract GenericVisitor<V, Void> createExpressionVisitor(
			Class<?> type);
}