package com.beasts.czs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.beasts.czs.utils.ResObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.web.bind.annotation.RestController;
import com.github.pagehelper.PageInfo;
import javax.servlet.http.HttpServletRequest;
import org.springframework.util.StringUtils;

import com.beasts.czs.model.po.ElectricInfo;
import com.beasts.czs.model.vo.ElectricInfoVo;
import com.beasts.czs.service.ElectricInfoService;


@RestController
@RequestMapping("/electricInfo")
public class ElectricInfoController {
	Logger logger = Logger.getLogger(ElectricInfoController.class);

	@Autowired
	private ElectricInfoService electricInfoService;


	/**
	 * 获取所有的电费用户信息
	 * @return 电费用户信息列表
	 */
	@RequestMapping("/getAllElectricInfo")
	public ResObject getAllElectricInfo() {
		List<ElectricInfoVo> electricInfoList = this.electricInfoService.getAllElectricInfo();
		Map<String, Object> data = new HashMap<String, Object>();
		 data.put("result", electricInfoList);
		return ResObject.ok().put("data", data);
	}

	/**
	 * 根据查询条件查询电费用户信息
	 * @param electricInfo 查询条件实体
	 * @param page 分页实体
	 * @return electricInfo列表
	 */
	@RequestMapping("/electricInfoList")
	public ResObject electricInfoList(ElectricInfoVo electricInfo,PageInfo page){
		//设置分页信息
		Page result = PageHelper.startPage(page.getPageNum(),page.getPageSize());
		//查询结果集合
		this.electricInfoService.electricInfoList(electricInfo);
		//设置分页信息
		page = new PageInfo<>(result);
		page.setTotal(result.getTotal());
		//返回信息
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("page", page);
		data.put("result", result);
		return ResObject.ok().put("data", data);
	}

	/**
	 * 根据主键获取电费用户信息
	 * @param id  主键id
	 * @return 电费用户信息
	 */
	@RequestMapping("/ElectricInfoInfo/{id}")
	public ResObject ElectricInfoInfo(@PathVariable("id") String id) {
		ElectricInfoVo electricInfoObj = this.electricInfoService.getElectricInfo(id);
		return ResObject.ok().put("data",electricInfoObj);
	}

	/**
	 * 根据数据实体保存数据
	 * @param electricInfo 数据实体
	 * @return 保存是否成功 "0"失败，"1"成功
	 */
	@RequestMapping("/saveElectricInfo")
	public ResObject saveElectricInfo(HttpServletRequest request,ElectricInfo electricInfo) {
		if(electricInfo == null){
			return ResObject.error("对象为空");
		}

		this.electricInfoService.saveElectricInfo(electricInfo);
		return ResObject.ok();
	}

	/**
	 * 根据主键id删除数据
	 * @param ids 主键id
	 * @return 删除是否成功 "0"失败，"1"成功
	 */
	@RequestMapping("/delElectricInfo")
	public ResObject delElectricInfo(String ids) {
		if(StringUtils.isEmpty(ids)){
			return ResObject.error("请选择需要删除的数据");
		}
		this.electricInfoService.deleteElectricInfo(ids.split(","));
		return ResObject.ok();
	}

}
