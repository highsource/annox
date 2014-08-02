package org.jvnet.annox.parser.java.visitor;

import japa.parser.ast.expr.StringLiteralExpr;

public final class StringExpressionVisitor extends
		ExpressionVisitor<String> {
	public StringExpressionVisitor(Class<?> targetClass) {
		super(targetClass);
	}

	@Override
	public String visitDefault(StringLiteralExpr n, Void arg) {
		return String.valueOf(n.getValue());
	}
}