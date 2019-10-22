package com.beasts.czs.dao;

import com.beasts.czs.common.BaseMapper;
import com.beasts.czs.model.po.Person;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface PersonMapper extends BaseMapper<Person> {
    @Insert("<script>" +
            "insert person(name, age, address) values (#{person.name},#{person.age},#{person.address})" +
            "</script>")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="ID")
    public int savePerson(@Param("person") Person person);

    @Update("<script>" +
            "update person " +
            "<trim prefix=\"set\" suffixOverrides=\",\">" +
            "   <if test=\"name != null and name != ''\">" +
            "       name = #{name}," +
            "   </if>" +
            "   <if test=\"age != null\">" +
            "       age = #{age}," +
            "   </if>" +
            "   <if test=\"address != null and address != ''\">" +
            "       address = #{address}," +
            "   </if>" +
            "</trim>" +
            " where id = #{id}" +
            "</script>")
    public int updatePerson(Person person);

    @Delete("<script>" +
            "delete from person where id in" +
            "<foreach collection=\"ids\" index=\"index\" item=\"id\" open=\"(\" separator=\",\" close=\")\">" +
            "   #{id}" +
            "</foreach>" +
            "</script>")
    public int deleteByid(@Param("ids") Long[] ids);

    @Select("<script>" +
            "select id , name , age , address from person " +
            "<trim prefix=\"where\" prefixOverrides=\"and |or\">" +
            "   <if test=\"name != null and name != ''\">" +
            "       and name = #{name}" +
            "   </if>" +
            "   <if test=\"age != null\">" +
            "       and age = #{age}" +
            "   </if>" +
            "   <if test=\"address != null and address != ''\">" +
            "       and address = #{address}" +
            "   </if>" +
            "</trim>" +
            "</script>")
    public List<Person> selectPerson(Person person);

    @Select("<script>" +
            "select id,name,age,address from person where address like concat('%',#{addr},'%')" +
            "</script>")
    public List<Person> selectPersonByAddr(@Param("addr") String addr);
}
