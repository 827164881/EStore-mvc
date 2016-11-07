package com.geng.service;

import java.util.List;

import com.geng.domain.Product;

public class ProdServiceImpl implements ProdService {

	@Override
	public void addProd(Product prod) {
		// TODO Auto-generated method stub
		dao.addProd(prod);
	}

	@Override
	public List<Product> findAllProd() {
		// TODO Auto-generated method stub
		return dao.findAllProd();
	}

	@Override
	public Product findProdById(String parameter) {
		// TODO Auto-generated method stub
		return dao.findProdById(parameter);
	}

}
