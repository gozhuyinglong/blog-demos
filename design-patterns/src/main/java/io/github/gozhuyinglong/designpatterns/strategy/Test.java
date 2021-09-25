package io.github.gozhuyinglong.designpatterns.strategy;

/**
 * @author 码农StayUp
 * @date 2021/1/31 0031
 */
public class Test {

    public static void main(String[] args) {
        MallardDuck mallardDuck = new MallardDuck();
        mallardDuck.display();
        mallardDuck.swim();
        mallardDuck.getFly().fly();
        mallardDuck.getQuack().quack();

        System.out.println("-------------------");

        DecoyDuck decoyDuck = new DecoyDuck();
        decoyDuck.display();
        decoyDuck.swim();
        decoyDuck.getFly().fly();
        decoyDuck.getQuack().quack();
    }
}
