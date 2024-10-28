package Model;

import java.util.concurrent.atomic.AtomicLong;

public abstract class DataStoreObj {
    private static final AtomicLong nextPK = new AtomicLong(Long.MAX_VALUE);
    private long pk;

    public long getPrimaryKey() {
        return pk;
    }

    private Long nextPK() {
        return nextPK.getAndDecrement();
    }

    public DataStoreObj() {
        pk = nextPK();
    }

    public DataStoreObj(Long id) {
        if (id == null) {
            pk = nextPK();
        } else {
            this.pk = id;
        }
    }
}
