package com.beasts.czs.dao;

import java.util.List;
import org.apache.ibatis.annotations.*;

import org.springframework.stereotype.Repository;
import com.beasts.czs.model.po.BookCatalogInfo;
import com.beasts.czs.model.vo.BookCatalogInfoVo;

@Repository
public interface BookCatalogInfoDao {
	/**
	 * 获取所有的书本信息
	 * @return 所有的书本信息
	 */
	@Select("<script>" +
			"SELECT " +
			 "	ID AS id, "+
			 "	CATALOG_NAME AS catalogName, "+
			 "	DETAIL_URL AS detailUrl, "+
			 "	ORDER_NO AS orderNo, "+
			 "	SOURCE AS source, "+
			 "	SOURCE_NAME AS sourceName, "+
			 "	CREATE_TIME AS createTime, "+
			 "	BOOK_ID AS bookId "+
			"FROM CZS_BOOK_CATALOG_INFO " +
			"</script>") 
	public List<BookCatalogInfoVo> getAllBookCatalogInfo();

	/**
	 * 根据主键id获取书本信息
	 * @param id 主键id
	 * @return 相应的书本信息
	 */
	@Select("<script>" +
			"SELECT " +
			 "	ID AS id, "+
			 "	CATALOG_NAME AS catalogName, "+
			 "	DETAIL_URL AS detailUrl, "+
			 "	ORDER_NO AS orderNo, "+
			 "	SOURCE AS source, "+
			 "	SOURCE_NAME AS sourceName, "+
			 "	CREATE_TIME AS createTime, "+
			 "	BOOK_ID AS bookId "+
			"FROM CZS_BOOK_CATALOG_INFO " +
			"WHERE 1=1 " + 
			"<if test=\"id != null and id != ''\">" +
			"	AND ID = #{id}" +
			"</if>" + 
			"</script>") 
	public BookCatalogInfoVo getBookCatalogInfo(@Param("id") String id);

	/**
	 * 根据查询条件显示书本信息列表
	 * @param bookCatalogInfo 查询条件对象
	 * @return 相应的书本信息列表
	 */
	@Select("<script>" +
			"SELECT " +
			 "	ID AS id, "+
			 "	CATALOG_NAME AS catalogName, "+
			 "	DETAIL_URL AS detailUrl, "+
			 "	ORDER_NO AS orderNo, "+
			 "	SOURCE AS source, "+
			 "	SOURCE_NAME AS sourceName, "+
			 "	CREATE_TIME AS createTime, "+
			 "	BOOK_ID AS bookId "+
			"FROM CZS_BOOK_CATALOG_INFO " +
			"WHERE 1=1 " + 
			"<if test=\"bookCatalogInfo.id != null\">" + 
			"	and ID = #{bookCatalogInfo.id} " + 
			"</if>" + 
			"<if test=\"bookCatalogInfo.catalogName != null\">" + 
			"	and CATALOG_NAME = #{bookCatalogInfo.catalogName} " + 
			"</if>" + 
			"<if test=\"bookCatalogInfo.detailUrl != null\">" + 
			"	and DETAIL_URL = #{bookCatalogInfo.detailUrl} " + 
			"</if>" + 
			"<if test=\"bookCatalogInfo.orderNo != null\">" + 
			"	and ORDER_NO = #{bookCatalogInfo.orderNo} " + 
			"</if>" + 
			"<if test=\"bookCatalogInfo.source != null\">" + 
			"	and SOURCE = #{bookCatalogInfo.source} " + 
			"</if>" + 
			"<if test=\"bookCatalogInfo.sourceName != null\">" + 
			"	and SOURCE_NAME = #{bookCatalogInfo.sourceName} " + 
			"</if>" + 
			"<if test=\"bookCatalogInfo.createTime != null\">" + 
			"	and CREATE_TIME = #{bookCatalogInfo.createTime} " + 
			"</if>" + 
			"<if test=\"bookCatalogInfo.bookId != null\">" + 
			"	and BOOK_ID = #{bookCatalogInfo.bookId} " + 
			"</if>" + 
			"</script>") 
	public List<BookCatalogInfoVo> bookCatalogInfoList(@Param("bookCatalogInfo") BookCatalogInfoVo bookCatalogInfo);

