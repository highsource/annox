package org.jvnet.annox.model.annotation.value;

import java.lang.annotation.Annotation;

import org.jvnet.annox.model.XAnnotation;

public class XAnnotationAnnotationValue<A extends Annotation> extends
		XStaticAnnotationValue<A> {

	private final XAnnotation<A> xannotation;

	public XAnnotationAnnotationValue(A value, XAnnotation<A> xannotation) {
		super(value);
		this.xannotation = xannotation;
	}

	public XAnnotation<A> getXannotation() {
		return xannotation;
	}

	@Override
	public <P> P accept(XAnnotationValueVisitor<P> visitor) {
		return visitor.visit(this);
	}
}
