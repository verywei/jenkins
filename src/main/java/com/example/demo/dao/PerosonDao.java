package com.example.demo.dao;

import com.example.demo.entity.Person;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PerosonDao {
    @Select("select * from person")
    @Results({
            @Result(id =true,column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "address",property = "address",one = @One(
                    select = "com.example.demo.dao.AddressDao.getAddressById"
            ))
    })
    List<Person> getPersonById();
}