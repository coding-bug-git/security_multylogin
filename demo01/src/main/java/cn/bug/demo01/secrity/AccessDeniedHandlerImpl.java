package cn.bug.demo01.secrity;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
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
 * @createDate 2022-01-04 16:41
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest req, HttpServletResponse res, AccessDeniedException e) throws IOException, ServletException {
        res.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = res.getOutputStream();
        // ResponseResult<Object> result = new ResponseResult<>(HttpStatus.FORBIDDEN.value(), "权限不足");

        // e.printStackTrace();

        // outputStream.write(JSONUtil.toJsonStr(result).getBytes(StandardCharsets.UTF_8));
        outputStream.write("权限不足".getBytes(StandardCharsets.UTF_8));

        outputStream.flush();
        outputStream.close();
    }
}
