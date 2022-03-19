package cn.bug.demo01.secrity;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author coding-bug
 * @description
 * @createDate 2022-01-04 15:14
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException e) throws IOException, ServletException {

        // 处理异常
        res.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = res.getOutputStream();
        // ResponseResult<Object> result = new ResponseResult<>(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
  
        e.printStackTrace();

        // outputStream.write(JSONUtil.toJsonStr(result).getBytes(StandardCharsets.UTF_8));
        outputStream.write("未认证".getBytes(StandardCharsets.UTF_8));

        outputStream.flush();
        outputStream.close();
    }
}
