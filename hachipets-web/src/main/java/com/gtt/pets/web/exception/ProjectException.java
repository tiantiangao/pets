package com.gtt.pets.web.exception;

/**
 * Created with IntelliJ IDEA.
 * User: gtt
 * Date: 13-10-5
 * Time: 下午2:53
 * To change this template use File | Settings | File Templates.
 */
public class ProjectException extends Exception {

    public ProjectException() {
        super("系统异常");
    }

    public ProjectException(String message) {
        super(message);
    }

    public ProjectException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProjectException(Throwable cause) {
        super(cause);
    }
}
