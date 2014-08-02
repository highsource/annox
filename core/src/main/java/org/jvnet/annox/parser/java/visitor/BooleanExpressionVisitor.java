package org.jvnet.annox.parser.java.visitor;

import japa.parser.ast.expr.BooleanLiteralExpr;

public final class BooleanExpressionVisitor extends
		ExpressionVisitor<Boolean> {
	public BooleanExpressionVisitor(Class<?> targetClass) {
		super(targetClass);
	}

	@Override
	public Boolean visit(BooleanLiteralExpr n, Void arg) {
		return n.getValue();
	}
}