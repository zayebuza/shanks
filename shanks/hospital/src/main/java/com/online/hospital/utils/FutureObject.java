package com.online.hospital.utils;

import java.util.Objects;

/**
 * Created by miaodongbiao
 * Date:2018/4/21-20:17
 * Description:
 */
public class FutureObject {

    public static FutureObject of(){
        return new FutureObject();
    }

    public void notNull(Object... objs) {
        if (objs.length == 0) {
            throw new IllegalArgumentException("check obj at last give one");
        }

        for (Object obj : objs) {
            if (Objects.isNull(obj)) throw new IllegalStateException("obj is null");
        }
    }
}
