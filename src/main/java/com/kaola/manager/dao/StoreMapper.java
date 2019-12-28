package com.kaola.manager.dao;

import com.kaola.manager.model.Store;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author linxu
 * @date 2019/11/19
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
@Mapper
public interface StoreMapper {
    /**
     * 查看所有门店信息
     *
     * @return 门店信息
     */
    @Select("SELECT * FROM store")
    @Results(value = {
            @Result(property = "storeId", column = "store_id"),
            @Result(property = "storeName", column = "store_name"),
            @Result(property = "storeAddress", column = "store_address"),
            @Result(property = "storeStatus", column = "store_status"),
            @Result(property = "uid",column = "UID"),
            @Result(property = "tel",column = "tel"),
            @Result(property = "storeTime",column = "store_time"),
            @Result(property = "verifyCode",column = "verify_code")
    })
    List<Store> queryAllStore();

    /**
     * 下架门店
     *
     * @param storeId 门店ID
     */
    @Delete("DELETE FROM store WHERE store_id=#{id}")
    void deleteStore(@Param("id") int storeId);

    /**
     * 添加门店
     *
     * @param store 新的门店
     */
    @Insert("INSERT INTO store (store_name,store_status,store_address,UID,store_time,tel,verify_code) VALUES(#{store.storeName},#{store.storeStatus},#{store.storeAddress},#{store.uid},#{store.storeTime},#{store.tel},#{store.verifyCode})")
    void addStore(@Param("store") Store store);

    /**
     * 修改门店信息
     *
     * @param store 新的门店信息
     */
    @Update("UPDATE store SET store_name=#{s.storeName},store_address=#{s.storeAddress},store_status=#{s.storeStatus},UID=#{s.uid},store_time=#{s.storeTime},tel=#{s.tel},verify_code=#{s.verifyCode} WHERE store_id=#{s.storeId}")
    void modifyStore(@Param("s") Store store);

    @Select("SELECT * FROM store WHERE store_id=#{sid}")
    @Results(value =
            {
                    @Result(property = "storeId", column = "store_id"),
                    @Result(property = "storeName", column = "store_name"),
                    @Result(property = "storeAddress", column = "store_address"),
                    @Result(property = "storeStatus", column = "store_status"),
                    @Result(property = "uid",column = "UID"),
                    @Result(property = "tel",column = "tel"),
                    @Result(property = "storeTime",column = "store_time"),
                    @Result(property = "verifyCode",column = "verify_code")
            })
    Store queryStoreById(@Param("sid") int storeId);
}
