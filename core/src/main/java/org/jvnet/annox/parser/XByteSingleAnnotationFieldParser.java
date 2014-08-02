package org.jvnet.annox.parser;

import org.jvnet.annox.model.annotation.value.XAnnotationValue;
import org.jvnet.annox.model.annotation.value.XByteAnnotationValue;
import org.jvnet.annox.parser.java.visitor.ByteExpressionVisitor;

public class XByteSingleAnnotationFieldParser extends XSingleAnnotationFieldParser<Byte, Byte> {

	@Override
	public XAnnotationValue<Byte> construct(Byte value, Class<?> type) {
		return new XByteAnnotationValue(value);
	}

	@Override
	protected org.jvnet.annox.parser.java.visitor.ExpressionVisitor<Byte> createExpressionVisitor(
			Class<?> type) {
		return new ByteExpressionVisitor(type);
	}
}