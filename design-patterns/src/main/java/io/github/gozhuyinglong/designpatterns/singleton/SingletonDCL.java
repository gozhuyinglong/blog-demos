package io.github.gozhuyinglong.designpatterns.singleton;

/**
 * 单例模式 - 双重校验锁（double-checked-locking）
 *
 * @author 码农StayUp
 * @date 2020/12/7 0007
 */
public class SingletonDCL {

    /**
     * 私有实例，volatile修饰的变量是具有可见性的（即被一个线程修改后，其他线程立即可见）
     */
    private volatile static SingletonDCL instance;

    /**
     * 私有构造方法
     */
    private SingletonDCL() {
    }

    /**
     * 唯一公开获取实例的方法（静态工厂方法）
     *
     * @return
     */
    public static SingletonDCL getInstance() {
        if (instance == null) {
            synchronized (SingletonDCL.class) {
                if (instance == null) {
                    instance = new SingletonDCL();
                }
            }
        }
        return instance;
    }
}
