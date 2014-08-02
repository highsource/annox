package org.jvnet.annox.parser.java.visitor;

import japa.parser.ast.expr.StringLiteralExpr;

public final class ByteExpressionVisitor extends
		ExpressionVisitor<Byte> {
	public ByteExpressionVisitor(Class<?> targetClass) {
		super(targetClass);
	}

	@Override
	public Byte visitDefault(StringLiteralExpr n, Void arg) {
		return Byte.valueOf(n.getValue());
	}
}