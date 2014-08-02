package org.jvnet.annox.parser;

import org.jvnet.annox.model.annotation.value.XAnnotationValue;
import org.jvnet.annox.model.annotation.value.XStringAnnotationValue;
import org.jvnet.annox.parser.java.visitor.StringExpressionVisitor;

public class XStringSingleAnnotationFieldParser extends
		XSingleAnnotationFieldParser<String, String> {

	@Override
	public XAnnotationValue<String> construct(String value, Class<?> type) {
		return new XStringAnnotationValue(value);
	}

	@Override
	protected org.jvnet.annox.parser.java.visitor.ExpressionVisitor<String> createExpressionVisitor(
			Class<?> type) {
		return new StringExpressionVisitor(type);
	}
}