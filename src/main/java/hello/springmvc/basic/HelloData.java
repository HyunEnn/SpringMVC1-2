package hello.springmvc.basic;

import lombok.Data;

@Data // getter , setter 를 자동으로 만들어 줌
public class HelloData {

    private String username;
    private int age;
}
