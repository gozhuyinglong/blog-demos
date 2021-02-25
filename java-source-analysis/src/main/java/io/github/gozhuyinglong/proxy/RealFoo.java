package io.github.gozhuyinglong.proxy;

/**
 * @author ZhuYinglong
 * @date 2021/2/24 0024
 */
public class RealFoo implements Foo {

    @Override
    public String ping(String name) {
        System.out.println("ping");
        return "pong";
    }

}
