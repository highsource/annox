package org.jvnet.annox.parser.java.visitor;

import japa.parser.ast.expr.StringLiteralExpr;

public final class LongExpressionVisitor extends
		ExpressionVisitor<Long> {
	public LongExpressionVisitor(Class<?> targetClass) {
		super(targetClass);
	}

	@Override
	public Long visitDefault(StringLiteralExpr n, Void arg) {
		return Long.valueOf(n.getValue());
	}
}