package org.jvnet.annox.parser.java.visitor;

import japa.parser.ast.expr.StringLiteralExpr;

import org.jvnet.annox.util.ValueParseException;

public final class CharacterExpressionVisitor extends
		ExpressionVisitor<Character> {
	public CharacterExpressionVisitor(Class<?> targetClass) {
		super(targetClass);
	}

	@Override
	public Character visitDefault(StringLiteralExpr n, Void arg) {
		final String value = n.getValue();
		if (value == null) {
			throw new RuntimeException(new ValueParseException(value,
					this.targetClass));
		} else if (value.length() != 1) {
			throw new RuntimeException(new ValueParseException(value,
					this.targetClass));
		} else {
			return Character.valueOf(value.charAt(0));
		}
	}
}