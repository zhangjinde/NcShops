package com.ncshop.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.SimpleExpression;

public class BaseDao extends BaseHibernateDAO {

	/**
	 * 
	 * @param t
	 *            要查找表的的bean实例
	 * @param fields
	 *            是表实例类型的 成员变量名
	 * @return
	 */
	public <T> List<T> getEntitiestNotLazy(T t, String[] fields,
			SimpleExpression eq, int start, int max,boolean flag) {

		try {
			Criteria criteria = getSession().createCriteria(t.getClass());

			if (fields != null) {
				for (String string : fields) {
					criteria = criteria.setFetchMode(string, FetchMode.JOIN);
				}
			}
			// 判断是否分页
			if (flag) {

				criteria.setFirstResult(start);
				criteria.setMaxResults(max);
			}

			if (eq != null) {

				criteria.add(eq);
			}
			return criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
