package FunctionLayer;

import java.util.logging.Level;

public class FOGException extends Exception {

    public final Level LEVEL;

    public FOGException(String msg) {
        this(msg, Level.OFF);
    }
    
    public FOGException(String msg, Level level) {
        super(msg);
        this.LEVEL = level;
    }

}
