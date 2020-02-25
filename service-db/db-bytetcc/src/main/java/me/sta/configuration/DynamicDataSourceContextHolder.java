//package me.sta.configuration;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Stack;
//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReentrantLock;
//
///**
// * Multiple DataSource Context Holder
// *
// * @author HelloWood
// * @date 2017 -08-15 14:26
// * @Email hellowoodes @gmail.com
// */
//public class DynamicDataSourceContextHolder {
//
//    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceContextHolder.class);
//
//    private static Lock lock = new ReentrantLock();
//
//    private static int counter = 0;
//
//    /**
//     * Maintain variable for every thread, to avoid effect other thread
//     */
//    private static final ThreadLocal<String> CONTEXT_HOLDER = ThreadLocal.withInitial(DataSourceKey.masterSource::name);
//
//    private static final ThreadLocal<Stack<String>> CONTEXT_HOLDED_HISTORY = new ThreadLocal<>();
//
//
//    /**
//     * All DataSource List
//     */
//    public static List<Object> dataSourceKeys = new ArrayList<>();
//
//    /**
//     * The constants slaveDataSourceKeys.
//     */
//    public static List<Object> slaveDataSourceKeys = new ArrayList<>();
//
//    /**
//     * To switch DataSource
//     *
//     * @param key the key
//     */
//    public static void setDataSourceKey(String key) {
//        CONTEXT_HOLDER.set(key);
//        Stack<String> sta = CONTEXT_HOLDED_HISTORY.get();
//        if (sta == null){
//            sta = new Stack<String>();
//            sta.push(key);
//            CONTEXT_HOLDED_HISTORY.set(sta);
//        }else{
//            sta.push(key);
//        }
//    }
//
//    /**
//     * Use master data source.
//     */
//    public static void useMasterDataSource() {
//        CONTEXT_HOLDER.set(DataSourceKey.masterSource.name());
//    }
//
//    /**
//     * Get current DataSource
//     *
//     * @return data source key
//     */
//    public static String getDataSourceKey() {
//        return CONTEXT_HOLDER.get();
//    }
//
//    /**
//     * 数据源栈
//     * @return
//     */
//    public static Stack<String> getDataSourceKeyStack(){
//        return CONTEXT_HOLDED_HISTORY.get();
//    }
//
//    /**
//     * To set DataSource as default
//     */
//    public static void clearDataSourceKey() {
//        CONTEXT_HOLDER.remove();
//        Stack<String> sta = CONTEXT_HOLDED_HISTORY.get();
//        sta.pop();
//        if (!sta.isEmpty()){
//            String peek = sta.peek();
//            CONTEXT_HOLDER.set(peek);
//        }
//
//    }
//
//    /**
//     * Check if give DataSource is in current DataSource list
//     *
//     * @param key the key
//     * @return boolean boolean
//     */
//    public static boolean containDataSourceKey(String key) {
//        return dataSourceKeys.contains(key);
//    }
//}
