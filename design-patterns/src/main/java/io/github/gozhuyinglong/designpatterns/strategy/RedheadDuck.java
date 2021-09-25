package io.github.gozhuyinglong.designpatterns.strategy;

import io.github.gozhuyinglong.designpatterns.strategy.behavior.FlyWithWings;
import io.github.gozhuyinglong.designpatterns.strategy.behavior.QuackGuaGua;

/**
 * 红头鸭
 *
 * @author 码农StayUp
 * @date 2021/1/31 0031
 */
public class RedheadDuck extends Duck {

    // 红头鸭用翅膀飞行，呱呱叫
    public RedheadDuck() {
        this.fly = new FlyWithWings();
        this.quack = new QuackGuaGua();
    }

    @Override
    public void display() {
        System.out.println("外观是红头鸭");
    }
}
