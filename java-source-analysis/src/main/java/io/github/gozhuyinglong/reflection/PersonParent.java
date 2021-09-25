package io.github.gozhuyinglong.reflection;

/**
 * @author 码农StayUp
 * @date 2021/2/7 0007
 */
public class PersonParent {

    protected PersonEnum sex; // 性别
    public String hobby; // 爱好

    public PersonEnum getSex() {
        return sex;
    }

    public void setSex(PersonEnum sex) {
        this.sex = sex;
    }
}
