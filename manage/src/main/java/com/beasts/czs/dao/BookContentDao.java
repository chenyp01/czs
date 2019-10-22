package com.beasts.czs.dao;

import java.util.List;
import org.apache.ibatis.annotations.*;

import org.springframework.stereotype.Repository;
import com.beasts.czs.model.po.BookContent;
import com.beasts.czs.model.vo.BookContentVo;

@Repository
public interface BookContentDao {
	/**
	 * 获取所有的书本信息
	 * @return 所有的书本信息
	 */
	@Select("<script>" +
			"SELECT " +
			 "	ID AS id, "+
			 "	BOOK_ID AS bookId, "+
			 "	CATALOG_ID AS catalogId, "+
			 "	CATALOG_NAME AS catalogName, "+
			 "	CATALOG_UPDATE_TIME AS catalogUpdateTime, "+
			 "	CONTENT AS content, "+
			 "	CREATE_TIME AS createTime, "+
			 "	SOURCE AS source, "+
			 "	SOURCE_NAME AS sourceName, "+
			 "	ORDER_NO AS orderNo "+
			"FROM CZS_BOOK_CONTENT " +
			"</script>") 
	public List<BookContentVo> getAllBookContent();

	/**
	 * 根据主键id获取书本信息
	 * @param id 主键id
	 * @return 相应的书本信息
	 */
	@Select("<script>" +
			"SELECT " +
			 "	ID AS id, "+
			 "	BOOK_ID AS bookId, "+
			 "	CATALOG_ID AS catalogId, "+
			 "	CATALOG_NAME AS catalogName, "+
			 "	CATALOG_UPDATE_TIME AS catalogUpdateTime, "+
			 "	CONTENT AS content, "+
			 "	CREATE_TIME AS createTime, "+
			 "	SOURCE AS source, "+
			 "	SOURCE_NAME AS sourceName, "+
			 "	ORDER_NO AS orderNo "+
			"FROM CZS_BOOK_CONTENT " +
			"WHERE 1=1 " + 
			"<if test=\"id != null and id != ''\">" +
			"	AND ID = #{id}" +
			"</if>" + 
			"</script>") 
	public BookContentVo getBookContent(@Param("id") String id);

	/**
	 * 根据查询条件显示书本信息列表
	 * @param bookContent 查询条件对象
	 * @return 相应的书本信息列表
	 */
	@Select("<script>" +
			"SELECT " +
			 "	ID AS id, "+
			 "	BOOK_ID AS bookId, "+
			 "	CATALOG_ID AS catalogId, "+
			 "	CATALOG_NAME AS catalogName, "+
			 "	CATALOG_UPDATE_TIME AS catalogUpdateTime, "+
			 "	CONTENT AS content, "+
			 "	CREATE_TIME AS createTime, "+
			 "	SOURCE AS source, "+
			 "	SOURCE_NAME AS sourceName, "+
			 "	ORDER_NO AS orderNo "+
			"FROM CZS_BOOK_CONTENT " +
			"WHERE 1=1 " + 
			"<if test=\"bookContent.id != null\">" + 
			"	and ID = #{bookContent.id} " + 
			"</if>" + 
			"<if test=\"bookContent.bookId != null\">" + 
			"	and BOOK_ID = #{bookContent.bookId} " + 
			"</if>" + 
			"<if test=\"bookContent.catalogId != null\">" + 
			"	and CATALOG_ID = #{bookContent.catalogId} " + 
			"</if>" + 
			"<if test=\"bookContent.catalogName != null\">" + 
			"	and CATALOG_NAME = #{bookContent.catalogName} " + 
			"</if>" + 
			"<if test=\"bookContent.catalogUpdateTime != null\">" + 
			"	and CATALOG_UPDATE_TIME = #{bookContent.catalogUpdateTime} " + 
			"</if>" + 
			"<if test=\"bookContent.content != null\">" + 
			"	and CONTENT = #{bookContent.content} " + 
			"</if>" + 
			"<if test=\"bookContent.createTime != null\">" + 
			"	and CREATE_TIME = #{bookContent.createTime} " + 
			"</if>" + 
			"<if test=\"bookContent.source != null\">" + 
			"	and SOURCE = #{bookContent.source} " + 
			"</if>" + 
			"<if test=\"bookContent.sourceName != null\">" + 
			"	and SOURCE_NAME = #{bookContent.sourceName} " + 
			"</if>" + 
			"<if test=\"bookContent.orderNo != null\">" + 
			"	and ORDER_NO = #{bookContent.orderNo} " + 
			"</if>" + 
			"</script>") 
	public List<BookContentVo> bookContentList(@Param("bookContent") BookContentVo bookContent);

