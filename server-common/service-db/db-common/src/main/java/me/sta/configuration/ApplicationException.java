package me.sta.configuration;

/**
 * Created by liujun.
 */
public class ApplicationException extends RuntimeException {
    private ApplicationCode applicationCode;

    public ApplicationCode getApplicationCode() {
        return applicationCode;
    }

    public ApplicationException(ApplicationCode applicationCode) {
        super(applicationCode.getMessage());
        this.applicationCode = applicationCode;
    }

    public ApplicationException(ApplicationCode applicationCode, Throwable cause) {
        super(applicationCode.getMessage(), cause);
        this.applicationCode = applicationCode;
    }
}
