package io.github.gozhuyinglong.designpatterns.strategy;

import io.github.gozhuyinglong.designpatterns.strategy.behavior.Fly;
import io.github.gozhuyinglong.designpatterns.strategy.behavior.Quack;

/**
 * 鸭子抽象类
 *
 * @author 码农StayUp
 * @date 2021/1/31 0031
 */
public abstract class Duck {

    protected Fly fly;
    protected Quack quack;

    public void swim() {
        System.out.println("正在游泳...");
    }

    public abstract void display();

    public Fly getFly() {
        return fly;
    }

    public Quack getQuack() {
        return quack;
    }
}
