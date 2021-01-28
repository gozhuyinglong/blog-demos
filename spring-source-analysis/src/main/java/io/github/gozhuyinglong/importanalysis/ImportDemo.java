package io.github.gozhuyinglong.importanalysis;

import io.github.gozhuyinglong.importanalysis.config.Config;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author 码农StayUp
 * @date 2021/1/28 0028
 */
public class ImportDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
        Config config = ctx.getBean(Config.class);
        config.print();
    }
}
