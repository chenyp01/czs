package com.beasts.czs.dao;

import java.util.List;
import org.apache.ibatis.annotations.*;

import org.springframework.stereotype.Repository;
import com.beasts.czs.model.po.BookInfo;
import com.beasts.czs.model.vo.BookInfoVo;

@Repository
public interface BookInfoDao {
	/**
	 * 获取所有的书本信息
	 * @return 所有的书本信息
	 */
	@Select("<script>" +
			"SELECT " +
			 "	ID AS id, "+
			 "	BOOK_NAME AS bookName, "+
			 "	AUTHOR AS author, "+
			 "	BOOK_IMG AS bookImg, "+
			 "	TYPE AS type, "+
			 "	STATUS AS status, "+
			 "	UPDATE_TIME AS updateTime, "+
			 "	WORD_NUM AS wordNum, "+
			 "	REMARK AS remark, "+
			 "	CREATE_TIME AS createTime, "+
			 "	SOURCE AS source, "+
			 "	SOURCE_NAME AS sourceName, "+
			 "	BOOK_ID AS bookId, "+
			 "	NEW_CATALOG AS newCatalog "+
			"FROM CZS_BOOK_INFO " +
			"</script>") 
	public List<BookInfoVo> getAllBookInfo();

	/**
	 * 根据主键id获取书本信息
	 * @param id 主键id
	 * @return 相应的书本信息
	 */
	@Select("<script>" +
			"SELECT " +
			 "	ID AS id, "+
			 "	BOOK_NAME AS bookName, "+
			 "	AUTHOR AS author, "+
			 "	BOOK_IMG AS bookImg, "+
			 "	TYPE AS type, "+
			 "	STATUS AS status, "+
			 "	UPDATE_TIME AS updateTime, "+
			 "	WORD_NUM AS wordNum, "+
			 "	REMARK AS remark, "+
			 "	CREATE_TIME AS createTime, "+
			 "	SOURCE AS source, "+
			 "	SOURCE_NAME AS sourceName, "+
			 "	BOOK_ID AS bookId, "+
			 "	NEW_CATALOG AS newCatalog "+
			"FROM CZS_BOOK_INFO " +
			"WHERE 1=1 " + 
			"<if test=\"id != null and id != ''\">" +
			"	AND ID = #{id}" +
			"</if>" + 
			"</script>") 
	public BookInfoVo getBookInfo(@Param("id") String id);

	/**
	 * 根据查询条件显示书本信息列表
	 * @param bookInfo 查询条件对象
	 * @return 相应的书本信息列表
	 */
	@Select("<script>" +
			"SELECT " +
			 "	ID AS id, "+
			 "	BOOK_NAME AS bookName, "+
			 "	AUTHOR AS author, "+
			 "	BOOK_IMG AS bookImg, "+
			 "	TYPE AS type, "+
			 "	STATUS AS status, "+
			 "	UPDATE_TIME AS updateTime, "+
			 "	WORD_NUM AS wordNum, "+
			 "	REMARK AS remark, "+
			 "	CREATE_TIME AS createTime, "+
			 "	SOURCE AS source, "+
			 "	SOURCE_NAME AS sourceName, "+
			 "	BOOK_ID AS bookId, "+
			 "	NEW_CATALOG AS newCatalog "+
			"FROM CZS_BOOK_INFO " +
			"WHERE 1=1 " + 
			"<if test=\"bookInfo.id != null\">" + 
			"	and ID = #{bookInfo.id} " + 
			"</if>" + 
			"<if test=\"bookInfo.bookName != null\">" + 
			"	and BOOK_NAME = #{bookInfo.bookName} " + 
			"</if>" + 
			"<if test=\"bookInfo.author != null\">" + 
			"	and AUTHOR = #{bookInfo.author} " + 
			"</if>" + 
			"<if test=\"bookInfo.bookImg != null\">" + 
			"	and BOOK_IMG = #{bookInfo.bookImg} " + 
			"</if>" + 
			"<if test=\"bookInfo.type != null\">" + 
			"	and TYPE = #{bookInfo.type} " + 
			"</if>" + 
			"<if test=\"bookInfo.status != null\">" + 
			"	and STATUS = #{bookInfo.status} " + 
			"</if>" + 
			"<if test=\"bookInfo.updateTime != null\">" + 
			"	and UPDATE_TIME = #{bookInfo.updateTime} " + 
			"</if>" + 
			"<if test=\"bookInfo.wordNum != null\">" + 
			"	and WORD_NUM = #{bookInfo.wordNum} " + 
			"</if>" + 
			"<if test=\"bookInfo.remark != null\">" + 
			"	and REMARK = #{bookInfo.remark} " + 
			"</if>" + 
			"<if test=\"bookInfo.createTime != null\">" + 
			"	and CREATE_TIME = #{bookInfo.createTime} " + 
			"</if>" + 
			"<if test=\"bookInfo.source != null\">" + 
			"	and SOURCE = #{bookInfo.source} " + 
			"</if>" + 
			"<if test=\"bookInfo.sourceName != null\">" + 
			"	and SOURCE_NAME = #{bookInfo.sourceName} " + 
			"</if>" + 
			"<if test=\"bookInfo.bookId != null\">" + 
			"	and BOOK_ID = #{bookInfo.bookId} " + 
			"</if>" + 
			"<if test=\"bookInfo.newCatalog != null\">" + 
			"	and NEW_CATALOG = #{bookInfo.newCatalog} " + 
			"</if>" + 
			"</script>") 
	public List<BookInfoVo> bookInfoList(@Param("bookInfo") BookInfoVo bookInfo);

