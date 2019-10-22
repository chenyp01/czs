package com.beasts.czs.dao;

import java.util.List;
import org.apache.ibatis.annotations.*;

import org.springframework.stereotype.Repository;
import com.beasts.czs.model.po.UserBookLike;
import com.beasts.czs.model.vo.UserBookLikeVo;

@Repository
public interface UserBookLikeDao {
	/**
	 * 获取所有的用户小说收藏信息表
	 * @return 所有的用户小说收藏信息表
	 */
	@Select("<script>" +
			"SELECT " +
			 "	ID AS id, "+
			 "	USER_ID AS userId, "+
			 "	BOOK_ID AS bookId, "+
			 "	BOOK_NAME AS bookName, "+
			 "	AUTHOR AS author, "+
			 "	CREATE_TIME AS createTime "+
			"FROM CZS_USER_BOOK_LIKE " +
			"</script>") 
	public List<UserBookLikeVo> getAllUserBookLike();

	/**
	 * 根据主键id获取用户小说收藏信息表
	 * @param id 主键id
	 * @return 相应的用户小说收藏信息表
	 */
	@Select("<script>" +
			"SELECT " +
			 "	ID AS id, "+
			 "	USER_ID AS userId, "+
			 "	BOOK_ID AS bookId, "+
			 "	BOOK_NAME AS bookName, "+
			 "	AUTHOR AS author, "+
			 "	CREATE_TIME AS createTime "+
			"FROM CZS_USER_BOOK_LIKE " +
			"WHERE 1=1 " + 
			"<if test=\"id != null and id != ''\">" +
			"	AND ID = #{id}" +
			"</if>" + 
			"</script>") 
	public UserBookLikeVo getUserBookLike(@Param("id") String id);

	/**
	 * 根据查询条件显示用户小说收藏信息表列表
	 * @param userBookLike 查询条件对象
	 * @return 相应的用户小说收藏信息表列表
	 */
	@Select("<script>" +
			"SELECT " +
			 "	ID AS id, "+
			 "	USER_ID AS userId, "+
			 "	BOOK_ID AS bookId, "+
			 "	BOOK_NAME AS bookName, "+
			 "	AUTHOR AS author, "+
			 "	CREATE_TIME AS createTime "+
			"FROM CZS_USER_BOOK_LIKE " +
			"WHERE 1=1 " + 
			"<if test=\"userBookLike.id != null\">" + 
			"	and ID = #{userBookLike.id} " + 
			"</if>" + 
			"<if test=\"userBookLike.userId != null\">" + 
			"	and USER_ID = #{userBookLike.userId} " + 
			"</if>" + 
			"<if test=\"userBookLike.bookId != null\">" + 
			"	and BOOK_ID = #{userBookLike.bookId} " + 
			"</if>" + 
			"<if test=\"userBookLike.bookName != null\">" + 
			"	and BOOK_NAME = #{userBookLike.bookName} " + 
			"</if>" + 
			"<if test=\"userBookLike.author != null\">" + 
			"	and AUTHOR = #{userBookLike.author} " + 
			"</if>" + 
			"<if test=\"userBookLike.createTime != null\">" + 
			"	and CREATE_TIME = #{userBookLike.createTime} " + 
			"</if>" + 
			"</script>") 
	public List<UserBookLikeVo> userBookLikeList(@Param("userBookLike") UserBookLikeVo userBookLike);

	/**
	 * 统计根据查询条件显示用户小说收藏信息表列表记录数
	 * @param userBookLike 查询条件对象
	 * @return 相应的用户小说收藏信息表列表记录数
	 */
	@Select("<script>" +
			"SELECT COUNT(1) " +
			"FROM CZS_USER_BOOK_LIKE " +
			"WHERE 1=1 " + 
			"<if test=\"userBookLike.id != null\">" + 
			"	and ID = #{userBookLike.id} " + 
			"</if>" + 
			"<if test=\"userBookLike.userId != null\">" + 
			"	and USER_ID = #{userBookLike.userId} " + 
			"</if>" + 
			"<if test=\"userBookLike.bookId != null\">" + 
			"	and BOOK_ID = #{userBookLike.bookId} " + 
			"</if>" + 
			"<if test=\"userBookLike.bookName != null\">" + 
			"	and BOOK_NAME = #{userBookLike.bookName} " + 
			"</if>" + 
			"<if test=\"userBookLike.author != null\">" + 
			"	and AUTHOR = #{userBookLike.author} " + 
			"</if>" + 
			"<if test=\"userBookLike.createTime != null\">" + 
			"	and CREATE_TIME = #{userBookLike.createTime} " + 
			"</if>" + 
			"</script>") 
	public int countUserBookLike(@Param("userBookLike") UserBookLikeVo userBookLike);

	/**
	 * 根据数据实体保存用户小说收藏信息表
	 * @param userBookLike 保存数据实体对象
	 * @return 新增的用户小说收藏信息表记录数
	 */
	@Insert("INSERT INTO CZS_USER_BOOK_LIKE(USER_ID,BOOK_ID,BOOK_NAME,AUTHOR,CREATE_TIME) " + 
		" VALUES(#{userBookLike.userId},#{userBookLike.bookId},#{userBookLike.bookName},#{userBookLike.author},#{userBookLike.createTime}) ") 
	@Options(useGeneratedKeys = true, keyProperty = "id",keyColumn = "ID")
	public int saveUserBookLike(@Param("userBookLike") UserBookLike userBookLike);

	/**
	 * 根据数据实体修改用户小说收藏信息表
	 * @param userBookLike 修改数据实体对象
	 * @return 修改的用户小说收藏信息表记录数
	 */
	@Update("<script> " + 
			"UPDATE CZS_USER_BOOK_LIKE SET USER_ID = #{userBookLike.userId},BOOK_ID = #{userBookLike.bookId},BOOK_NAME = #{userBookLike.bookName},AUTHOR = #{userBookLike.author},CREATE_TIME = #{userBookLike.createTime}" + 
			" WHERE ID = #{userBookLike.id} " + 
			"</script>") 
	public int updateUserBookLike(@Param("userBookLike") UserBookLike userBookLike);

	/**
	 * 根据主键id删除用户小说收藏信息表
	 * @param ids 需删除的主键
	 * @return 删除的用户小说收藏信息表记录数
	 */
	@Delete("<script>" + 
			"delete from CZS_USER_BOOK_LIKE where ID in" + 
			"<foreach collection=\"ids\" index=\"index\" item=\"id\" open=\"(\" separator=\",\" close=\")\">" + 
			"   #{id}"+ 
			"</foreach>"+ 
			"</script>") 
	public int deleteUserBookLikes(@Param("ids") String[] ids);

	/**
	 * 根据书名ID和用户ID删除收藏信息
	 * @param bookId
	 * @param userId
	 * @return
	 */
	@Delete("<script>" +
			"delete from CZS_USER_BOOK_LIKE where BOOK_ID = #{bookId} AND USER_ID = #{uesrId}"+
			"</script>")
	public int deleteUserBookLike(@Param("bookId") String bookId, @Param("userId") String userId);

}
