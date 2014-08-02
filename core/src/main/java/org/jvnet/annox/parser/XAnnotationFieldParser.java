package org.jvnet.annox.parser;

import japa.parser.ast.expr.Expression;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.jvnet.annox.annotation.NoSuchAnnotationFieldException;
import org.jvnet.annox.model.XAnnotation;
import org.jvnet.annox.model.annotation.field.XAnnotationField;
import org.w3c.dom.Element;

public abstract class XAnnotationFieldParser<T, V> {

	public abstract XAnnotationField<T> parse(Element element, String name,
			Class<?> type) throws AnnotationElementParseException;

	public abstract XAnnotationField<T> parse(Expression expression,
			String name, Class<?> type)
			throws AnnotationExpressionParseException;

	public abstract XAnnotationField<T> parse(Annotation annotation,
			String name, Class<?> type) throws NoSuchAnnotationFieldException;

	public abstract XAnnotationField<T> construct(String name, V value,
			Class<?> type);

	@SuppressWarnings("unchecked")
	public <U> U getAnnotationFieldValue(Annotation annotation, String name)
			throws NoSuchAnnotationFieldException {
		final Class<? extends Annotation> annotationClass = annotation
				.annotationType();
		try {
			final Method method = annotationClass.getMethod(name);
			final U value = (U) method.invoke(annotation);
			return value;
		} catch (NoSuchMethodException nsmex) {
			throw new NoSuchAnnotationFieldException(annotationClass, name,
					nsmex);
		} catch (IllegalAccessException iaex) {
			throw new AssertionError(iaex);
		} catch (InvocationTargetException itex) {
			throw new AssertionError(itex);
		}
	}

	public static final XBooleanSingleAnnotationFieldParser BOOLEAN = new XBooleanSingleAnnotationFieldParser();

	public static final XByteSingleAnnotationFieldParser BYTE = new XByteSingleAnnotationFieldParser();

	public static final XIntSingleAnnotationFieldParser INT = new XIntSingleAnnotationFieldParser();

	public static final XLongSingleAnnotationFieldParser LONG = new XLongSingleAnnotationFieldParser();

	public static final XShortSingleAnnotationFieldParser SHORT = new XShortSingleAnnotationFieldParser();

	public static final XCharSingleAnnotationFieldParser CHAR = new XCharSingleAnnotationFieldParser();

	public static final XClassSingleAnnotationFieldParser CLASS = new XClassSingleAnnotationFieldParser();

	public static final XDoubleSingleAnnotationFieldParser DOUBLE = new XDoubleSingleAnnotationFieldParser();

	public static final XFloatSingleAnnotationFieldParser FLOAT = new XFloatSingleAnnotationFieldParser();

	public static final XEnumSingleAnnotationFieldParser ENUM = new XEnumSingleAnnotationFieldParser();

	public static final XStringSingleAnnotationFieldParser STRING = new XStringSingleAnnotationFieldParser();

	@SuppressWarnings("rawtypes")
	public static final XAnnotationFieldParser<?, ?> ANNOTATION = new XAnnotationSingleAnnotationFieldParser();

	@SuppressWarnings("rawtypes")
	public static final XAnnotationFieldParser<?, ?> XANNOTATION = new XXAnnotationSingleAnnotationFieldParser();

	public static final XBooleanArrayAnnotationFieldParser BOOLEAN_ARRAY = new XBooleanArrayAnnotationFieldParser();

	public static final XByteArrayAnnotationFieldParser BYTE_ARRAY = new XByteArrayAnnotationFieldParser();

	public static final XIntArrayAnnotationFieldParser INT_ARRAY = new XIntArrayAnnotationFieldParser();

	public static final XLongArrayAnnotationFieldParser LONG_ARRAY = new XLongArrayAnnotationFieldParser();

	public static final XShortArrayAnnotationFieldParser SHORT_ARRAY = new XShortArrayAnnotationFieldParser();

	public static final XCharArrayAnnotationFieldParser CHAR_ARRAY = new XCharArrayAnnotationFieldParser();

	public static final XClassArrayAnnotationFieldParser CLASS_ARRAY = new XClassArrayAnnotationFieldParser();

