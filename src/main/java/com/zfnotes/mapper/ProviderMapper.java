package com.zfnotes.mapper;

import com.zfnotes.entities.Provider;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 使用Mybatis的注解版
 */
// @Mapper // 指定这是操作数据库的mapper
public interface ProviderMapper {

    List<Provider> getProvider(Provider provider);

    Provider getProviderById(Integer pid);

    int addProvider(Provider provider);
    int deleteProviderById(Integer pid);
    int updateProvider(Provider provider);

}
