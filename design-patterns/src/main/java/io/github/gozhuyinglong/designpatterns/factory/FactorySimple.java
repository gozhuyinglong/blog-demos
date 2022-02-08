package io.github.gozhuyinglong.designpatterns.factory;

/**
 * 简单工厂模式
 *
 * @author 码农StayUp
 * @date 2020/12/8 0008
 */
public class FactorySimple {

    /**
     * 手机接口
     */
    private interface Phone {

        /**
         * 打电话
         */
        void call();

        /**
         * 发短信
         */
        void sendSMS();
    }

    /**
     * 小米手机实现类
     */
    private static class XiaomiPhone implements Phone {
        @Override
        public void call() {
            System.out.println("使用小米手机打电话");
        }

        @Override
        public void sendSMS() {
            System.out.println("使用小米手机发短信");
        }
    }

    /**
     * 红米手机实现类
     */
    private static class RedmiPhone implements Phone {
        @Override
        public void call() {
            System.out.println("使用红米手机打电话");
        }

        @Override
        public void sendSMS() {
            System.out.println("使用红米手机发短信");
        }
    }

    /**
     * 手机工厂类
     */
    private static class PhoneFactory {

        /**
         * 根据类型获取手机
         *
         * @param type
         * @return
         */
        public static Phone createPhone(String type) {
            switch (type) {
                case "xiaomi":
                    return new XiaomiPhone();
                case "redmi":
                    return new RedmiPhone();
                default:
                    return null;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("================小米手机================");
        Phone xiaomiPhone = PhoneFactory.createPhone("xiaomi");
        if (xiaomiPhone != null) {
            xiaomiPhone.call();
            xiaomiPhone.sendSMS();
        }

        System.out.println("================红米手机================");
        Phone redmiPhone = PhoneFactory.createPhone("redmi");
        if (redmiPhone != null) {
            redmiPhone.call();
            redmiPhone.sendSMS();
        }

    }
}
