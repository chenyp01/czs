package com.beasts.czs.service;

import com.beasts.czs.model.po.Person;

import java.util.List;

public interface PersonService {
    public int savePerson(Person person);
    public int updatePerson(Person person);
    public int deleteByid(Long[] ids);
    public List<Person> selectPerson(Person person);

    public int savePersonMapper(Person person);
    public int updatePersonMapper(Person person , String name);

    public List<Person> selectAll();

    public List<Person> selectPersonByAddr(String addr);
}
