package io.github.gozhuyinglong.importanalysis.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author 码农StayUp
 * @date 2021/1/28 0028
 */
public class MyImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"io.github.gozhuyinglong.importanalysis.config.ConfigC"};
    }
}
