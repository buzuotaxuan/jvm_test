package netty;

public enum Command {
	HEARTBEAT(1),
	
    HANDSHAKE(2),
    
    UNKNOWN(-1);
    
    Command(int cmd) {
//    	System.out.println("----"+ this.cmd);
        this.cmd = (byte) cmd;
        System.out.println("----"+ this.cmd);
    }

    public final byte cmd;
    
    
    public static Command toCMD(byte b) {
        Command[] values = values();
        if (b > 0 && b < values.length) return values[b - 1];
        return UNKNOWN;
    }
}
