package com.bootcamp.utils.exception;

import org.springframework.core.NestedRuntimeException;

/**
 * Created by yaobin on 2016/11/14.
 */
public class DaoException extends NestedRuntimeException {

    private static final long serialVersionUID = 1L;

    public DaoException(String msg) {
        super(msg);
    }

    public DaoException(String msg, Throwable obj) {
        super(msg, obj);
    }
}
