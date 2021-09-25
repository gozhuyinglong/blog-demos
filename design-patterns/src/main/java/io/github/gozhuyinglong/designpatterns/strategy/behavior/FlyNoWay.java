package io.github.gozhuyinglong.designpatterns.strategy.behavior;

/**
 * 不会飞行
 *
 * @author 码农StayUp
 * @date 2021/1/31 0031
 */
public class FlyNoWay implements Fly {
    @Override
    public void fly() {
        System.out.println("不会飞行");
    }
}
