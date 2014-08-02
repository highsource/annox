package org.jvnet.annox.model;

import org.jvnet.annox.model.annotation.field.XArrayAnnotationField;
import org.jvnet.annox.model.annotation.field.XSingleAnnotationField;

public class XAnnotationVisitorWrapper<T> implements XAnnotationVisitor<T> {

	private final XAnnotationVisitor<T> annotationVisitor;

	public XAnnotationVisitor<T> getAnnotationVisitor() {
		return annotationVisitor;
	}

	public XAnnotationVisitorWrapper(XAnnotationVisitor<T> annotationVisitor) {
		this.annotationVisitor = annotationVisitor;
	}

	public T visitAnnotation(XAnnotation<?> annotation) {
		return annotationVisitor.visitAnnotation(annotation);
	}

	@Override
	public T visitSingleAnnotationField(
			XSingleAnnotationField<?> singleAnnotationField) {
		return annotationVisitor
				.visitSingleAnnotationField(singleAnnotationField);
	}

	@Override
	public T visitArrayAnnotationField(
			XArrayAnnotationField<?> arrayAnnotationField) {
		return annotationVisitor
				.visitArrayAnnotationField(arrayAnnotationField);
	}

}
