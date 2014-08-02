package org.jvnet.annox.parser.java.visitor;

import japa.parser.ast.expr.FieldAccessExpr;

import org.jvnet.annox.util.ObjectUtils;

public final class EnumExpressionVisitor extends
		ExpressionVisitor<Enum<?>> {
	public EnumExpressionVisitor(Class<?> targetClass) {
		super(targetClass);
	}

	// TODO Implement handling enums as strings. 
	@Override
	public Enum<?> visit(FieldAccessExpr n, Void arg) {
		try {
			return (Enum<?>) ObjectUtils.valueOf(this.targetClass,
					n.getField());
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}