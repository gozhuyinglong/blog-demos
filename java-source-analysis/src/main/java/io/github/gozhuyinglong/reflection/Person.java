package io.github.gozhuyinglong.reflection;

/**
 * @author 码农StayUp
 * @date 2021/2/2 0002
 */
@PersonAnnotation
public class Person extends PersonParent implements PersonInterface {

    @PersonAnnotation
    private String name; // 姓名
    private int age; // 年龄
    public int height; // 身高 cm

    public Person() {
    }

    private Person(String name) {
        this.name = name;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @PersonAnnotation
    public Person(String name, int age, PersonEnum sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }


    @PersonAnnotation
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public String sayHello() {
        return "Hello！我叫[" + name + "]";
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }

}
