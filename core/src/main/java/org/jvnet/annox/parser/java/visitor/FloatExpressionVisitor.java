package org.jvnet.annox.parser.java.visitor;

import japa.parser.ast.expr.StringLiteralExpr;

public final class FloatExpressionVisitor extends
		ExpressionVisitor<Float> {
	public FloatExpressionVisitor(Class<?> targetClass) {
		super(targetClass);
	}

	@Override
	public Float visitDefault(StringLiteralExpr n, Void arg) {
		return Float.valueOf(n.getValue());
	}
}