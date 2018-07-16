package com.example.demo.dao;

import com.example.demo.entity.Address;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AddressDao {
    @Select("select * from address where id=#{id}")
    Address getAddressById(int id);
}
