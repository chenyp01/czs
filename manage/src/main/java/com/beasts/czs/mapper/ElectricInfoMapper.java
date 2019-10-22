package com.beasts.czs.mapper;

import com.beasts.czs.common.BaseMapper;
import com.beasts.czs.model.po.ElectricInfo;
import com.beasts.czs.model.vo.ElectricInfoVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ElectricInfoMapper extends BaseMapper<ElectricInfo> {

    /**
     * 获取所有的电费用户信息
     * @return 所有的电费用户信息
     */
    @Select("<script>" +
            "SELECT * " +
            "FROM CZS_ELECTRIC_INFO " +
            "</script>")
    public List<ElectricInfoVo> getAllElectricInfo();

    /**
     * 根据主键id获取电费用户信息
     * @param id 主键id
     * @return 相应的电费用户信息
     */
    @Select("<script>" +
            "SELECT * " +
            "FROM CZS_ELECTRIC_INFO " +
            "WHERE 1=1 " +
            "<if test=\"id != null and id != ''\">" +
            "	AND ID = #{id}" +
            "</if>" +
            "</script>")
    public ElectricInfoVo getElectricInfo(@Param("id") String id);

    /**
     * 根据查询条件显示电费用户信息列表
     * @param electricInfo 查询条件对象
     * @return 相应的电费用户信息列表
     */
    @Select("<script>" +
            "SELECT * " +
            "FROM CZS_ELECTRIC_INFO " +
            "WHERE 1=1 " +
            "</script>")
    public List<ElectricInfoVo> electricInfoList(@Param("electricInfo") ElectricInfoVo electricInfo);

    /**
     * 统计根据查询条件显示电费用户信息列表记录数
     * @param electricInfo 查询条件对象
     * @return 相应的电费用户信息列表记录数
     */
    @Select("<script>" +
            "SELECT COUNT(1) " +
            "FROM CZS_ELECTRIC_INFO " +
            "WHERE 1=1 " +
            "</script>")
    public int countElectricInfo(@Param("electricInfo") ElectricInfoVo electricInfo);

    /**
     * 根据数据实体保存电费用户信息
     * @param electricInfo 保存数据实体对象
     * @return 新增的电费用户信息记录数
     */
    @Insert("INSERT INTO CZS_ELECTRIC_INFO() " +
            " VALUES() ")
    @Options(useGeneratedKeys = true, keyProperty = "id",keyColumn = "ID")
    public int saveElectricInfo(@Param("electricInfo") ElectricInfo electricInfo);

    /**
     * 根据数据实体修改电费用户信息
     * @param electricInfo 修改数据实体对象
     * @return 修改的电费用户信息记录数
     */
    @Update("<script> " +
            "UPDATE CZS_ELECTRIC_INFO SET " +
            " WHERE ID = #{electricInfo.id} " +
            "</script>")
    public int updateElectricInfo(@Param("electricInfo") ElectricInfo electricInfo);

    /**
     * 根据主键id删除电费用户信息
     * @param ids 需删除的主键
     * @return 删除的电费用户信息记录数
     */
    @Delete("<script>" +
            "delete from CZS_ELECTRIC_INFO where ID in" +
            "<foreach collection=\"ids\" index=\"index\" item=\"id\" open=\"(\" separator=\",\" close=\")\">" +
            "   #{id}"+
            "</foreach>"+
            "</script>")
    public int deleteElectricInfo(@Param("ids") String[] ids);

}
