Annox
=====

Parse Java annotations from text or XML resources.

```java
		// Parse annotation from the string
		XAnnotation<XmlRootElement> xannotation =
			(XAnnotation<XmlRootElement>) XAnnotationParser.INSTANCE.parse
				("@javax.xml.bind.annotation.XmlRootElement(name=\"foo\")");

		// Create an instance of the annotation 
		XmlRootElement xmlRootElement = xannotation.getResult();
		assertEquals("foo", xmlRootElement.name());
		assertEquals("##default", xmlRootElement.namespace());
		
		// Analyze the structure of the annotation
		assertEquals(String.class, xannotation.getFieldsMap().get("name").getType());
		assertEquals("##default", xannotation.getFieldsMap().get("namespace").getResult());
```

