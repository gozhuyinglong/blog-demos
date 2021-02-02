package io.github.gozhuyinglong.reflection;

import org.junit.Test;

/**
 * @author ZhuYinglong
 * @date 2021/2/2 0002
 */
public class ReflectionTest {

    /**
     * 获取 Class 类对象的三种方式
     *
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {

        // 通过类对象获取
        Person person = new Person();
        Class<? extends Person> clazz1 = person.getClass();
        System.out.println("01 - " + clazz1.toString());

        // 通过类直接调用class获取
        Class<Person> clazz2 = Person.class;
        System.out.println("02 - " + clazz2.toString());

        // 通过Class.forName获取
        Class<?> clazz3 = Class.forName("io.github.gozhuyinglong.reflection.Person");
        System.out.println("03 - " + clazz3.toString());

        // hashCode相等，说明这三种方式创建出来是同一个对象
        System.out.println("04 - " + clazz1.hashCode());
        System.out.println("05 - " + clazz2.hashCode());
        System.out.println("06 - " + clazz3.hashCode());

    }

    /**
     * 验证 Class 类源码注释内容
     */
    @Test
    public void test2() {

        // 枚举是一种类
        Class<PersonEnum> clazz1 = PersonEnum.class;
        System.out.println("01 - " + clazz1.toString());

        // 注解是一种接口
        Class<PersonAnnotation> clazz2 = PersonAnnotation.class;
        System.out.println("02 - " + clazz2.toString());

        // 数组也属于一个反应 Class 对象的类
        Person[] personArray3 = new Person[1];
        Class<? extends Person[]> clazz3 = personArray3.getClass();
        System.out.println("03 - " + clazz3.toString());

        // 具有相同元素类型和维数的数组，也具有相同的 Class 对象
        Person[] personArray4 = new Person[4];
        Class<?> clazz4 = personArray4.getClass();

        Person[][] personArray5 = new Person[1][];
        Class<?> clazz5 = personArray5.getClass();

        System.out.println("04 - " + clazz3.hashCode());
        System.out.println("05 - " + clazz4.hashCode());
        System.out.println("06 - " + clazz5.hashCode());

        // 原始 Java 类型和关键字 void 也表示为 Class 对象
        Class<Integer> clazz6 = int.class;
        System.out.println("07 - " + clazz6.toString());

        Class<Double> clazz7 = double.class;
        System.out.println("08 - " + clazz7.toString());

        Class<Void> clazz8 = void.class;
        System.out.println("09 - " + clazz8.toString());

    }
}
