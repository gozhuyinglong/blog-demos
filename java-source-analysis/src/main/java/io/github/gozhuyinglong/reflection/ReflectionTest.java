package io.github.gozhuyinglong.reflection;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

/**
 * @author 码农StayUp
 * @date 2021/2/2 0002
 */
public class ReflectionTest {

    /**
     * 获取 Class 类实例的四种方式
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
     * 类
     *
     * @throws Exception
     */
    @Test
    public void testClass() throws Exception {
        Class<?> clazz = Class.forName("io.github.gozhuyinglong.reflection.Person");

        // 获取该类所在包路径
        Package aPackage = clazz.getPackage();
        System.out.println("01 - " + aPackage);

        // 获取该类上所有注解
        Annotation[] declaredAnnotations = clazz.getDeclaredAnnotations();
        for (Annotation temp : declaredAnnotations) {
            System.out.println("02 - " + temp);
        }

        // 获取类上的修饰符
        int modifiers = clazz.getModifiers();
        String modifier = Modifier.toString(modifiers);
        System.out.println("03 - " + modifier);

        // 获取类名称
        String name = clazz.getName();
        System.out.println("04 - " + name);
        // 获取简单类名
        String simpleName = clazz.getSimpleName();
        System.out.println("05 - " + simpleName);

        // 获取直属超类
        Type genericSuperclass = clazz.getGenericSuperclass();
        System.out.println("06 - " + genericSuperclass);

        // 获取直属实现的接口
        Type[] genericInterfaces = clazz.getGenericInterfaces();
        for (Type temp : genericInterfaces) {
            System.out.println("07 - " + temp);
        }

    }

    /**
     * 构造函数
     *
     * @throws Exception
     */
    @Test
    public void testConstructor() throws Exception {
        Class<?> clazz = Class.forName("io.github.gozhuyinglong.reflection.Person");

        // 获取一个声明为 public 构造函数实例
        Constructor<?> constructor1 = clazz.getConstructor(String.class, int.class, PersonEnum.class);
        System.out.println("01 - " + constructor1);

        // 获取所有声明为 public 构造函数实例
        Constructor<?>[] constructorArray1 = clazz.getConstructors();
        for (Constructor<?> constructor : constructorArray1) {
            System.out.println("02 - " + constructor);
        }

        // 获取一个声明的构造函数实例
        Constructor<?> constructor2 = clazz.getDeclaredConstructor(String.class);
        System.out.println("03 - " + constructor2);

        // 获取所有声明的构造函数实例
        Constructor<?>[] constructorArray2 = clazz.getDeclaredConstructors();
        for (Constructor<?> constructor : constructorArray2) {
            System.out.println("04 - " + constructor);
        }

        // 根据构造函数创建一个实例
        Object o1 = constructor1.newInstance("杨过", 25, PersonEnum.MAN);
        System.out.println("05 - " + o1);

        // 将构造函数的可访问标志设为 true 后，可以通过私有构造函数创建实例
        constructor2.setAccessible(true);
        Object o2 = constructor2.newInstance("小龙女");
        System.out.println("06 - " + o2);

        // 获取该构造函数上的所有注解
        Annotation[] annotations = constructor1.getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println("07 - " + annotation);
        }

