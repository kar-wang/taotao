package com.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;

	@Override
	public TbItem getItemById(long itemId) {
		return itemMapper.selectByPrimaryKey(itemId);
	}

	@Override
	public EasyUIDataGridResult getItemList(int page, int rows) {
		// 分页处理
		PageHelper.startPage(page, rows);

		// 执行查询
		TbItemExample example = new TbItemExample();
		List<TbItem> list = itemMapper.selectByExample(example);

		// 取total
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		long total = pageInfo.getTotal();

		// 创建返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult(total, list);

		return result;
	}

}
