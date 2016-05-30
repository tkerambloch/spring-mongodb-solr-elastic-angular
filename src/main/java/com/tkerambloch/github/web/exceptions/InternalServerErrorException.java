package com.tkerambloch.github.web.exceptions;

/**
 * Created by tkerambloch on 16/02/2016.
 */
public class InternalServerErrorException extends RuntimeException {
    
    private Exception catchedException;

    public InternalServerErrorException() {}
    
    public InternalServerErrorException(Exception e) {
        catchedException = e;
    }

    /**
     * @return the catchedException
     */
    public Exception getCatchedException() {
        return catchedException;
    }

    /**
     * @param catchedException the catchedException to set
     */
    public void setCatchedException(Exception catchedException) {
        this.catchedException = catchedException;
    }
    
    
}
