package io.github.gozhuyinglong.designpatterns.singleton;

/**
 * 单例模式 - 枚举类
 *
 * @author ZhuYinglong
 * @date 2020/12/7 0007
 */
public enum SingletonEnum {

    INSTANCE;

    public void method() {
        System.out.println("枚举类中定义方法！");
    }

}
