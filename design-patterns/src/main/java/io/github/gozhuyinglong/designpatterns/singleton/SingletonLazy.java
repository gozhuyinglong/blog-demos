package io.github.gozhuyinglong.designpatterns.singleton;

/**
 * 单例模式 - 懒汉式
 *
 * @author 码农StayUp
 * @date 2020/12/7 0007
 */
public class SingletonLazy {

    /**
     * 私有实例
     */
    private static SingletonLazy instance;

    /**
     * 私有构造方法
     */
    private SingletonLazy() {
    }

    /**
     * 唯一公开获取实例的方法（静态工厂方法），该方法使用synchronized加锁，来保证线程安全性
     *
     * @return
     */
    public static synchronized SingletonLazy getInstance() {
        if (instance == null) {
            instance = new SingletonLazy();
        }
        return instance;
    }

}
