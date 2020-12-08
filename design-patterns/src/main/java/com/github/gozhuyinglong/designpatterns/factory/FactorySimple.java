package com.github.gozhuyinglong.designpatterns.factory;

/**
 * 简单工厂模式
 *
 * @author ZhuYinglong
 * @date 2020/12/8 0008
 */
public class FactorySimple {

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

    private static class PhoneFactory {

        /**
         * 根据类型获取手机
         *
         * @param type
         * @return
         */
        public Phone getPhone(String type) {
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
        PhoneFactory phoneFactory = new PhoneFactory();

        System.out.println("================小米手机================");
        Phone xiaomiPhone = phoneFactory.getPhone("xiaomi");
        xiaomiPhone.call();
        xiaomiPhone.sendSMS();

        System.out.println("================红米手机================");
        Phone redmiPhone = phoneFactory.getPhone("redmi");
        redmiPhone.call();
        redmiPhone.sendSMS();

    }
}
