package org.jvnet.annox.parser.java.visitor;

import japa.parser.ast.expr.ClassExpr;
import japa.parser.ast.type.ClassOrInterfaceType;
import japa.parser.ast.type.PrimitiveType;
import japa.parser.ast.type.ReferenceType;
import japa.parser.ast.type.Type;
import japa.parser.ast.type.VoidType;
import japa.parser.ast.type.WildcardType;

public final class ClassExpressionVisitor extends
		ExpressionVisitor<Object> {
	public ClassExpressionVisitor(Class<?> targetClass) {
		super(targetClass);
	}

	@Override
	public Object visit(ClassExpr n, Void arg) {
		return n.getType().accept(
				new ExpressionVisitor<Object>(this.targetClass) {

					@Override
					public Object visit(ClassOrInterfaceType n, Void arg) {
						// TODO Scopes
						// TODO unknown clases
						// TODO We'll need a more complex construct for classes here
						final String className = n.toString();
						try {
							return Class.forName(className);
						} catch (ClassNotFoundException cnfex) {
							return className;
						}
					}

					@Override
					public Object visit(ReferenceType n, Void arg) {
						// TODO consider arrayCount
						final Type type = n.getType();
						return type.accept(this, arg);
					}

					@Override
					public Object visit(VoidType n, Void arg) {
						return Void.class;
					}

					@Override
					public Object visit(PrimitiveType n, Void arg) {
						switch (n.getType()) {
						case Boolean:
							return boolean.class;
						case Char:
							return char.class;
						case Byte:
							return byte.class;
						case Short:
							return short.class;
						case Int:
							return int.class;
						case Long:
							return long.class;
						case Double:
							return double.class;
						case Float:
							return double.class;
						default:
							throw new IllegalArgumentException();
						}
					}

					@Override
					public Object visit(WildcardType n, Void arg) {
						// TODO Do we need to support this?
						// ? extends T
						// ? super T
						throw new UnsupportedOperationException();
					}

				}, null);
	}
}