        // 获取该构造函数上的所有参数类型
        Type[] genericParameterTypes = constructor1.getGenericParameterTypes();
        for (Type genericParameterType : genericParameterTypes) {
            System.out.println("08 - " + genericParameterType);
        }

    }

    /**
     * 属性
     *
     * @throws Exception
     */
    @Test
    public void testField() throws Exception {

        Class<?> clazz = Class.forName("io.github.gozhuyinglong.reflection.Person");

        // 获取一个该类或父类中声明为 public 的属性
        Field field1 = clazz.getField("hobby");
        System.out.println("01 - " + field1);

        // 获取该类及父类中所有声明为 public 的属性
        Field[] fieldArray1 = clazz.getFields();
        for (Field field : fieldArray1) {
            System.out.println("02 - " + field);
        }

        // 获取一个该类中声明的属性
        Field field2 = clazz.getDeclaredField("name");
        System.out.println("03 - " + field2);

        // 获取该类中所有声明的属性
        Field[] fieldArray2 = clazz.getDeclaredFields();
        for (Field field : fieldArray2) {
            System.out.println("04 - " + field);
        }

        // 获取该属性上的所有注解
        Annotation[] declaredAnnotations = field2.getDeclaredAnnotations();
        for (Annotation declaredAnnotation : declaredAnnotations) {
            System.out.println("05 - " + declaredAnnotation);
        }

        // 获取修饰符
        String modifier = Modifier.toString(field2.getModifiers());
        System.out.println("06 - " + modifier);

        // 获取属性类型，返回类对象
        Class<?> type = field2.getType();
        System.out.println("07 - " + type);
        // 获取属性类型，返回Type对象
        Type genericType = field2.getGenericType();
        System.out.println("08 - " + genericType);

        // 获取属性名称
        String name = field2.getName();
        System.out.println("09 - " + name);

    }


    /**
     * 方法
     *
     * @throws Exception
     */
    @Test
    public void testMethod() throws Exception {

        Class<?> clazz = Class.forName("io.github.gozhuyinglong.reflection.Person");

        // 获取一个该类及父类中声明为 public 的方法，需要指定方法的入参类型
        Method method = clazz.getMethod("setName", String.class);
        System.out.println("01 - " + method);

        // 获取该类及父类中所有声明为 public 的方法
        Method[] methods = clazz.getMethods();
        for (Method temp : methods) {
            System.out.println("02 - " + temp);
        }

        // 获取一个在该类中声明的方法
        Method declaredMethod = clazz.getDeclaredMethod("display");
        System.out.println("03 - " + declaredMethod);

        // 获取所有在该类中声明的方法
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method temp : declaredMethods) {
            System.out.println("04 - " + temp);
        }

        // 获取该方法上的所有注解
        Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
        for (Annotation temp : declaredAnnotations) {
            System.out.println("05 - " + temp);
        }

        // 获取修饰符
        String modifier = Modifier.toString(method.getModifiers());
        System.out.println("06 - " + modifier);

        // 获取返回值类型，返回类对象
        Class<?> returnType = method.getReturnType();
        System.out.println("07 - " + returnType);
        // 获取返回值类型，返回Type对象
        Type genericReturnType = method.getGenericReturnType();
        System.out.println("08 - " + genericReturnType);

        // 获取方法名称
        String name = method.getName();
        System.out.println("09 - " + name);

        // 获取所有入参
        Parameter[] parameters = method.getParameters();
        for (Parameter temp : parameters) {
            System.out.println("10 - " + temp);
        }

    }

    /**
     * 访问修饰符，以下简称：修饰符
     *
     * @throws Exception
     */
    @Test
    public void testModifier() throws Exception {
        Class<?> clazz = Class.forName("io.github.gozhuyinglong.reflection.Person");

        // 获取类的修饰符值
        int modifiers1 = clazz.getModifiers();
        System.out.println("01 - " + modifiers1);

        // 获取属性的修饰符值
        int modifiers2 = clazz.getDeclaredField("name").getModifiers();
        System.out.println("02 - " + modifiers2);

        // 获取构造函数的修饰符值
        int modifiers3 = clazz.getDeclaredConstructor(String.class).getModifiers();
        System.out.println("03 - " + modifiers3);

        // 获取方法的修饰符值
        int modifiers4 = clazz.getDeclaredMethod("display").getModifiers();
        System.out.println("04 - " + modifiers4);

        // 判断修饰符值是否 final 类型
        boolean isFinal = Modifier.isFinal(modifiers1);
        System.out.println("05 - " + isFinal);

        // 判断修饰符值是否 public 类型
        boolean isPublic = Modifier.isPublic(modifiers2);
        System.out.println("06 - " + isPublic);

        // 根据修饰符值，获取修饰符标志的字符串
        String modifier = Modifier.toString(modifiers1);
        System.out.println("07 - " + modifier);
        System.out.println("08 - " + Modifier.toString(modifiers2));

    }

    /**
     * 参数
     *
     * @throws Exception
     */
    @Test
    public void testParameter() throws Exception {
        Class<?> clazz = Class.forName("io.github.gozhuyinglong.reflection.Person");

        // 获取构造函数的参数
        Constructor<?> constructor = clazz.getConstructor(String.class, int.class, PersonEnum.class);
        Parameter[] parameterArray1 = constructor.getParameters();
        for (Parameter temp : parameterArray1) {
            System.out.println("01 - " + temp);
        }

        // 获取方法的参数
        Method method = clazz.getMethod("setName", String.class);
        Parameter[] parameterArray2 = method.getParameters();
        for (Parameter temp : parameterArray2) {
            System.out.println("02 - " + temp);
        }

        Parameter parameter = parameterArray1[0];
        // 获取参数上的注解
        Annotation[] annotationArray = parameter.getAnnotations();
        for (Annotation temp : annotationArray) {
            System.out.println("02 - " + temp);
        }

        // 获取参数名称
        String name = parameter.getName();
        System.out.println("03 - " + name);

        // 获取参数类型
        Type parameterizedType = parameter.getParameterizedType();
        System.out.println("04 - " + parameterizedType);
        Class<?> type = parameter.getType();
        System.out.println("05 - " + type);

    }

    /**
     * 创建对象并执行方法
     *
     * @throws Exception
     */
    @Test
    public void testInvoke() throws Exception {
        Class<?> clazz = Class.forName("io.github.gozhuyinglong.reflection.Person");

        // 通过Class类的newInstance创建一个实例。（该方法调用无参构造器）
        Object o1 = clazz.newInstance();
        System.out.println("01 - " + o1.toString());

        // 通过构造函数Constructor类创建一个实例
        Constructor<?> constructor = clazz.getConstructor(String.class, int.class, PersonEnum.class);
        Object o2 = constructor.newInstance("杨过", 25, PersonEnum.MAN);
        System.out.println("02 - " + o2.toString());

        // 先获取方法，再通过 invoke 方法来调用，第一个参数为实例，后面参数为方法的Parameter
        Method method = clazz.getMethod("setName", String.class);
        method.invoke(o1, "小龙女");
        System.out.println("03 - " + o1.toString());

        // 获取字段，因为 age 字段是私有的，所以将其设置为可访问（不设置会报异常）。并通过 set 方法来赋值
        Field field = clazz.getDeclaredField("age");
        field.setAccessible(true);
        field.set(o1, 28);
        System.out.println("04 - " + o1.toString());

    }


}
