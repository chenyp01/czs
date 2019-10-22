package com.beasts.czs.service.impl;

import com.beasts.czs.dao.PersonMapper;
import com.beasts.czs.model.po.Person;
import com.beasts.czs.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Auther: chenjie
 * @Date: 2019/3/7 22:14
 * @Description:
 */
@Service
public class PersonServiceImpl implements PersonService{

    @Autowired
    private PersonMapper personMapper;

    public int savePerson(Person person) {
        return personMapper.savePerson(person);
    }

    public int updatePerson(Person person) {
        return personMapper.updatePerson(person);
    }

    public int deleteByid(Long[] ids) {
        return personMapper.deleteByid(ids);
    }

    public List<Person> selectPerson(Person person) {
        return personMapper.selectPerson(person);
    }

    public int savePersonMapper(Person person) {
        return personMapper.insertSelective(person);
    }

    public int updatePersonMapper(Person person,String name) {
        Example example = new Example(Person.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("name",name);

        return personMapper.updateByExample(person,example);
    }

    public List<Person> selectAll() {
        return this.personMapper.selectPerson(null);
    }


    public List<Person> selectPersonByAddr(String addr) {
        return personMapper.selectPersonByAddr(addr);
    }
}
