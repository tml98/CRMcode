package com.tml.pojo.msg;

import java.util.HashMap;
import java.util.Map;

public class Result {
    public static final Map<String,Object> MSG=new HashMap<String,Object>(){{
        put("success",true);
        put("msg","操作成功!");
    }};
}
