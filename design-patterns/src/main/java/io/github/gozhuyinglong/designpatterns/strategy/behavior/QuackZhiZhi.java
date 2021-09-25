package io.github.gozhuyinglong.designpatterns.strategy.behavior;

/**
 * 吱吱叫
 *
 * @author 码农StayUp
 * @date 2021/1/31 0031
 */
public class QuackZhiZhi implements Quack {
    @Override
    public void quack() {
        System.out.println("吱吱叫");
    }
}
