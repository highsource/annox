package org.jvnet.annox.parser.java.visitor;

import japa.parser.ast.expr.StringLiteralExpr;

public final class DoubleExpressionVisitor extends
		ExpressionVisitor<Double> {
	public DoubleExpressionVisitor(Class<?> targetClass) {
		super(targetClass);
	}

	@Override
	public Double visitDefault(StringLiteralExpr n, Void arg) {
		return Double.valueOf(n.getValue());
	}
}