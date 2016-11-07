package com.geng.service;

import java.util.List;

import com.geng.dao.ProdDao;
import com.geng.domain.Product;
import com.geng.factory.BasicFactory;

public interface ProdService {
     ProdDao dao=BasicFactory.getFactory().getInstence(ProdDao.class);
	//�����Ʒ�����ݿ�
	void addProd(Product prod);
	/**
	 * ��ѯ���з���
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

