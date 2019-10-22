package com.beasts.czs.service;

import java.util.List;
import com.beasts.czs.model.po.ElectricInfo;
import com.beasts.czs.model.vo.ElectricInfoVo;

public interface ElectricInfoService {
	/**
	 * 获取所有的电费用户信息
	 * @return 所有的电费用户信息
	 */
	public List<ElectricInfoVo> getAllElectricInfo();

	/**
	 * 根据主键获取电费用户信息
	 * @param id 主键id
	 * @return 电费用户信息
	 */
	public ElectricInfoVo getElectricInfo(String id);

	/**
	 * 根据数据实体保存数据
	 * @param electricInfo 数据实体
	 * @return 新增记录数
	 */
	public int saveElectricInfo(ElectricInfo electricInfo);

	/**
	 * 根据查询条件查询电费用户信息
	 * @param electricInfo 查询条件实体
	 * @return electricInfo列表
	 */
	public List<ElectricInfoVo> electricInfoList(ElectricInfoVo electricInfo);

	/**
	 * 统计记录数
	 * @param electricInfo 查询条件实体
	 * @return 记录数
	 */
	public int countElectricInfo(ElectricInfoVo electricInfo);

	/**
	 * 根据主键id删除数据
	 * @param id 主键id
	 * @return 删除的记录数
	 */
	public int deleteElectricInfo(String[] ids);

}
