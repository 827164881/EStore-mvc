package com.geng.dao;

import java.util.List;

import com.geng.domain.Product;

public interface ProdDao {
	//�����Ʒ�����ݿ�
	void addProd(Product prod);

	/**
	 * �������е���Ʒ�б�
	 * @return
	 */
	List<Product> findAllProd();

	/**
	 * ����id���ҳ�ָ����Ʒ
	 * @param parameter
	 * @return
	 */
	Product findProdById(String parameter);
	
	
}
