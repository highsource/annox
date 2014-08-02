package org.jvnet.annox.parser;

import japa.parser.ast.Node;
import japa.parser.ast.expr.AnnotationExpr;
import japa.parser.ast.expr.Expression;
import japa.parser.ast.expr.MarkerAnnotationExpr;
import japa.parser.ast.expr.MemberValuePair;
import japa.parser.ast.expr.NormalAnnotationExpr;
import japa.parser.ast.expr.SingleMemberAnnotationExpr;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.jvnet.annox.Constants;
import org.jvnet.annox.annotation.AnnotationClassNotFoundException;
import org.jvnet.annox.annotation.NoSuchAnnotationFieldException;
import org.jvnet.annox.japa.parser.ast.visitor.AbstractGenericExpressionVisitor;
import org.jvnet.annox.model.XAnnotation;
import org.jvnet.annox.model.annotation.field.XAnnotationField;
import org.w3c.dom.Element;

public class XAnnotationParser {

	public static final XAnnotationParser GENERIC = new XAnnotationParser();

	private final ClassLoader classLoader;

	/**
	 * Default constructor.
	 */
	public XAnnotationParser() {
		classLoader = this.getClass().getClassLoader();
	}

	/**
	 * Constructor with a specified class loader.
	 * 
	 * @param classLoader
	 *            the class loader which should be used to get classes (like
	 *            annotations)
	 */
	public XAnnotationParser(ClassLoader classLoader) {
		super();
		this.classLoader = classLoader;
	}

	public XAnnotation<?> parse(final Annotation annotation) {
		Validate.notNull(annotation, "Annotation must not be null.");

		final Class<? extends Annotation> annotationClass = annotation
				.annotationType();

		final XAnnotationField<?>[] fields = parseFields(annotation,
				annotationClass);

		@SuppressWarnings({ "rawtypes", "unchecked" })
		final XAnnotation<?> xannotation = new XAnnotation(annotationClass, fields);
		return xannotation;

	}

	public XAnnotation<?>[] parse(final Annotation[] annotations) {
		Validate.noNullElements(annotations,
				"Annotations must not contain nulls.");
		final XAnnotation<?>[] xannotations = new XAnnotation[annotations.length];
		for (int index = 0; index < annotations.length; index++) {
			final Annotation annotation = annotations[index];
			xannotations[index] = parse(annotation);
		}
		return xannotations;
	}

	@SuppressWarnings("unchecked")
	public XAnnotation<?> parse(final Element annotationElement)
			throws AnnotationElementParseException {
		Validate.notNull(annotationElement,
				"Annotation element must not be null.");

		final String name = annotationElement.getLocalName();

		final String classAttribute = annotationElement.getAttributeNS(
				Constants.NAMESPACE_URI, "class");

		final String className;
		if (!StringUtils.isEmpty(classAttribute)) {
			className = classAttribute;
		} else {
			final String namespaceURI = annotationElement.getNamespaceURI();

			if (namespaceURI != null
					&& namespaceURI.startsWith(Constants.NAMESPACE_URI_PREFIX)) {
				final String containerPrefix = namespaceURI
						.substring(Constants.NAMESPACE_URI_PREFIX.length());
				className = containerPrefix + "." + name.replace('.', '$');
			} else {
				className = name;
			}
		}

		try {
			final Class<?> draftClass = classLoader.loadClass(className);

			if (!Annotation.class.isAssignableFrom(draftClass))
				throw new AnnotationElementParseException(annotationElement,
						new IllegalArgumentException(MessageFormat.format(
								"The class [{0}] is not an annotation class.",
								draftClass.getName())));

			final Class<? extends Annotation> annotationClass = (Class<? extends Annotation>) draftClass;

			final XAnnotationField<?>[] fields = parseFields(annotationElement,
					annotationClass);
			@SuppressWarnings("rawtypes")
			final XAnnotation<?> xannotation = new XAnnotation(annotationClass,
					fields);
			return xannotation;
		} catch (ClassNotFoundException cnfex) {
			throw new AnnotationElementParseException(annotationElement,
					new AnnotationClassNotFoundException(className, cnfex));
		} catch (AnnotationElementParseException cnfex) {
			throw new AnnotationElementParseException(annotationElement,
					new AnnotationClassNotFoundException(className, cnfex));
		}

	}

	public XAnnotation<?>[] parse(final Element[] annotationElements)
			throws AnnotationElementParseException {
		Validate.noNullElements(annotationElements,
				"Annotation elements must not contain null.");
		final XAnnotation<?>[] xannotations = new XAnnotation[annotationElements.length];
		for (int index = 0; index < annotationElements.length; index++) {
			final Element annotationElement = annotationElements[index];
			try {
				xannotations[index] = parse(annotationElement);
			} catch (AnnotationElementParseException aepex) {
				throw new AnnotationElementParseException(annotationElement,
						aepex);
			}
		}
		return xannotations;
	}

	@SuppressWarnings("unchecked")
	public XAnnotation<?> parse(final AnnotationExpr annotationElement)
			throws AnnotationExpressionParseException {
		Validate.notNull(annotationElement,
				"Annotation expression must not be null.");
		final String className = annotationElement.getName().toString();

		try {

			final Class<?> draftClass = ClassUtils.getClass(classLoader,
					className);

			if (!Annotation.class.isAssignableFrom(draftClass))
				throw new AnnotationExpressionParseException(annotationElement,
						new IllegalArgumentException(MessageFormat.format(
								"The class [{0}] is not an annotation class.",
								draftClass.getName())));

			final Class<? extends Annotation> annotationClass = (Class<? extends Annotation>) draftClass;

			final XAnnotationField<?>[] fields = parseFields(annotationElement,
					annotationClass);
			@SuppressWarnings("rawtypes")
			final XAnnotation<?> xannotation = new XAnnotation(annotationClass,
					fields);
			return xannotation;
		} catch (ClassNotFoundException cnfex) {
			throw new AnnotationExpressionParseException(annotationElement,
					new AnnotationClassNotFoundException(className, cnfex));
		}
	}

