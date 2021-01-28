package io.github.gozhuyinglong.importanalysis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

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

    @Autowired
    ConfigA configA;

    @Autowired
    ConfigC configC;

    @Autowired
    ConfigD configD;

    final ConfigB configB;

    public Config(ConfigB configB) {
        this.configB = configB;
    }

    public void print() {
        configA.print();
        configB.print();
        configC.print();
        configD.print();
    }
}
