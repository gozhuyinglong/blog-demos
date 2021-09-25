package io.github.gozhuyinglong.designpatterns.strategy.behavior;

/**
 * 用翅膀飞行
 *
 * @author 码农StayUp
 * @date 2021/1/31 0031
 */
public class FlyWithWings implements Fly {
    @Override
    public void fly() {
        System.out.println("用翅膀飞行");
    }
}
