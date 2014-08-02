package org.jvnet.annox.model.annotation.value;

import java.lang.annotation.Annotation;

public class XAnnotationAnnotationValue<A extends Annotation> extends
		XStaticAnnotationValue<A> {

	public XAnnotationAnnotationValue(A value) {
		super(value);
	}

	@Override
	public <P> P accept(XAnnotationValueVisitor<P> visitor) {
		return visitor.visit(this);
	}
}
