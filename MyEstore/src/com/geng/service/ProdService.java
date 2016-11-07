package com.geng.service;

import java.util.List;

import com.geng.dao.ProdDao;
import com.geng.domain.Product;
import com.geng.factory.BasicFactory;

public interface ProdService {
     ProdDao dao=BasicFactory.getFactory().getInstence(ProdDao.class);
	//添加商品到数据库
	void addProd(Product prod);
	/**
	 * 查询所有方法
	 * @return
	 */
	List<Product> findAllProd();
	/**
	 * 根据id查找出指定商品
	 * @param parameter
	 * @return
	 */
	Product findProdById(String parameter);
	
	
}

