package io.github.gozhuyinglong.designpatterns.strategy;

import io.github.gozhuyinglong.designpatterns.strategy.behavior.FlyNoWay;
import io.github.gozhuyinglong.designpatterns.strategy.behavior.QuackNoWay;

/**
 * 诱饵鸭
 *
 * @author 码农StayUp
 * @date 2021/1/31 0031
 */
public class DecoyDuck extends Duck {

    // 诱饵鸭不会飞行，也不会叫
    public DecoyDuck() {
        this.fly = new FlyNoWay();
        this.quack = new QuackNoWay();
    }

    @Override
    public void display() {
        System.out.println("外观是诱饵鸭");
    }
}
