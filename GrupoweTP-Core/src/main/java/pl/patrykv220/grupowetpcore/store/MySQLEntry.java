package pl.patrykv220.grupowetpcore.store;

public abstract class MySQLEntry
{
    private boolean changes;
    
    public void changes() {
        this.changes = true;
    }
    
    public abstract String getSQL();
    
    public boolean isChanges() {
        return this.changes;
    }
    
    public void setChanges(final boolean changes) {
        this.changes = changes;
    }
    
    public MySQLEntry() {
        final boolean changes = true;
        this.changes = changes;
    }
    
    public abstract String getName();
    
}
