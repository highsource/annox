package org.jvnet.annox.parser;

import org.jvnet.annox.model.annotation.value.XAnnotationValue;
import org.jvnet.annox.model.annotation.value.XCharAnnotationValue;
import org.jvnet.annox.parser.java.visitor.CharacterExpressionVisitor;

public class XCharSingleAnnotationFieldParser extends
		XSingleAnnotationFieldParser<Character, Character> {

	@Override
	public XAnnotationValue<Character> construct(Character value, Class<?> type) {
		return new XCharAnnotationValue(value);
	}

	@Override
	protected org.jvnet.annox.parser.java.visitor.ExpressionVisitor<Character> createExpressionVisitor(
			Class<?> type) {
		return new CharacterExpressionVisitor(type);
	}
}