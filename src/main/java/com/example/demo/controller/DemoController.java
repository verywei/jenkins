package com.example.demo.controller;

import com.example.demo.dao.AddressDao;
import com.example.demo.dao.PerosonDao;
import com.example.demo.entity.Address;
import com.example.demo.entity.Person;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class DemoController {

    @Autowired
    private PerosonDao perosonDao;
    @Autowired
    private AddressDao addressDao;
    @Autowired
    private RedisTemplate redisTemplate;
    @Value("${msg:default}")
    String msg;


    @RequestMapping("/{page}")
    @Cacheable(key = "'Person'+#page", value = "persons")
    public List<Person> index(@PathVariable("page") int page) {
        PageHelper.startPage(page, 10);
        List<Person> persons = perosonDao.getPersonById();
        return persons;
    }

    @RequestMapping("/")
    public List<Person> all(HttpSession session) {
        System.out.println(msg);
        return perosonDao.getPersonById();
    }

    @RequestMapping("/add")
    public Address add() {
        return addressDao.getAddressById(1);
    }
}
