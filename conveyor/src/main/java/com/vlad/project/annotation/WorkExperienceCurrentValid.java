package com.vlad.project.annotation;

import com.vlad.project.filter.WorkExperienceCurrentFilter;
import com.vlad.project.filter.WorkExperienceTotalFilter;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(ElementType.FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = WorkExperienceCurrentFilter.class)
public @interface WorkExperienceCurrentValid {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
