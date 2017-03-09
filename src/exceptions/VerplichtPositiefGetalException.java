package exceptions;

public class VerplichtPositiefGetalException extends RuntimeException
{
    public VerplichtPositiefGetalException()
    {
    }
    
    public VerplichtPositiefGetalException(String message)
    {
        super(message);
    }

    public VerplichtPositiefGetalException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public VerplichtPositiefGetalException(Throwable cause)
    {
        super(cause);
    }

    public VerplichtPositiefGetalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
