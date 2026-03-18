package com.jiangnan.www.springboot01helloword;

import com.jiangnan.www.springboot01helloword.demos.web.bean.Pet;
import com.jiangnan.www.springboot01helloword.demos.web.bean.User;
import com.jiangnan.www.springboot01helloword.demos.web.config.MyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 组合注解包含了:
 * {@code @SpringBootConfiguration}
 * {@code @EnableAutoConfiguration}
 * {@code @ComponentScan}
 */
@SpringBootApplication
public class SpringBoot01HellowordApplication {

    public static void main(String[] args) {
        // 返回我的IOC容器
        ConfigurableApplicationContext run = SpringApplication.run(SpringBoot01HellowordApplication.class, args);
        //查看容器里面的组件
        String[] beanDefinitionNames = run.getBeanDefinitionNames();
        // 从容器中获取组件
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println("beanName" + beanDefinitionName);
        }

        // 验证组件是单实例
        Pet tom = run.getBean("tomMimi", Pet.class);
        Pet tom1 = run.getBean("tomMimi", Pet.class);
        System.out.println("组件" + (tom == tom1));

        // bean = com.jiangnan.www.springboot01helloword.demos.web.config.MyConfig$$EnhancerBySpringCGLIB$$b7973e58@3354cb9f
        // 本身就是代理对象
        MyConfig bean = run.getBean(MyConfig.class);
        System.out.println("bean = " + bean);


        // 如果proxyBeanMethods=true ,代理对象调用方法,Springboot总会检查这个组件是否在容器中,确保它总是单实例
        User user = bean.user01();
        User user1 = bean.user01();
        System.out.println("user == user1" + (user == user1));

        User user01 = run.getBean("user01", User.class);
        Pet tomMimi = run.getBean("tomMimi", Pet.class);
        // 我们看用户依赖的宠物是不是就是tomMimi组件是同一个
        System.out.println("用户宠物" + (user01.getPet() == tomMimi));

        System.out.println("===================================");
        // 获取容器中的组件
        for (String s : run.getBeanNamesForType(User.class)) {
            System.out.println("s = " + s);
        }
    }

}
