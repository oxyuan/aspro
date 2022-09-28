package org.atbyuan.aspro.common.tls;

import java.util.Map;

/**
 * @author atbyuan
 * @since 2022/4/24 22:31
 */
public interface MsgContextHolder {

    class Config {

        private static final ThreadLocal<Map<Integer, String>> contextHolder = new ThreadLocal<>();
        public static void init(Map<Integer, String> configMap) {
            contextHolder.set(configMap);
        }
        public static Map<Integer, String> get() {
            return contextHolder.get();
        }
        public static void clear() {
            contextHolder.remove();
        }
    }

}
