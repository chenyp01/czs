package com.beasts.czs.dao;

import java.util.List;
import org.apache.ibatis.annotations.*;

import org.springframework.stereotype.Repository;
import com.beasts.czs.model.po.User;
import com.beasts.czs.model.vo.UserVo;

@Repository
public interface UserDao {
	/**
	 * 获取所有的用户信息
	 * @return 所有的用户信息
	 */
	@Select("<script>" +
			"SELECT " +
			 "	ID AS id, "+
			 "	USER_NAME AS userName, "+
			 "	PASSWORD AS password, "+
			 "	OTHER_NAME AS otherName, "+
			 "	PHONE_NO AS phoneNo, "+
			 "	CARD_NO AS cardNo, "+
			 "	REAL_NAME AS realName, "+
			 "	USER_TYPE AS userType, "+
			 "	REMARK AS remark, "+
			 "	STATUS AS status, "+
			 "	CREATE_TIME AS createTime, "+
			 "	HEAD_IMG AS headImg "+
			"FROM CZS_USER " +
			"</script>") 
	public List<UserVo> getAllUser();

	/**
	 * 根据主键id获取用户信息
	 * @param id 主键id
	 * @return 相应的用户信息
	 */
	@Select("<script>" +
			"SELECT " +
			 "	ID AS id, "+
			 "	USER_NAME AS userName, "+
			 "	PASSWORD AS password, "+
			 "	OTHER_NAME AS otherName, "+
			 "	PHONE_NO AS phoneNo, "+
			 "	CARD_NO AS cardNo, "+
			 "	REAL_NAME AS realName, "+
			 "	USER_TYPE AS userType, "+
			 "	REMARK AS remark, "+
			 "	STATUS AS status, "+
			 "	CREATE_TIME AS createTime, "+
			 "	HEAD_IMG AS headImg "+
			"FROM CZS_USER " +
			"WHERE 1=1 " + 
			"<if test=\"id != null and id != ''\">" +
			"	AND ID = #{id}" +
			"</if>" + 
			"</script>") 
	public UserVo getUser(@Param("id") String id);

	/**
	 * 根据查询条件显示用户信息列表
	 * @param user 查询条件对象
	 * @return 相应的用户信息列表
	 */
	@Select("<script>" +
			"SELECT " +
			 "	ID AS id, "+
			 "	USER_NAME AS userName, "+
			 "	PASSWORD AS password, "+
			 "	OTHER_NAME AS otherName, "+
			 "	PHONE_NO AS phoneNo, "+
			 "	CARD_NO AS cardNo, "+
			 "	REAL_NAME AS realName, "+
			 "	USER_TYPE AS userType, "+
			 "	REMARK AS remark, "+
			 "	STATUS AS status, "+
			 "	CREATE_TIME AS createTime, "+
			 "	HEAD_IMG AS headImg "+
			"FROM CZS_USER " +
			"WHERE 1=1 " + 
			"<if test=\"user.id != null\">" + 
			"	and ID = #{user.id} " + 
			"</if>" + 
			"<if test=\"user.userName != null\">" + 
			"	and USER_NAME = #{user.userName} " + 
			"</if>" + 
			"<if test=\"user.password != null\">" + 
			"	and PASSWORD = #{user.password} " + 
			"</if>" + 
			"<if test=\"user.otherName != null\">" + 
			"	and OTHER_NAME = #{user.otherName} " + 
			"</if>" + 
			"<if test=\"user.phoneNo != null\">" + 
			"	and PHONE_NO = #{user.phoneNo} " + 
			"</if>" + 
			"<if test=\"user.cardNo != null\">" + 
			"	and CARD_NO = #{user.cardNo} " + 
			"</if>" + 
			"<if test=\"user.realName != null\">" + 
			"	and REAL_NAME = #{user.realName} " + 
			"</if>" + 
			"<if test=\"user.userType != null\">" + 
			"	and USER_TYPE = #{user.userType} " + 
			"</if>" + 
			"<if test=\"user.remark != null\">" + 
			"	and REMARK = #{user.remark} " + 
			"</if>" + 
			"<if test=\"user.status != null\">" + 
			"	and STATUS = #{user.status} " + 
			"</if>" + 
			"<if test=\"user.createTime != null\">" + 
			"	and CREATE_TIME = #{user.createTime} " + 
			"</if>" + 
			"<if test=\"user.headImg != null\">" + 
			"	and HEAD_IMG = #{user.headImg} " + 
			"</if>" + 
			"</script>") 
	public List<UserVo> userList(@Param("user") UserVo user);

