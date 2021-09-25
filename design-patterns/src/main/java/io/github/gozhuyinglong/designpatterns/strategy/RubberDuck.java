package io.github.gozhuyinglong.designpatterns.strategy;

import io.github.gozhuyinglong.designpatterns.strategy.behavior.FlyNoWay;
import io.github.gozhuyinglong.designpatterns.strategy.behavior.QuackZhiZhi;

/**
 * 橡皮鸭
 *
 * @author 码农StayUp
 * @date 2021/1/31 0031
 */
public class RubberDuck extends Duck {

    // 橡皮鸭不会飞行，吱吱叫
    public RubberDuck() {
        this.fly = new FlyNoWay();
        this.quack = new QuackZhiZhi();
    }

    @Override
    public void display() {
        System.out.println("外观是橡皮鸭");
    }
}
