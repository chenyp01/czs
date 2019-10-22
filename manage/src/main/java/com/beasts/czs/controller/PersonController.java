package com.beasts.czs.controller;

import com.beasts.czs.config.ConstantConfig;
import com.beasts.czs.model.po.Person;
import com.beasts.czs.service.PersonService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: chenjie
 * @Date: 2019/3/7 22:21
 * @Description:
 */
@RestController
@RequestMapping("/person")
@PropertySource(value= "classpath:bootstrap.yml")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/get_person_by_name")
    public List<Person> getPersonByName(@RequestParam("name") String name){
        Person person = new Person(name);

        List<Person> personList = personService.selectPerson(person);
        return  personList;
    }

    @PostMapping("/save_person")
    public int savePerson(@RequestBody Person person){
        return personService.savePerson(person);
    }

    @PutMapping("/update_person")
    public int updatePerson(@RequestBody Person person){
        return personService.updatePerson(person);
    }

    @DeleteMapping("/delete_person")
    public int deletePerson(@RequestBody Long[] ids){
        return personService.deleteByid(ids);
    }

    @PostMapping("/spm")
    public int savePersonMapper(@RequestBody Person person){
        return personService.savePersonMapper(person);
    }

    @PutMapping("/update_person_mapper")
    public int updatePersonMapper(Person person , String sname){
        return personService.updatePersonMapper(person,sname);
    }

    @GetMapping("/all_person")
    public PageInfo<Person> allPerson(@RequestParam(value = "page",defaultValue = "1")int page,
                                      @RequestParam(value = "size",defaultValue = ConstantConfig.PAGESIZE)int size){

        PageHelper.startPage(page, size);
        List<Person> personList = personService.selectAll();

        PageInfo<Person> pageInfo = new PageInfo<>(personList);

        return pageInfo;
    }

    @PostMapping("/select_person")
    public PageInfo<Person> selectPerson(@RequestParam(value = "page",defaultValue = "1")int page,
                                         @RequestParam(value = "size",defaultValue = ConstantConfig.PAGESIZE)int size,
                                         Person person){
        if(person == null){
            return null;
        }

        PageHelper.startPage(page, size);
        List<Person> personList = personService.selectPersonByAddr(person.getAddress());

        PageInfo<Person> pageInfo = new PageInfo<>(personList);

        return pageInfo;

    }

}