	/**
	 * 统计根据查询条件显示用户信息列表记录数
	 * @param user 查询条件对象
	 * @return 相应的用户信息列表记录数
	 */
	@Select("<script>" +
			"SELECT COUNT(1) " +
			"FROM CZS_USER " +
			"WHERE 1=1 " + 
			"<if test=\"user.id != null\">" + 
			"	and ID = #{user.id} " + 
			"</if>" + 
			"<if test=\"user.userName != null\">" + 
			"	and USER_NAME = #{user.userName} " + 
			"</if>" + 
			"<if test=\"user.password != null\">" + 
			"	and PASSWORD = #{user.password} " + 
			"</if>" + 
			"<if test=\"user.otherName != null\">" + 
			"	and OTHER_NAME = #{user.otherName} " + 
			"</if>" + 
			"<if test=\"user.phoneNo != null\">" + 
			"	and PHONE_NO = #{user.phoneNo} " + 
			"</if>" + 
			"<if test=\"user.cardNo != null\">" + 
			"	and CARD_NO = #{user.cardNo} " + 
			"</if>" + 
			"<if test=\"user.realName != null\">" + 
			"	and REAL_NAME = #{user.realName} " + 
			"</if>" + 
			"<if test=\"user.userType != null\">" + 
			"	and USER_TYPE = #{user.userType} " + 
			"</if>" + 
			"<if test=\"user.remark != null\">" + 
			"	and REMARK = #{user.remark} " + 
			"</if>" + 
			"<if test=\"user.status != null\">" + 
			"	and STATUS = #{user.status} " + 
			"</if>" + 
			"<if test=\"user.createTime != null\">" + 
			"	and CREATE_TIME = #{user.createTime} " + 
			"</if>" + 
			"<if test=\"user.headImg != null\">" + 
			"	and HEAD_IMG = #{user.headImg} " + 
			"</if>" + 
			"</script>") 
	public int countUser(@Param("user") UserVo user);

	/**
	 * 根据数据实体保存用户信息
	 * @param user 保存数据实体对象
	 * @return 新增的用户信息记录数
	 */
	@Insert("INSERT INTO CZS_USER(USER_NAME,PASSWORD,OTHER_NAME,PHONE_NO,CARD_NO,REAL_NAME,USER_TYPE,REMARK,STATUS,CREATE_TIME,HEAD_IMG) " + 
		" VALUES(#{user.userName},#{user.password},#{user.otherName},#{user.phoneNo},#{user.cardNo},#{user.realName},#{user.userType},#{user.remark},#{user.status},#{user.createTime},#{user.headImg}) ") 
	@Options(useGeneratedKeys = true, keyProperty = "id",keyColumn = "ID")
	public int saveUser(@Param("user") User user);

	/**
	 * 根据数据实体修改用户信息
	 * @param user 修改数据实体对象
	 * @return 修改的用户信息记录数
	 */
	@Update("<script> " + 
			"UPDATE CZS_USER SET USER_NAME = #{user.userName},PASSWORD = #{user.password},OTHER_NAME = #{user.otherName},PHONE_NO = #{user.phoneNo},CARD_NO = #{user.cardNo},REAL_NAME = #{user.realName},USER_TYPE = #{user.userType},REMARK = #{user.remark},STATUS = #{user.status},CREATE_TIME = #{user.createTime},HEAD_IMG = #{user.headImg}" + 
			" WHERE ID = #{user.id} " + 
			"</script>") 
	public int updateUser(@Param("user") User user);

	/**
	 * 根据主键id删除用户信息
	 * @param ids 需删除的主键
	 * @return 删除的用户信息记录数
	 */
	@Delete("<script>" + 
			"delete from CZS_USER where ID in" + 
			"<foreach collection=\"ids\" index=\"index\" item=\"id\" open=\"(\" separator=\",\" close=\")\">" + 
			"   #{id}"+ 
			"</foreach>"+ 
			"</script>") 
	public int deleteUser(@Param("ids") String[] ids);

}