	public XAnnotation<?>[] parse(final AnnotationExpr[] annotationElements)
			throws AnnotationExpressionParseException {
		Validate.noNullElements(annotationElements,
				"Annotation elements must not contain null.");
		final XAnnotation<?>[] xannotations = new XAnnotation[annotationElements.length];
		for (int index = 0; index < annotationElements.length; index++) {
			final AnnotationExpr annotationElement = annotationElements[index];
			try {
				xannotations[index] = parse(annotationElement);
			} catch (AnnotationExpressionParseException aepex) {
				throw new AnnotationExpressionParseException(annotationElement,
						aepex);
			}
		}
		return xannotations;
	}

	public XAnnotationField<?>[] parseFields(Element annotationElement,
			Class<? extends Annotation> annotationClass)
			throws AnnotationElementParseException {

		final Method[] methods = annotationClass.getMethods();

		final Map<String, Class<?>> fieldsMap = new HashMap<String, Class<?>>();

		for (final Method method : methods) {
			if (!Annotation.class.equals(method.getDeclaringClass())) {
				final String name = method.getName();
				final Class<?> type = method.getReturnType();
				fieldsMap.put(name, type);
			}
		}

		final List<XAnnotationField<?>> fields = new ArrayList<XAnnotationField<?>>(
				fieldsMap.size());

		for (Entry<String, Class<?>> entry : fieldsMap.entrySet()) {
			final String name = entry.getKey();
			final Class<?> type = entry.getValue();
			try {
				final XAnnotationField<?> field = parseField(annotationElement,
						name, type);
				if (field != null) {
					fields.add(field);
				}
			} catch (AnnotationElementParseException aepex) {
				throw new AnnotationElementParseException(annotationElement,
						aepex);

			}

		}
		return fields.toArray(new XAnnotationField<?>[fields.size()]);
	}

	public XAnnotationField<?>[] parseFields(Annotation annotation,
			Class<? extends Annotation> annotationClass) {

		final Method[] methods = annotationClass.getMethods();

		final List<XAnnotationField<?>> fields = new ArrayList<XAnnotationField<?>>(
				methods.length);

		for (final Method method : methods) {
			if (!Annotation.class.equals(method.getDeclaringClass())) {
				final String name = method.getName();
				final Class<?> type = method.getReturnType();
				try {
					fields.add(parseField(annotation, name, type));
				} catch (NoSuchAnnotationFieldException nsafex) {
					// We're sure that method exists, exception can not happen
					throw new AssertionError(nsafex);
				}
			}
		}
		return fields.toArray(new XAnnotationField<?>[fields.size()]);
	}

	public XAnnotationField<?>[] parseFields(AnnotationExpr annotationExpr,
			Class<? extends Annotation> annotationClass)
			throws AnnotationExpressionParseException {
		final List<MemberValuePair> pairs = annotationExpr
				.accept(new AbstractGenericExpressionVisitor<List<MemberValuePair>, Void>() {
					@Override
					public List<MemberValuePair> visitDefault(Node n, Void arg) {
						throw new IllegalArgumentException();
					}

					@Override
					public List<MemberValuePair> visit(NormalAnnotationExpr n,
							Void arg) {
						return n.getPairs();
					}

					@Override
					public List<MemberValuePair> visit(MarkerAnnotationExpr n,
							Void arg) {
						return Collections.emptyList();
					}

					@Override
					public List<MemberValuePair> visit(
							SingleMemberAnnotationExpr n, Void arg) {
						return Collections.singletonList(new MemberValuePair(
								"value", n.getMemberValue()));
					}
				}, null);

		final Method[] methods = annotationClass.getMethods();

		final Map<String, Class<?>> fieldsMap = new HashMap<String, Class<?>>();

		for (final Method method : methods) {
			if (!Annotation.class.equals(method.getDeclaringClass())) {
				final String name = method.getName();
				final Class<?> type = method.getReturnType();
				fieldsMap.put(name, type);
			}
		}

		final List<XAnnotationField<?>> fields = new ArrayList<XAnnotationField<?>>(
				fieldsMap.size());

		for (MemberValuePair memberValuePair : pairs) {
			final String name = memberValuePair.getName();
			final Expression value = memberValuePair.getValue();
			final Class<?> type = fieldsMap.get(name);
			if (type != null) {
				fields.add(parseField(value, name, type));
			} else {
				// TODO
			}
		}
		return fields.toArray(new XAnnotationField<?>[fields.size()]);
	}

	@SuppressWarnings("unchecked")
	public XAnnotationField<?> parseField(Expression annotationExpression,
			String name, Class<?> type)
			throws AnnotationExpressionParseException {

		final XAnnotationField<?> field = XAnnotationFieldParser.GENERIC.parse(
				annotationExpression, name, type);
		return field;
	}

	@SuppressWarnings("unchecked")
	public XAnnotationField<?> parseField(Element annotationElement,
			String name, Class<?> type) throws AnnotationElementParseException {

		final XAnnotationField<?> field = XAnnotationFieldParser.GENERIC.parse(
				annotationElement, name, type);
		return field;
	}

	@SuppressWarnings("unchecked")
	public XAnnotationField<?> parseField(Annotation annotation, String name,
			Class<?> type) throws NoSuchAnnotationFieldException {

		final XAnnotationField<?> field = XAnnotationFieldParser.GENERIC.parse(
				annotation, name, type);
		return field;
	}

}