	public static final XDoubleArrayAnnotationFieldParser DOUBLE_ARRAY = new XDoubleArrayAnnotationFieldParser();

	public static final XFloatArrayAnnotationFieldParser FLOAT_ARRAY = new XFloatArrayAnnotationFieldParser();

	public static final XEnumArrayAnnotationFieldParser ENUM_ARRAY = new XEnumArrayAnnotationFieldParser();

	public static final XStringArrayAnnotationFieldParser STRING_ARRAY = new XStringArrayAnnotationFieldParser();

	@SuppressWarnings("rawtypes")
	public static final XAnnotationArrayAnnotationFieldParser ANNOTATION_ARRAY = new XAnnotationArrayAnnotationFieldParser();

	@SuppressWarnings("rawtypes")
	public static final XXAnnotationArrayAnnotationFieldParser XANNOTATION_ARRAY = new XXAnnotationArrayAnnotationFieldParser();

	public static XAnnotationFieldParser<?, ?> detectType(Class<?> theClass) {
		if (theClass == null)
			throw new IllegalArgumentException("Class must not be null.");

		if (theClass.isArray()) {
			final XAnnotationFieldParser<?, ?> componentType = detectType(theClass
					.getComponentType());
			if (componentType == BOOLEAN)
				return BOOLEAN_ARRAY;
			else if (componentType == BYTE)
				return BYTE_ARRAY;
			else if (componentType == INT)
				return INT_ARRAY;
			else if (componentType == LONG)
				return LONG_ARRAY;
			else if (componentType == SHORT)
				return SHORT_ARRAY;
			else if (componentType == CHAR)
				return CHAR_ARRAY;
			else if (componentType == CLASS)
				return CLASS_ARRAY;
			else if (componentType == DOUBLE)
				return DOUBLE_ARRAY;
			else if (componentType == FLOAT)
				return FLOAT_ARRAY;
			else if (componentType == ENUM)
				return ENUM_ARRAY;
			else if (componentType == STRING)
				return STRING_ARRAY;
			else if (componentType == ANNOTATION)
				return ANNOTATION_ARRAY;
			else if (componentType == XANNOTATION)
				return XANNOTATION_ARRAY;
			else
				throw new IllegalArgumentException(
						"Unknown annotation field type.");

		} else {
			if (Boolean.class.equals(theClass) || Boolean.TYPE.equals(theClass)) {
				return BOOLEAN;
			} else if (Byte.class.equals(theClass)
					|| Byte.TYPE.equals(theClass)) {
				return BYTE;
			} else if (Integer.class.equals(theClass)
					|| Integer.TYPE.equals(theClass)) {
				return INT;
			} else if (Long.class.equals(theClass)
					|| Long.TYPE.equals(theClass)) {
				return LONG;
			} else if (Short.class.equals(theClass)
					|| Short.TYPE.equals(theClass)) {
				return SHORT;
			} else if (Character.class.equals(theClass)
					|| Character.TYPE.equals(theClass)) {
				return CHAR;
			} else if (Double.class.equals(theClass)
					|| Double.TYPE.equals(theClass)) {
				return DOUBLE;
			} else if (Float.class.equals(theClass)
					|| Float.TYPE.equals(theClass)) {
				return FLOAT;
			} else if (Class.class.equals(theClass)) {
				return CLASS;
			} else if (String.class.equals(theClass)) {
				return STRING;
			} else if (Enum.class.isAssignableFrom(theClass)) {
				return ENUM;
			} else if (Annotation.class.isAssignableFrom(theClass)) {
				return ANNOTATION;
			} else if (XAnnotation.class.isAssignableFrom(theClass)) {
				return XANNOTATION;
			} else {
				throw new IllegalArgumentException(
						"Unknown annotation field type [" + theClass.getName()
								+ "].");
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public static final XGenericFieldParser GENERIC = new XGenericFieldParser();

	public static <T, V> XAnnotationFieldParser<T, V> generic() {
		@SuppressWarnings("unchecked")
		final XAnnotationFieldParser<T, V> parser = GENERIC;
		return parser;
	}
}
