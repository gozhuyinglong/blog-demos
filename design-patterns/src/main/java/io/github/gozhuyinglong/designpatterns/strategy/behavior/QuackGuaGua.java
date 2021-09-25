package io.github.gozhuyinglong.designpatterns.strategy.behavior;

/**
 * 呱呱叫
 *
 * @author 码农StayUp
 * @date 2021/1/31 0031
 */
public class QuackGuaGua implements Quack {
    @Override
    public void quack() {
        System.out.println("呱呱叫");
    }
}
