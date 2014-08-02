package org.jvnet.annox.parser;

import japa.parser.ast.visitor.GenericVisitor;

import org.jvnet.annox.model.annotation.value.XAnnotationValue;
import org.jvnet.annox.model.annotation.value.XCharAnnotationValue;
import org.jvnet.annox.parser.java.visitor.CharacterExpressionVisitor;

public class XCharArrayAnnotationFieldParser extends
		XArrayAnnotationFieldParser<Character, Character> {

	@Override
	public XAnnotationValue<Character> construct(Character value, Class<?> type) {
		return new XCharAnnotationValue(value);
	}

	@Override
	protected GenericVisitor<Character, Void> createExpressionVisitor(
			Class<?> type) {
		return new CharacterExpressionVisitor(type);
	}
}