package hello.springmvc.basic.request;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);
        response.getWriter().write("ok");
    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);
        responseWriter.write("ok");
    }

    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {

        String body = httpEntity.getBody();
        log.info("messageBody={}", body);
        return new HttpEntity<>("ok");
    }

    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) throws IOException {
        log.info("messageBody={}", messageBody);
        return messageBody;
    }
}

/**
 * 요청 파라미터는 get에 쿼리 string이 오는것, form 을 post로 보내는 방식을 제외하고는
 * HttpEntity<>를 통해서 데이터를 직접 꺼내서 써야 함.
 * 하지만, 이걸 모두 축약시켜 놓은게 RequestBody, ResponseBody로 해결 가능하다.
 * 요청오는 것은 RequestBody, 요청 나가는 것은 ResponseBody를 통해서 구현된다.
 * 위에서 얘기한 것과 같이 메시지 바디를 직접 조회하는 기능은 요청 파라미터를 조회하는
 * RequestParam, ModelAttribute 와는 관계가 없다.
 * 정리하자면 RequestParam 으로 가져올 정보들을 골라서 가져오고 ,
 * ModelAttribute 는 모든 정보들을 다 땡겨와서 그 중 일부를 사용하는 방식
 * RequestBody, ResponseBody 는 이와 관계없이 쿼리 내용이 아닌 그 안의 내용들을 가져온다
 * 라고 생각하면 될 것 같다.
 */
