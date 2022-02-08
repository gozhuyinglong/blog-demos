package io.github.gozhuyinglong.designpatterns.factory;

/**
 * 工厂方法模式
 *
 * @author 码农StayUp
 * @date 2020/12/8 0008
 */
public class FactoryMethod {

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
     * 手机工厂接口
     */
    private interface PhoneFactory {
        /**
         * 获取手机实例
         *
         * @return
         */
        Phone createPhone();
    }

    /**
     * 小米手机工厂类
     */
    private static class XiaomiPhoneFactory implements PhoneFactory {
        @Override
        public Phone createPhone() {
            return new XiaomiPhone();
        }
    }

    /**
     * 红米手机工厂类
     */
    private static class RedmiPhoneFactory implements PhoneFactory {
        @Override
        public Phone createPhone() {
            return new RedmiPhone();
        }
    }

    public static void main(String[] args) {
        System.out.println("================小米手机================");
        Phone xiaomiPhone = new XiaomiPhoneFactory().createPhone();
        xiaomiPhone.call();
        xiaomiPhone.sendSMS();

        System.out.println("================红米手机================");
        Phone redmiPhone = new RedmiPhoneFactory().createPhone();
        redmiPhone.call();
        redmiPhone.sendSMS();
    }

}
