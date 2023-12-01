package pl.patrykv220.grupowetpcore.utils;

public class Timming
{
    private long startTime;
    private long endTime;
    private long nanoStartTime;
    private long nanoEndTime;
    private final String name;
    
    public Timming(final String name) {
        this.name = name;
    }
    
    public Timming start() {
        this.startTime = System.currentTimeMillis();
        this.nanoStartTime = System.nanoTime();
        return this;
    }
    
    public Timming stop() {
        this.endTime = System.currentTimeMillis();
        this.nanoEndTime = System.nanoTime();
        return this;
    }
    
    public long getExecutingTime() {
        if (this.startTime == 0L || this.endTime == 0L) {
            return 0L;
        }
        return this.endTime - this.startTime;
    }
    
    public long getExecutingNanoTime() {
        if (this.nanoStartTime == 0L || this.nanoEndTime == 0L) {
            return 0L;
        }
        return this.nanoEndTime - this.nanoStartTime;
    }
    
    @Override
    public String toString() {
        return String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.name)))) + " executing time: " + this.getExecutingTime() + "ms (" + this.getExecutingNanoTime() + "ns)";
    }
    
    public long getStartTime() {
        return this.startTime;
    }
    
    public long getEndTime() {
        return this.endTime;
    }
    
    public long getNanoStartTime() {
        return this.nanoStartTime;
    }
    
    public long getNanoEndTime() {
        return this.nanoEndTime;
    }
    
    public String getName() {
        return this.name;
    }
}

