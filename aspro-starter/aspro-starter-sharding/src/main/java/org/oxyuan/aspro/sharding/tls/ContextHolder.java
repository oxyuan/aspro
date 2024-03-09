package org.oxyuan.aspro.sharding.tls;

/**
 * @author oxyuan
 * @since 2022-10-04 19:01
 */
public class ContextHolder {

    private static final ThreadLocal<String> DBS = new ThreadLocal<>();

    public static void init(String dbName) {
        DBS.set(dbName);
    }

    public static String dbs() {
        return DBS.get();
    }

    public static void clear() {
        DBS.remove();
    }

}
