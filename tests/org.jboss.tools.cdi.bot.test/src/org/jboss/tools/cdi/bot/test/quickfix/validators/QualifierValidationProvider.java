package org.jboss.tools.cdi.bot.test.quickfix.validators;

import java.util.ArrayList;

import org.jboss.tools.cdi.bot.test.annotations.CDIAnnotationsType;

public class QualifierValidationProvider extends AbstractValidationProvider {

	public QualifierValidationProvider() {
		super();
	}
	
	@Override
	void init() {
		validationErrors.get("Warnings").add("Qualifier annotation type must be annotated " +
				"with @Retention(RUNTIME)");
		validationErrors.get("Warnings").add("Qualifier annotation type must be annotated with " +
				"@Target");
		validationErrors.get("Warnings").add("Annotation-valued member of a qualifier type " +
				"should be annotated @Nonbinding");
		validationErrors.get("Warnings").add("Array-valued member of a qualifier type " +
				"should be annotated @Nonbinding");
		
		warningsAnnotation.add(CDIAnnotationsType.RETENTION);
		warningsAnnotation.add(CDIAnnotationsType.TARGET);
		warningsAnnotation.add(CDIAnnotationsType.NONBINDING);
	}

	public ArrayList<String> getAllWarningForAnnotationType(
			CDIAnnotationsType annotationType) {
		int warningIndex = 0;
		switch(annotationType) {
		case RETENTION:
			warningIndex = 0;
			break;
		case TARGET:
			warningIndex = 1;
			break;
		case NONBINDING:
			warningIndex = 2;
			warningsForAnnotationType.add(validationErrors.get("Warnings").get(warningIndex));
			warningIndex = 3;
			break;
		}
		warningsForAnnotationType.add(validationErrors.get("Warnings").get(warningIndex));
		return warningsForAnnotationType;
	}
	
}