	/**
	 * 统计根据查询条件显示书本信息列表记录数
	 * @param bookInfo 查询条件对象
	 * @return 相应的书本信息列表记录数
	 */
	@Select("<script>" +
			"SELECT COUNT(1) " +
			"FROM CZS_BOOK_INFO " +
			"WHERE 1=1 " + 
			"<if test=\"bookInfo.id != null\">" + 
			"	and ID = #{bookInfo.id} " + 
			"</if>" + 
			"<if test=\"bookInfo.bookName != null\">" + 
			"	and BOOK_NAME = #{bookInfo.bookName} " + 
			"</if>" + 
			"<if test=\"bookInfo.author != null\">" + 
			"	and AUTHOR = #{bookInfo.author} " + 
			"</if>" + 
			"<if test=\"bookInfo.bookImg != null\">" + 
			"	and BOOK_IMG = #{bookInfo.bookImg} " + 
			"</if>" + 
			"<if test=\"bookInfo.type != null\">" + 
			"	and TYPE = #{bookInfo.type} " + 
			"</if>" + 
			"<if test=\"bookInfo.status != null\">" + 
			"	and STATUS = #{bookInfo.status} " + 
			"</if>" + 
			"<if test=\"bookInfo.updateTime != null\">" + 
			"	and UPDATE_TIME = #{bookInfo.updateTime} " + 
			"</if>" + 
			"<if test=\"bookInfo.wordNum != null\">" + 
			"	and WORD_NUM = #{bookInfo.wordNum} " + 
			"</if>" + 
			"<if test=\"bookInfo.remark != null\">" + 
			"	and REMARK = #{bookInfo.remark} " + 
			"</if>" + 
			"<if test=\"bookInfo.createTime != null\">" + 
			"	and CREATE_TIME = #{bookInfo.createTime} " + 
			"</if>" + 
			"<if test=\"bookInfo.source != null\">" + 
			"	and SOURCE = #{bookInfo.source} " + 
			"</if>" + 
			"<if test=\"bookInfo.sourceName != null\">" + 
			"	and SOURCE_NAME = #{bookInfo.sourceName} " + 
			"</if>" + 
			"<if test=\"bookInfo.bookId != null\">" + 
			"	and BOOK_ID = #{bookInfo.bookId} " + 
			"</if>" + 
			"<if test=\"bookInfo.newCatalog != null\">" + 
			"	and NEW_CATALOG = #{bookInfo.newCatalog} " + 
			"</if>" + 
			"</script>") 
	public int countBookInfo(@Param("bookInfo") BookInfoVo bookInfo);

	/**
	 * 根据数据实体保存书本信息
	 * @param bookInfo 保存数据实体对象
	 * @return 新增的书本信息记录数
	 */
	@Insert("INSERT INTO CZS_BOOK_INFO(BOOK_NAME,AUTHOR,BOOK_IMG,TYPE,STATUS,UPDATE_TIME,WORD_NUM,REMARK,CREATE_TIME,SOURCE,SOURCE_NAME,BOOK_ID,NEW_CATALOG) " + 
		" VALUES(#{bookInfo.bookName},#{bookInfo.author},#{bookInfo.bookImg},#{bookInfo.type},#{bookInfo.status},#{bookInfo.updateTime},#{bookInfo.wordNum},#{bookInfo.remark},#{bookInfo.createTime},#{bookInfo.source},#{bookInfo.sourceName},#{bookInfo.bookId},#{bookInfo.newCatalog}) ") 
	@Options(useGeneratedKeys = true, keyProperty = "id",keyColumn = "ID")
	public int saveBookInfo(@Param("bookInfo") BookInfo bookInfo);

	/**
	 * 根据数据实体修改书本信息
	 * @param bookInfo 修改数据实体对象
	 * @return 修改的书本信息记录数
	 */
	@Update("<script> " + 
			"UPDATE CZS_BOOK_INFO SET BOOK_NAME = #{bookInfo.bookName},AUTHOR = #{bookInfo.author},BOOK_IMG = #{bookInfo.bookImg},TYPE = #{bookInfo.type},STATUS = #{bookInfo.status},UPDATE_TIME = #{bookInfo.updateTime},WORD_NUM = #{bookInfo.wordNum},REMARK = #{bookInfo.remark},CREATE_TIME = #{bookInfo.createTime},SOURCE = #{bookInfo.source},SOURCE_NAME = #{bookInfo.sourceName},BOOK_ID = #{bookInfo.bookId},NEW_CATALOG = #{bookInfo.newCatalog}" + 
			" WHERE ID = #{bookInfo.id} " + 
			"</script>") 
	public int updateBookInfo(@Param("bookInfo") BookInfo bookInfo);

	/**
	 * 根据主键id删除书本信息
	 * @param ids 需删除的主键
	 * @return 删除的书本信息记录数
	 */
	@Delete("<script>" + 
			"delete from CZS_BOOK_INFO where ID in" + 
			"<foreach collection=\"ids\" index=\"index\" item=\"id\" open=\"(\" separator=\",\" close=\")\">" + 
			"   #{id}"+ 
			"</foreach>"+ 
			"</script>") 
	public int deleteBookInfo(@Param("ids") String[] ids);

}