	/**
	 * 统计根据查询条件显示书本信息列表记录数
	 * @param bookContent 查询条件对象
	 * @return 相应的书本信息列表记录数
	 */
	@Select("<script>" +
			"SELECT COUNT(1) " +
			"FROM CZS_BOOK_CONTENT " +
			"WHERE 1=1 " + 
			"<if test=\"bookContent.id != null\">" + 
			"	and ID = #{bookContent.id} " + 
			"</if>" + 
			"<if test=\"bookContent.bookId != null\">" + 
			"	and BOOK_ID = #{bookContent.bookId} " + 
			"</if>" + 
			"<if test=\"bookContent.catalogId != null\">" + 
			"	and CATALOG_ID = #{bookContent.catalogId} " + 
			"</if>" + 
			"<if test=\"bookContent.catalogName != null\">" + 
			"	and CATALOG_NAME = #{bookContent.catalogName} " + 
			"</if>" + 
			"<if test=\"bookContent.catalogUpdateTime != null\">" + 
			"	and CATALOG_UPDATE_TIME = #{bookContent.catalogUpdateTime} " + 
			"</if>" + 
			"<if test=\"bookContent.content != null\">" + 
			"	and CONTENT = #{bookContent.content} " + 
			"</if>" + 
			"<if test=\"bookContent.createTime != null\">" + 
			"	and CREATE_TIME = #{bookContent.createTime} " + 
			"</if>" + 
			"<if test=\"bookContent.source != null\">" + 
			"	and SOURCE = #{bookContent.source} " + 
			"</if>" + 
			"<if test=\"bookContent.sourceName != null\">" + 
			"	and SOURCE_NAME = #{bookContent.sourceName} " + 
			"</if>" + 
			"<if test=\"bookContent.orderNo != null\">" + 
			"	and ORDER_NO = #{bookContent.orderNo} " + 
			"</if>" + 
			"</script>") 
	public int countBookContent(@Param("bookContent") BookContentVo bookContent);

	/**
	 * 根据数据实体保存书本信息
	 * @param bookContent 保存数据实体对象
	 * @return 新增的书本信息记录数
	 */
	@Insert("INSERT INTO CZS_BOOK_CONTENT(BOOK_ID,CATALOG_ID,CATALOG_NAME,CATALOG_UPDATE_TIME,CONTENT,CREATE_TIME,SOURCE,SOURCE_NAME,ORDER_NO) " + 
		" VALUES(#{bookContent.bookId},#{bookContent.catalogId},#{bookContent.catalogName},#{bookContent.catalogUpdateTime},#{bookContent.content},#{bookContent.createTime},#{bookContent.source},#{bookContent.sourceName},#{bookContent.orderNo}) ") 
	@Options(useGeneratedKeys = true, keyProperty = "id",keyColumn = "ID")
	public int saveBookContent(@Param("bookContent") BookContent bookContent);

	/**
	 * 根据数据实体修改书本信息
	 * @param bookContent 修改数据实体对象
	 * @return 修改的书本信息记录数
	 */
	@Update("<script> " + 
			"UPDATE CZS_BOOK_CONTENT SET BOOK_ID = #{bookContent.bookId},CATALOG_ID = #{bookContent.catalogId},CATALOG_NAME = #{bookContent.catalogName},CATALOG_UPDATE_TIME = #{bookContent.catalogUpdateTime},CONTENT = #{bookContent.content},CREATE_TIME = #{bookContent.createTime},SOURCE = #{bookContent.source},SOURCE_NAME = #{bookContent.sourceName},ORDER_NO = #{bookContent.orderNo}" + 
			" WHERE ID = #{bookContent.id} " + 
			"</script>") 
	public int updateBookContent(@Param("bookContent") BookContent bookContent);

	/**
	 * 根据主键id删除书本信息
	 * @param id 需删除的主键
	 * @return 删除的书本信息记录数
	 */
	@Delete("<script>" + 
			"delete from CZS_BOOK_CONTENT where ID in" + 
			"<foreach collection=\"ids\" index=\"index\" item=\"id\" open=\"(\" separator=\",\" close=\")\">" + 
			"   #{id}"+ 
			"</foreach>"+ 
			"</script>") 
	public int deleteBookContent(@Param("ids") String[] ids);

}
