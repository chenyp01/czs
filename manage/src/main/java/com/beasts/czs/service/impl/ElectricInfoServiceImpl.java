package com.beasts.czs.service.impl;

import java.util.List;

import com.beasts.czs.dao.ElectricInfoDao;
import com.beasts.czs.mapper.ElectricInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beasts.czs.service.ElectricInfoService;
import com.beasts.czs.model.po.ElectricInfo;
import com.beasts.czs.model.vo.ElectricInfoVo;

@Service
public class ElectricInfoServiceImpl implements ElectricInfoService {

	@Autowired
	private ElectricInfoDao electricInfoMapper;

	/**
	 * 获取所有的电费用户信息
	 * @return 所有的电费用户信息
	 */
	public List<ElectricInfoVo> getAllElectricInfo(){
		List<ElectricInfoVo> electricInfoList = this.electricInfoMapper.getAllElectricInfo();
		return electricInfoList;
	}

	/**
	 * 根据主键获取电费用户信息
	 * @param id 主键id
	 * @return 电费用户信息
	 */
	public ElectricInfoVo getElectricInfo(String id) {
		ElectricInfoVo electricInfoObj = this.electricInfoMapper.getElectricInfo(id);
		return electricInfoObj;
	}

	/**
	 * 根据数据实体保存数据
	 * @param electricInfo 数据实体
	 * @return 新增记录数
	 */
	public int saveElectricInfo(ElectricInfo electricInfo){
		if(electricInfo == null) {
			return 0;
		}
		int saveCount = 0;
		if(electricInfo.getId() == null || "".equals(electricInfo.getId())) {
			saveCount = this.electricInfoMapper.saveElectricInfo(electricInfo);
		} else {
			saveCount = this.electricInfoMapper.updateElectricInfo(electricInfo);
		}
		return saveCount;
	}

	/**
	 * 根据查询条件查询电费用户信息
	 * @param electricInfo 查询条件实体
	 * @return electricInfo列表
	 */
	public List<ElectricInfoVo> electricInfoList(ElectricInfoVo electricInfo){
		List<ElectricInfoVo> electricInfoList = this.electricInfoMapper.electricInfoList(electricInfo);
		return electricInfoList;
	}

	/**
	 * 统计记录数
	 * @param electricInfo 查询条件实体
	 * @return 记录数
	 */
	public int countElectricInfo(ElectricInfoVo electricInfo){
		int count = this.electricInfoMapper.countElectricInfo(electricInfo);
		return count;
	}

	/**
	 * 根据主键id删除数据
	 * @param ids 主键id
	 * @return 删除的记录数
	 */
	public int deleteElectricInfo(String[] ids ){
		int count = this.electricInfoMapper.deleteElectricInfo(ids);
		return count;
	}

}
