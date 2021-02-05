package io.github.gozhuyinglong.reflection;

/**
 * @author ZhuYinglong
 * @date 2021/2/2 0002
 */
@PersonAnnotation
public class Person {

    private String name;
    private int age;

    public Person() {
    }

    private Person(String name) {
        this.name = name;
    }

    @PersonAnnotation
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
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
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}