package org.jvnet.annox.model.annotation.value;

import java.lang.annotation.Annotation;

import org.apache.commons.lang3.Validate;
import org.jvnet.annox.model.XAnnotation;

public class XXAnnotationAnnotationValue<A extends Annotation> extends
		XDynamicAnnotationValue<A> {

	private final XAnnotation<A> value;

	public XXAnnotationAnnotationValue(XAnnotation<A> value) {
		this.value = Validate.notNull(value);
	}

	@Override
	public A getValue() {
		return this.value.getResult();
	}

	@Override
	protected Object getInternalValue() {
		return this.value;
	}

	@Override
	public <P> P accept(XAnnotationValueVisitor<P> visitor) {
		return visitor.visit(this);
	}

}
