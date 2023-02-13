package hello.springmvc.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogTestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        System.out.println("name = " + name);
        
        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info(" info log={}", name);
        log.warn("warn log={}", name);
        log.error("error log={}", name);
        return "ok";
    }
}
/***
 * 1. Controller로 어노테이션 하면 return 이 view 를 return 해주는데
 * 2. restController를 반환하면 return의 값을 그대로 반환함
 * 3. restAPI 를 만들때 중요함
 */
