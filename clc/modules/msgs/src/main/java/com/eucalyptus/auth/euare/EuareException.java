package com.eucalyptus.auth.euare;

public class EuareException extends Exception {

  private static final long serialVersionUID = 1L;

  public static final String ENTITY_ALREADY_EXISTS = "EntityAlreadyExists";
  public static final String LIMIT_EXCEEDED = "LimitExceeded";
  public static final String NO_SUCH_ENTITY = "NoSuchEntity";
  
  private String code;
  private String error;
  
  public EuareException( String code, String error ) {
    super( );
    this.code = code;
    this.error = error;
  }
  
  public EuareException( String code, String error, String message, Throwable cause ) {
    super( message, cause );
    this.code = code;
    this.error = error;
  }
  
  public EuareException( String code, String error, String message ) {
    super( message );
    this.code = code;
    this.error = error;
  }
  
  public EuareException( String code, String error, Throwable cause ) {
    super( cause );
    this.code = code;
    this.error = error;
  }
  
  public String getCode( ) {
    return this.code;
  }
  
  public String getError( ) {
    return this.error;
  }
  
}
