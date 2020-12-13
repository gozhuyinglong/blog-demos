package io.github.gozhuyinglong.designpatterns.singleton;

import java.util.concurrent.TimeUnit;

/**
 * 使用多线程测试单例模式
 *
 * @author ZhuYinglong
 * @date 2020/12/7 0007
 */
public class SingletonTest {

    public static void main(String[] args) throws InterruptedException {

        final int loop = 5;

        // 1.懒汉式
        for (int i = 0; i < loop; i++) {
            new Thread(() -> {
                SingletonLazy singleton = SingletonLazy.getInstance();
                System.out.println("1.懒汉式：" + singleton.hashCode());
            }).start();
        }

        // 2.饿汉式
        for (int i = 0; i < loop; i++) {
            new Thread(() -> {
                SingletonEager singleton = SingletonEager.getInstance();
                System.out.println("2.饿汉式：" + singleton.hashCode());
            }).start();
        }

        // 3.双重校验锁
        for (int i = 0; i < loop; i++) {
            new Thread(() -> {
                SingletonDCL singleton = SingletonDCL.getInstance();
                System.out.println("3.双重校验锁：" + singleton.hashCode());
            }).start();
        }

        // 4.静态内部类
        for (int i = 0; i < loop; i++) {
            new Thread(() -> {
                SingletonInnerClass singleton = SingletonInnerClass.getInstance();
                System.out.println("4.静态内部类：" + singleton.hashCode());
            }).start();
        }

        // 5.枚举类
        for (int i = 0; i < loop; i++) {
            new Thread(() -> {
                SingletonEnum singleton = SingletonEnum.INSTANCE;
                System.out.println("5.枚举类：" + singleton.hashCode());
            }).start();
        }

        TimeUnit.SECONDS.sleep(1);
    }
}
