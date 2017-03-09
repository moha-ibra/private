package exceptions;

public class VerplichtGeenLeestekensException extends RuntimeException
{
    public VerplichtGeenLeestekensException()
    {
    }
    
    public VerplichtGeenLeestekensException(String message)
    {
        super(message);
    }

    public VerplichtGeenLeestekensException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public VerplichtGeenLeestekensException(Throwable cause)
    {
        super(cause);
    }

    public VerplichtGeenLeestekensException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