	/**
	 * 统计根据查询条件显示书本信息列表记录数
	 * @param bookCatalogInfo 查询条件对象
	 * @return 相应的书本信息列表记录数
	 */
	@Select("<script>" +
			"SELECT COUNT(1) " +
			"FROM CZS_BOOK_CATALOG_INFO " +
			"WHERE 1=1 " + 
			"<if test=\"bookCatalogInfo.id != null\">" + 
			"	and ID = #{bookCatalogInfo.id} " + 
			"</if>" + 
			"<if test=\"bookCatalogInfo.catalogName != null\">" + 
			"	and CATALOG_NAME = #{bookCatalogInfo.catalogName} " + 
			"</if>" + 
			"<if test=\"bookCatalogInfo.detailUrl != null\">" + 
			"	and DETAIL_URL = #{bookCatalogInfo.detailUrl} " + 
			"</if>" + 
			"<if test=\"bookCatalogInfo.orderNo != null\">" + 
			"	and ORDER_NO = #{bookCatalogInfo.orderNo} " + 
			"</if>" + 
			"<if test=\"bookCatalogInfo.source != null\">" + 
			"	and SOURCE = #{bookCatalogInfo.source} " + 
			"</if>" + 
			"<if test=\"bookCatalogInfo.sourceName != null\">" + 
			"	and SOURCE_NAME = #{bookCatalogInfo.sourceName} " + 
			"</if>" + 
			"<if test=\"bookCatalogInfo.createTime != null\">" + 
			"	and CREATE_TIME = #{bookCatalogInfo.createTime} " + 
			"</if>" + 
			"<if test=\"bookCatalogInfo.bookId != null\">" + 
			"	and BOOK_ID = #{bookCatalogInfo.bookId} " + 
			"</if>" + 
			"</script>") 
	public int countBookCatalogInfo(@Param("bookCatalogInfo") BookCatalogInfoVo bookCatalogInfo);

	/**
	 * 根据数据实体保存书本信息
	 * @param bookCatalogInfo 保存数据实体对象
	 * @return 新增的书本信息记录数
	 */
	@Insert("INSERT INTO CZS_BOOK_CATALOG_INFO(CATALOG_NAME,DETAIL_URL,ORDER_NO,SOURCE,SOURCE_NAME,CREATE_TIME,BOOK_ID) " + 
		" VALUES(#{bookCatalogInfo.catalogName},#{bookCatalogInfo.detailUrl},#{bookCatalogInfo.orderNo},#{bookCatalogInfo.source},#{bookCatalogInfo.sourceName},#{bookCatalogInfo.createTime},#{bookCatalogInfo.bookId}) ") 
	@Options(useGeneratedKeys = true, keyProperty = "id",keyColumn = "ID")
	public int saveBookCatalogInfo(@Param("bookCatalogInfo") BookCatalogInfo bookCatalogInfo);

	/**
	 * 根据数据实体修改书本信息
	 * @param bookCatalogInfo 修改数据实体对象
	 * @return 修改的书本信息记录数
	 */
	@Update("<script> " + 
			"UPDATE CZS_BOOK_CATALOG_INFO SET CATALOG_NAME = #{bookCatalogInfo.catalogName},DETAIL_URL = #{bookCatalogInfo.detailUrl},ORDER_NO = #{bookCatalogInfo.orderNo},SOURCE = #{bookCatalogInfo.source},SOURCE_NAME = #{bookCatalogInfo.sourceName},CREATE_TIME = #{bookCatalogInfo.createTime},BOOK_ID = #{bookCatalogInfo.bookId}" + 
			" WHERE ID = #{bookCatalogInfo.id} " + 
			"</script>") 
	public int updateBookCatalogInfo(@Param("bookCatalogInfo") BookCatalogInfo bookCatalogInfo);

	/**
	 * 根据主键id删除书本信息
	 * @param id 需删除的主键
	 * @return 删除的书本信息记录数
	 */
	@Delete("<script>" + 
			"delete from CZS_BOOK_CATALOG_INFO where ID in" + 
			"<foreach collection=\"ids\" index=\"index\" item=\"id\" open=\"(\" separator=\",\" close=\")\">" + 
			"   #{id}"+ 
			"</foreach>"+ 
			"</script>") 
	public int deleteBookCatalogInfo(@Param("ids") String[] ids);

}
