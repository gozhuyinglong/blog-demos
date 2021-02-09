package io.github.gozhuyinglong.reflection;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author 码农StayUp
 * @date 2021/2/2 0002
 */
@Retention(RUNTIME)
@Target({TYPE, CONSTRUCTOR, METHOD, FIELD, PARAMETER})
public @interface PersonAnnotation {
}
