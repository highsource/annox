package org.jvnet.annox.model;

import org.jvnet.annox.model.annotation.field.XArrayAnnotationField;
import org.jvnet.annox.model.annotation.field.XSingleAnnotationField;

/**
 * Annotation visitor.
 * 
 * @param <T>
 *            Visitor return type.
 * @author Aleksei Valikov
 */
public interface XAnnotationVisitor<T> {

	/**
	 * Visits an annotation.
	 * 
	 * @param annotation
	 *            annotation to be visited.
	 * @return Result of the visit.
	 */
	public T visitAnnotation(XAnnotation<?> annotation);

	public T visitSingleAnnotationField(XSingleAnnotationField<?> singleAnnotationField);
	
	public T visitArrayAnnotationField(XArrayAnnotationField<?> arrayAnnotationField);

}
