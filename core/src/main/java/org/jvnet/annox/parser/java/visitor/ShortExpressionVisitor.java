package org.jvnet.annox.parser.java.visitor;

import japa.parser.ast.expr.StringLiteralExpr;

public final class ShortExpressionVisitor extends
		ExpressionVisitor<Short> {
	public ShortExpressionVisitor(Class<?> targetClass) {
		super(targetClass);
	}

	@Override
	public Short visitDefault(StringLiteralExpr n, Void arg) {
		return Short.valueOf(n.getValue());
	}
}