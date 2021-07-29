package com.tml.web.exceptionhandler;

import com.tml.exception.DeleteException;
import com.tml.exception.InsertException;
import com.tml.exception.LoginException;
import com.tml.exception.UpdateException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/*对controller的增强*/
@ControllerAdvice
@ResponseBody
public class ExceptionController {

    @ExceptionHandler(LoginException.class)
    public Map<String, Object> loginException(Exception e) {
        Map<String, Object> map = new HashMap<>();
        map.put("success", false);
        map.put("msg", e.getMessage());
        return map;
    }

    @ExceptionHandler(InsertException.class)
    public Map<String, Object> insertException(Exception e) {
        Map<String, Object> map = new HashMap<>();
        map.put("success", false);
        map.put("msg", e.getMessage());
        return map;
    }

    @ExceptionHandler(UpdateException.class)
    public Map<String, Object> updateException(Exception e) {
        Map<String, Object> map = new HashMap<>();
        map.put("success", false);
        map.put("msg", e.getMessage());
        return map;
    }

    @ExceptionHandler(DeleteException.class)
    public Map<String, Object> deleteException(Exception e) {
        Map<String, Object> map = new HashMap<>();
        map.put("success", false);
        map.put("msg", e.getMessage());
        return map;
    }
}
