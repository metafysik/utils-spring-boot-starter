package me.metafysik.utils.logs.intelligent;

import me.metafysik.utils.logs.intelligent.logs.LogCondition;
import me.metafysik.utils.logs.intelligent.logs.LogFeature;
import me.metafysik.utils.logs.intelligent.logs.LogModule;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author metafysik
 */
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
public @interface IntelligentLog {

    /**
     * Log module. Default value is service.
     *
     * @return
     */
    LogModule module() default LogModule.Service;

    /**
     * Log category. Default value is class name.
     *
     * @return
     */
    String category() default "";

    /**
     * Log subcategory. Default value is method name.
     *
     * @return
     */
    String subCategory() default "";

    /**
     * Log condition.
     *
     * @return
     */
    LogCondition condition() default LogCondition.Any;

    /**
     * Log feature.
     *
     * @return
     */
    int feature() default LogFeature.GLOBAL_FULL_LOG;
}