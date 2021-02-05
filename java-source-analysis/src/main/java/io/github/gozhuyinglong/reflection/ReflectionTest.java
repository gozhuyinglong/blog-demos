package io.github.gozhuyinglong.reflection;

import org.junit.Test;

import java.lang.reflect.Constructor;

/**
 * @author ZhuYinglong
 * @date 2021/2/2 0002
 */
public class ReflectionTest {

    /**
     * 获取 Class 类实例的四种方式
     *
     * @throws Exception
     */
    @Test
    public void testClassFor() {

        // 1.通过类实例获取
        Person person = new Person();
        Class<? extends Person> clazz1 = person.getClass();
        System.out.println("01 - " + clazz1);

        // 2.通过类直接调用class获取
        Class<Person> clazz2 = Person.class;
        System.out.println("02 - " + clazz2);

        // 3.通过Class.forName获取
        Class<?> clazz3 = null;
        try {
            clazz3 = Class.forName("io.github.gozhuyinglong.reflection.Person");
        } catch (ClassNotFoundException e) {
            // 当找不到指定类时，会抛出此异常
            e.printStackTrace();
        }
        System.out.println("03 - " + clazz3);

        // 4.通过类加载器获取
        ClassLoader classLoader = this.getClass().getClassLoader();
        Class<?> clazz4 = null;
        try {
            clazz4 = classLoader.loadClass("io.github.gozhuyinglong.reflection.Person");
        } catch (ClassNotFoundException e) {
            // 当找不到指定类时，会抛出此异常
            e.printStackTrace();
        }
        System.out.println("04 - " + clazz4);

        // hashCode相等，说明这四种方式获取的是同一个实例
        System.out.println("05 - " + clazz1.hashCode());
        System.out.println("06 - " + clazz2.hashCode());
        System.out.println("07 - " + clazz3.hashCode());
        System.out.println("08 - " + clazz4.hashCode());

    }

    /**
     * 一些特殊的接口、类、关键字
     */
    @Test
    public void testClassOther() {

        // 枚举是一种类
        Class<PersonEnum> clazz1 = PersonEnum.class;
        System.out.println("01 - " + clazz1);

        // 注解是一种接口
        Class<PersonAnnotation> clazz2 = PersonAnnotation.class;
        System.out.println("02 - " + clazz2);

        // 数组也属于一个反应 Class 实例的类
        Person[] personArray3 = new Person[1];
        Class<? extends Person[]> clazz3 = personArray3.getClass();
        System.out.println("03 - " + clazz3);

        // 具有相同元素类型和维数的数组，也具有相同的 Class 实例
        Person[] personArray4 = new Person[4];
        Class<?> clazz4 = personArray4.getClass();

        Person[][] personArray5 = new Person[1][];
        Class<?> clazz5 = personArray5.getClass();

        // 两个一维数组的 hashCode 相等，说明是同一实例
        System.out.println("04 - " + clazz3.hashCode());
        System.out.println("05 - " + clazz4.hashCode());
        // 一维数组与二维数组的 hashCode 不相等，说明不是同一实例
        System.out.println("06 - " + clazz5.hashCode());

        // 原始 Java 类型和关键字 void 也表示为 Class 实例
        Class<Integer> clazz6 = int.class;
        System.out.println("07 - " + clazz6);

        Class<Double> clazz7 = double.class;
        System.out.println("08 - " + clazz7);

        Class<Void> clazz8 = void.class;
        System.out.println("09 - " + clazz8);

    }

    /**
     * 构造函数
     *
     * @throws Exception
     */
    @Test
    public void testConstructor() throws Exception {
        Class<?> clazz = Class.forName("io.github.gozhuyinglong.reflection.Person");

        // 获取一个 public 构造函数实例
        Constructor<?> constructor1 = clazz.getConstructor(String.class, int.class);
        System.out.println("01 - " + constructor1);

        // 获取所有 public 构造函数实例
        Constructor<?>[] constructorArray1 = clazz.getConstructors();
        System.out.println("02 - " + constructorArray1.length);

        // 获取一个构造函数实例
        Constructor<?> constructor2 = clazz.getDeclaredConstructor(String.class);
        System.out.println("03 - " + constructor2);

        // 获取所有构造函数实例
        Constructor<?>[] constructorArray2 = clazz.getDeclaredConstructors();
        System.out.println("04 - " + constructorArray2.length);

        // 根据构造函数实例创建一个实例
        Object o1 = constructor1.newInstance("杨过", 25);
        System.out.println("05 - " + o1);

        // 将构造函数的可访问标志设为 true 后，可以为私有构造函数创建实例
        constructor2.setAccessible(true);
        Object o2 = constructor2.newInstance("小龙女");
        System.out.println("06 - " + o2);

    }
}
