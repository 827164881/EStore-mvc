package com.geng.dao;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.eclipse.jdt.internal.compiler.ast.QualifiedTypeReference;

import com.geng.domain.Product;
import com.geng.utils.DaoUtils;

public class ProdDaoImpl implements ProdDao{

	@Override
	public void addProd(Product prod) {
		// TODO Auto-generated method stub
		String sql="insert into products values (SEQ_PRODUCTS.NEXTVAL,?,?,?,?,?,?)";
		try {
			QueryRunner runner=new QueryRunner(DaoUtils.getSource());
			runner.update(sql, prod.getName(),prod.getPrice(),prod.getCategory(),prod.getPnum(),prod.getImgurl(),prod.getDescription());
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Product> findAllProd() {
		// TODO Auto-generated method stub
		String sql="select * from products";
		QueryRunner runner=new QueryRunner(DaoUtils.getSource());
		try {
			return runner.query(sql, new BeanListHandler<Product>(Product.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public Product findProdById(String parameter) {
		// TODO Auto-generated method stub
		String sql="select * from products where id=?";
		QueryRunner runner=new QueryRunner(DaoUtils.getSource());
		try {
			return runner.query(sql, new BeanHandler<Product>(Product.class),parameter);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
