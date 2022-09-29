package scorpio.core.util.excel;

import java.lang.annotation.*;

/**
 * Excel 註解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
public @interface Excel {

    String name();

    int index();

    boolean isMust() default true;

    int width() default 1;

    String remark() default "";
}
