package org.jvnet.annox.parser.java.visitor;

import japa.parser.ast.expr.StringLiteralExpr;

public final class IntegerExpressionVisitor extends
		ExpressionVisitor<Integer> {
	public IntegerExpressionVisitor(Class<?> targetClass) {
		super(targetClass);
	}

	@Override
	public Integer visitDefault(StringLiteralExpr n, Void arg) {
		return Integer.valueOf(n.getValue());
	}
}