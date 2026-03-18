/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jiangnan.www.springboot01helloword.demos.web;

import com.jiangnan.www.springboot01helloword.demos.web.bean.Car;
import com.jiangnan.www.springboot01helloword.demos.web.bean.Dog;
import com.jiangnan.www.springboot01helloword.demos.web.bean.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@RestController
public class BasicController {

    private final Car car;

    private final Dog dog;

    private final Person person;

    public BasicController(Car car, Dog dog, Person person) {
        this.car = car;
        this.dog = dog;
        this.person = person;
    }

    // http://127.0.0.1:8080/hello?name=lisi
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(@RequestParam(name = "name", defaultValue = "unknown user") String name) {
        return "Hello " + name;
    }

    // http://127.0.0.1:8080/user
    @RequestMapping("/user")
    @ResponseBody
    public User user() {
        User user = new User();
        user.setName("theonefx");
        user.setAge(666);
        return user;
    }

    // http://127.0.0.1:8080/save_user?name=newName&age=11
    @RequestMapping("/save_user")
    @ResponseBody
    public String saveUser(User u) {
        return "user will save: name=" + u.getName() + ", age=" + u.getAge();
    }

    // http://127.0.0.1:8080/html
    @RequestMapping("/html")
    public String html() {
        return "index.html";
    }

    @ModelAttribute
    public void parseUser(@RequestParam(name = "name", defaultValue = "unknown user") String name
            , @RequestParam(name = "age", defaultValue = "12") Integer age, User user) {
        user.setName("zhangsan");
        user.setAge(18);
    }

    /**
     * 通过 @Component @ConfigurationProperties(prefix = "my-car") 配置绑定car
     *
     * @return Car
     */
    @RequestMapping("/car")
    public Car car(){
        return car;
    }

    /**
     * 通过 @EnableConfigurationProperties(Dog.class) 和 @ConfigurationProperties(prefix = "my-dog") 配置绑定
     */
    @RequestMapping("/dog")
    public Dog dog(){
        return dog;
    }

    @RequestMapping("/person")
    public Person person(){
        return person;
    }


}
