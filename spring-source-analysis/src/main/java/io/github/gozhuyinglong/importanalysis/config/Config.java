package io.github.gozhuyinglong.importanalysis.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.Resource;

/**
 * @author 码农StayUp
 * @date 2021/1/28 0028
 */
@Configuration
@Import({ConfigA.class,
        ConfigB.class,
        MyImportSelector.class,
        MyImportBeanDefinitionRegistrar.class})
public class Config {

    @Resource
    ConfigA configA;

    @Resource
    ConfigB configB;

    @Resource
    ConfigC configC;

    @Resource
    ConfigD configD;

    public void print() {
        configA.print();
        configB.print();
        configC.print();
        configD.print();
    }
}
