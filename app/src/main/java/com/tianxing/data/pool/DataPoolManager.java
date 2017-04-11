package com.tianxing.data.pool;


import com.tianxing.data.DataManager;
import com.tianxing.data.visitor.Visitor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tianxing on 2017/4/6.
 *
 */

public class DataPoolManager implements DataManager {


    private static DataManager manager;


    private AssignmentPool assignmentPool;
    private ContactsPool contactsPool;
    private InfoPool infoPool;
    private MessagePool messagePool;

    private Map<Class<? extends DataPool>, Object> dataPoolMap;


    private boolean isInitialize = false;


    private DataPoolManager(){}


    public synchronized static DataManager getInstance(){
        if (manager == null){
            manager = new DataPoolManager();
        }
        return manager;
    }


    @Override
    public void initialize() {
        if (!isInitialize){
            dataPoolMap = new HashMap<>();
            dataPoolMap.put(AssignmentPool.class, new AssignmentPoolImpl());
            dataPoolMap.put(ContactsPool.class, new ContactsPoolImpl());
            dataPoolMap.put(InfoPool.class, new InfoPoolImpl());
            dataPoolMap.put(MessagePool.class, new MessagePoolImpl());
        }
        isInitialize = true;
    }

    /**
     * 取得数据池
     *
     * @param dataPool
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends DataPool> T getDataPool(Class<T> dataPool) {
        if (!isInitialize){
            throw new RuntimeException("dataPool is uninitialized");
        }
        Object o = dataPoolMap.get(dataPool);
        if (o == null){
            throw new RuntimeException("dataPool not exist.");
        }
        return (T)o;
    }

    /**
     * 接受数据访问者
     *
     * @param visitor
     */
    @Override
    public void accept(Visitor visitor) {
        if (!isInitialize){
            throw new RuntimeException("dataPool is uninitialized");
        }
        for (Object o : dataPoolMap.values()) {
            ((DataPool)o).accept(visitor);
        }
    }
}
