package io.github.gozhuyinglong.designpatterns.strategy;

import io.github.gozhuyinglong.designpatterns.strategy.behavior.FlyWithWings;
import io.github.gozhuyinglong.designpatterns.strategy.behavior.QuackGuaGua;

/**
 * 野鸭
 *
 * @author 码农StayUp
 * @date 2021/1/31 0031
 */
public class MallardDuck extends Duck {

    // 野鸭用翅膀飞行，呱呱叫
    public MallardDuck() {
        this.fly = new FlyWithWings();
        this.quack = new QuackGuaGua();
    }

    @Override
    public void display() {
        System.out.println("外观是绿头鸭");
    }
}
