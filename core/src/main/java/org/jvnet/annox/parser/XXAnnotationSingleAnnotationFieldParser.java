package org.jvnet.annox.parser;

import japa.parser.ast.Node;
import japa.parser.ast.expr.AnnotationExpr;
import japa.parser.ast.expr.Expression;

import java.lang.annotation.Annotation;
import java.text.MessageFormat;

import org.jvnet.annox.annotation.NoSuchAnnotationFieldException;
import org.jvnet.annox.japa.parser.ast.visitor.AbstractGenericExpressionVisitor;
import org.jvnet.annox.model.XAnnotation;
import org.jvnet.annox.model.annotation.field.XAnnotationField;
import org.jvnet.annox.model.annotation.field.XSingleAnnotationField;
import org.jvnet.annox.model.annotation.value.XAnnotationAnnotationValue;
import org.jvnet.annox.model.annotation.value.XAnnotationValue;
import org.jvnet.annox.model.annotation.value.XXAnnotationAnnotationValue;
import org.jvnet.annox.util.AnnotationElementUtils;
import org.w3c.dom.Element;

public class XXAnnotationSingleAnnotationFieldParser<A extends Annotation>
		extends XAnnotationFieldParser<A, XAnnotation<A>> {

	@Override
	@SuppressWarnings("unchecked")
	public XAnnotationField<A> parse(Element annotationElement, String name,
			Class<?> type) throws AnnotationElementParseException {

		final Element element = AnnotationElementUtils.getFieldElement(
				annotationElement, name);

		if (element == null) {
			return null;
		} else {
			try {
				final XAnnotation<A> xannotation = (XAnnotation<A>) XAnnotationParser.GENERIC
						.parse(element);

				final XAnnotationValue<A> value = new XXAnnotationAnnotationValue<A>(
						xannotation);
				return new XSingleAnnotationField<A>(name, type, value);
			} catch (AnnotationElementParseException aepex) {
				throw new AnnotationElementParseException(annotationElement,
						aepex);
			}
		}
	}

	@Override
	public XAnnotationField<A> parse(Expression annotationElement, String name,
			Class<?> type) throws AnnotationExpressionParseException {

		final AnnotationExpr element = annotationElement.accept(
				new AbstractGenericExpressionVisitor<AnnotationExpr, Void>() {
					@Override
					public AnnotationExpr visitDefault(Node n, Void arg) {
						throw new IllegalArgumentException(
								MessageFormat
										.format("Unexpected expression [{0}], only annotation expressions are expected.",
												n));
					}

					@Override
					public AnnotationExpr visitDefault(AnnotationExpr n,
							Void arg) {
						return n;
					}
				}, null);

		if (element == null) {
			return null;
		} else {
			try {
				@SuppressWarnings("unchecked")
				final XAnnotation<A> xannotation = (XAnnotation<A>) XAnnotationParser.GENERIC
						.parse(element);
				final XAnnotationValue<A> value = new XXAnnotationAnnotationValue<A>(
						xannotation);
				return new XSingleAnnotationField<A>(name, type, value);
			} catch (AnnotationExpressionParseException aepex) {
				throw new AnnotationExpressionParseException(annotationElement,
						aepex);
			}
		}
	}

	@Override
	public XAnnotationField<A> parse(Annotation annotation, String name,
			Class<?> type) throws NoSuchAnnotationFieldException {
		final A value = getAnnotationFieldValue(annotation, name);
		return new XSingleAnnotationField<A>(name, type,
				new XAnnotationAnnotationValue<A>(value));
	}

	@Override
	public XAnnotationField<A> construct(String name,
			XAnnotation<A> annotation, Class<?> type) {
		return new XSingleAnnotationField<A>(name, type,
				new XXAnnotationAnnotationValue<A>(annotation));
	}

}