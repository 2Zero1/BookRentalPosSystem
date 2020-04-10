package common;

/**
 * 시스템 운용 중 발생 가능한 최상위 예외 클래스
 *
 * @author springrunner.kr@gmail.com
 */
@SuppressWarnings("serial")
public class SystemException extends RuntimeException  {

    public SystemException(String foramt, Object...args) {
        super(String.format(foramt, args));
    }

    public SystemException(Throwable cause) {
        super(cause);
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }


    public Object[] getArguments() {
        return new Object[0];
    }

    public String getDefaultMessage() {
        return getMessage();
    }

}
