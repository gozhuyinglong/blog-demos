package io.github.gozhuyinglong.proxy;

/**
 * @author ZhuYinglong
 * @date 2021/2/24 0024
 */
public class RealFoo implements Foo {

    @Override
    public String sayHello(String name) {
        System.out.println("invoke sayHello()");
        return "hello," + name;
    }

}
