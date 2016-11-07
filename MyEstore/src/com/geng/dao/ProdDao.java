package com.geng.dao;

import java.util.List;

import com.geng.domain.Product;

public interface ProdDao {
	//添加商品到数据库
	void addProd(Product prod);

	/**
	 * 查找所有的商品列表
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
