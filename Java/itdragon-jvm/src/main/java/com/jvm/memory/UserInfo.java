package com.jvm.memory;

/**
 * 封装数据类型：建议定义全局变量时使用，默认值为null
 * 基本数据类型：建议定义局部变量时使用，整数默认值为0，小数默认值为0.0，Boolean默认为false，char类型默认为\u0000，其实都是0
 */
// step1 类加载：JVM将UserInfo.class的类信息加载到方法区中
public class UserInfo {
    private Long id;
    private String name;

    public void sayHello(String toName) {
        System.out.println(name + " : Hello! " + toName);
    }

    // step2 JVM读取到方法区中的main方法，开始执行它的指令
    public static void main(String [] args) {
        System.out.println(Math.round(11.5));
        System.out.println(Math.round(-11.5));
        // step3 user为局部变量，存放在栈中。
        UserInfo user = new UserInfo(); // step4 实例化UserInfo对象，存放在堆中，并赋值给user
        user.id = 1L;   // step5 属性赋值
        user.name = "itdragon";
        user.sayHello("ITDragonBlog");  // step6 方法执行
    }
}
