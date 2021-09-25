package io.github.gozhuyinglong.designpatterns.singleton;

/**
 * 单例模式 - 饿汉式
 *
 * @author 码农StayUp
 * @date 2020/12/7 0007
 */
public class SingletonEager {

    /**
     * 私有实例，静态变量会在类加载的时候初始化，是线程安全的
     */
    private static final SingletonEager instance = new SingletonEager();

    /**
     * 私有构造方法
     */
    private SingletonEager() {
    }

    /**
     * 唯一公开获取实例的方法（静态工厂方法）
     *
     * @return
     */
    public static SingletonEager getInstance() {
        return instance;
    }
}
