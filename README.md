Annox
=====

Parse Java annotations from text or XML resources.

IMPORTANT NOTE
--------------

This repository has been merged in [jaxb-tools](https://github.com/highsource/jaxb-tools) repository along maven-jaxb2-plugin.  
Users are encouraged to read the [migration guide](https://github.com/highsource/jaxb-tools/wiki/JAXB-Tools-Migration-Guide) to get the latest version of the plugin according their JAXB version

Usage
-----

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
