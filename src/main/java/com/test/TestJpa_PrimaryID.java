package com.test;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.bean.CJBean;
import com.bean.CJID;
import com.util.JpaUtil;

public class TestJpa_PrimaryID {
	public static void main(String[] args) {
//		TestJpa_PrimaryID.save();
		TestJpa_PrimaryID.query();
	}

	private static void save() {
		EntityManager manager = null;
		EntityTransaction tx = null;
		try {
			manager = JpaUtil.getManager();
			tx = manager.getTransaction();
			tx.begin();
			
			CJID cjid = new CJID();
			cjid.setStu_name("詹姆斯");
			cjid.setStu_subject("数学");
			
			CJBean cjBean = new CJBean();
			cjBean.setCjid(cjid);
			cjBean.setStu_score(new BigDecimal("88"));
			
			manager.persist(cjBean);
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			JpaUtil.closeManager();
			System.exit(0);
		}
	}

	private static void query() {
		EntityManager manager = null;
		EntityTransaction tx = null;
		try {
			manager = JpaUtil.getManager();
			tx = manager.getTransaction();
			tx.begin();
			
			CJID cjid = new CJID();
			cjid.setStu_name("詹姆斯");
			cjid.setStu_subject("数学");
			
			CJBean cjBean = manager.find(CJBean.class, cjid);
			
			System.out.println("stu_name = " + cjBean.getCjid().getStu_name());
			System.out.println("stu_subject = " + cjBean.getCjid().getStu_subject());
			System.out.println("stu_score = " + cjBean.getStu_score());
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			JpaUtil.closeManager();
			System.exit(0);
		}
	}

}
