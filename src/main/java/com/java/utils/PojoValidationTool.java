package com.java.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:	   <br/>
 * Date:     0021, September 21 23:06 <br/>
 *
 * @author Aaron CHEN
 * @see
 */
public class PojoValidationTool {

    public static Map<String, Object> validatePojo(BindingResult bindingResult) {
        try {
            boolean flag = bindingResult.hasErrors();
            Map<String, Object> errorMap = new HashMap<>();
            if (flag) {
                List<FieldError> fieldErrors = bindingResult.getFieldErrors();
                for (FieldError fieldError : fieldErrors) {
                    errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
                }
                return errorMap;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
