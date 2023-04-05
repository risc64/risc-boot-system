package com.risc.boot.util;

/**
 * 异常输出
 * @author 李良发
 * @version 1.0.0
 * @since 2022/4/19 14:42
 */
public class ExceptionUtil {

    /**
     * 异常拼接
     * @param e
     * @return
     */
    public static String getErrorString(Exception e) {
        StringBuffer sb = new StringBuffer();
        sb.append(e.toString());
        for (StackTraceElement st: e.getStackTrace()) {
            sb.append("\n at ");
            sb.append(st.getClassName());
            sb.append("(");
            sb.append(st.getFileName()); 
            sb.append(":");
            sb.append(st.getLineNumber());
            sb.append(")");
        }
        return sb.toString();
    }
}
