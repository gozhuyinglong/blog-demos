package com.github.gozhuyinglong.designpatterns.factory;

/**
 * @author ZhuYinglong
 * @date 2020/12/8 0008
 */
public class FactoryMethod {

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

    private interface PhoneFactory {
        /**
         * 获取手机实例
         *
         * @return
         */
        Phone getPhone();
    }

    private static class XiaomiPhoneFactory implements PhoneFactory {
        @Override
        public Phone getPhone() {
            return new XiaomiPhone();
        }
    }

    private static class RedmiPhoneFactory implements PhoneFactory {
        @Override
        public Phone getPhone() {
            return new RedmiPhone();
        }
    }

    public static void main(String[] args) {
        System.out.println("================小米手机================");
        Phone xiaomiPhone = new XiaomiPhoneFactory().getPhone();
        xiaomiPhone.call();
        xiaomiPhone.sendSMS();

        System.out.println("================红米手机================");
        Phone redmiPhone = new RedmiPhoneFactory().getPhone();
        redmiPhone.call();
        redmiPhone.sendSMS();
    }

}
