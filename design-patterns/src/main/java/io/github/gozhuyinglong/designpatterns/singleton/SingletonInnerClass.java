package io.github.gozhuyinglong.designpatterns.singleton;

/**
 * 单例模式 - 静态内部类
 *
 * @author ZhuYinglong
 * @date 2020/12/7 0007
 */
public class SingletonInnerClass {

    /**
     * 私有构造方法
     */
    private SingletonInnerClass() {
    }

    /**
     * 唯一公开获取实例的方法（静态工厂方法）
     *
     * @return
     */
    public static SingletonInnerClass getInstance() {
        return LazyHolder.INSTANCE;
    }

    /**
     * 私有静态内部类
     */
    private static class LazyHolder {
        private static final SingletonInnerClass INSTANCE = new SingletonInnerClass();
    }
}
