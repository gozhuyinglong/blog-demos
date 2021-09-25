package io.github.gozhuyinglong.designpatterns.strategy.behavior;

/**
 * 不会叫
 *
 * @author 码农StayUp
 * @date 2021/1/31 0031
 */
public class QuackNoWay implements Quack {
    @Override
    public void quack() {
        System.out.println("不会叫");
    }
}
