package org.jvnet.annox.model.annotation.value;

public class XClassByNameAnnotationValue<I> extends
		XDynamicAnnotationValue<Class<I>> {

	private final String className;

	public XClassByNameAnnotationValue(String className) {
		this.className = className;
	}

	@Override
	public <P> P accept(XAnnotationValueVisitor<P> visitor) {
		return visitor.visit(this);
	}
	
	public String getClassName() {
		return className;
	}

	@Override
	protected Object getInternalValue() {
		return this.className;
	}

	@Override
	public Class<I> getValue() {
		try {
			@SuppressWarnings("unchecked")
			final Class<I> _class = (Class<I>) Class.forName(this.className);
			return _class;
		} catch (ClassNotFoundException cnfex) {
			throw new IllegalStateException("Could not file the class ["
					+ this.className + "]", cnfex);
		}
	}

}
