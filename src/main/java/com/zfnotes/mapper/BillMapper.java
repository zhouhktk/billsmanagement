package com.zfnotes.mapper;

import com.zfnotes.entities.Bill;
import com.zfnotes.entities.BillProvider;

import java.util.List;

/**
 * 使用Mybatis的注解版
 */
// @Mapper // 指定这是操作数据库的mapper
public interface BillMapper {

    List<BillProvider> getBill(Bill bill);

    BillProvider getBillById(Integer bid);

    int addBill(Bill bill);

    int deleteBill(Integer bid);

    int updateBill(Bill bill);

}
