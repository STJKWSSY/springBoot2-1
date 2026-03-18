package com.jiangnan.www.springboot01helloword.demos.web.config;


import com.jiangnan.www.springboot01helloword.demos.web.bean.Pet;
import com.jiangnan.www.springboot01helloword.demos.web.bean.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;


/**
 * 1.配置类里面使用@Bean标注在方法上给容器注册组件,默认是单实例
 * 2. 配置类本身也是组件
 * 3. proxyBeanMethods:代理bean的方法,容器中的保存代理对象,解决组件依赖问题
 *      full(proxyBeanMethods=true),Lite(proxyBeanMethods=false)(全配置,轻量配置)
 *      最佳实战:
 *          配置类组件之间无依赖关系使用lite模式加速容器启动过程,减少是否存在的判断
 *          配置类组件之间有依赖关系,方法会被调用得到之前单实例组件,使用full模式

 * 4. @Import: 给容器中自动创建出这两个类型的组件,默认组件的名称就是全类名(om.jiangnan.www.springboot01helloword.demos.web.bean.User)/(DOMObjectHelpersun.plugin.dom.DOMObjectHelper@3bab4445)
 */
@Import({User.class, Pet.class})
@Configuration
// @ConditionalOnBean(name = "tomMimi") 条件装配  当容器中存在tomMimi,才会注入以下组件
@ImportResource
public class MyConfig {


    /**
     * 给容器中添加组组件,以方法名称为组件的ID,返回类型就是组件类型,返回值就是组件在容器中的实例
     * 外部无论对配置类中的这个组件调用多少次,获取的都是之前注册在容器中的单实例对象
     * {@code @ConditionalOnBean(name} = "tomMimi") : 只有存在tomcat组件的时候,才会注入user01组件
     * {@code @Scope("prototype"):} 指定每次请求时都会创建一个新的实例,每个实例都是独立的
     * @return User实例
     */
    @Bean
    public User user01(){
        // User组件依赖了pet组件
        return new User("张三", 18, "地球",tomcatPet());
    }


    /**
     * 在Spring应用程序上下文中注册一个名为"tom"的bean。
     * 此方法是设置应用程序中bean的配置的一部分。
     * 应根据应用程序的需求来定义此bean的具体实现和用途。
     */
    @Bean("tomMimi")
    public Pet tomcatPet(){
        return  new Pet("tomcat",14);
    }


}
