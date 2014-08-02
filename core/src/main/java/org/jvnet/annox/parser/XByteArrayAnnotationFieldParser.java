package org.jvnet.annox.parser;

import japa.parser.ast.visitor.GenericVisitor;

import org.jvnet.annox.model.annotation.value.XAnnotationValue;
import org.jvnet.annox.model.annotation.value.XByteAnnotationValue;
import org.jvnet.annox.parser.java.visitor.ByteExpressionVisitor;

public class XByteArrayAnnotationFieldParser extends
		XArrayAnnotationFieldParser<Byte, Byte> {

	@Override
	public XAnnotationValue<Byte> construct(Byte value, Class<?> type) {
		return new XByteAnnotationValue(value);
	}

	@Override
	protected GenericVisitor<Byte, Void> createExpressionVisitor(Class<?> type) {
		return new ByteExpressionVisitor(type);
	}
}