package com.tianxing.data.pool;


import com.tianxing.data.DataManager;
import com.tianxing.data.visitor.Visitor;

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
        if (dataPool == AssignmentPool.class){
            return (T)assignmentPool;
        }else if (dataPool == ContactsPool.class){
            return (T) contactsPool;
        }else if (dataPool == InfoPool.class){
            return (T) infoPool;
        }else if (dataPool == MessagePool.class){
            return (T) messagePool;
        }else {
            throw new RuntimeException("dataPool not exist.");
        }
    }

    /**
     * 接受数据访问者
     *
     * @param visitor
     */
    @Override
    public void accept(Visitor visitor) {
        assignmentPool.accept(visitor);
        contactsPool.accept(visitor);
        infoPool.accept(visitor);
        messagePool.accept(visitor);
    }
}
