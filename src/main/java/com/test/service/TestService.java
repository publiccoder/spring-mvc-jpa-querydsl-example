package com.test.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.test.entity.QTest;
import com.test.entity.QUser;
import com.test.entity.Test;

@Service
@Transactional
public class TestService extends ServiceTemplate<Test, Integer> {

	public Object test() {
		QTest t = QTest.test1;
		QUser u = QUser.user;
		List<Tuple> resultSet = this.queryFactory.select(t.id, u.username, t.updatedAt, u.createdAt).from(t).join(u)
				.on(t.id.eq(u.id)).where(u.id.gt(0)).orderBy(new OrderSpecifier<>(Order.DESC, t.createdAt)).offset(0)
				.limit(10).fetch();
		List<Map<String, ?>> list = new ArrayList<>();
		for (Tuple r : resultSet) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", r.get(t.id));
			map.put("username", r.get(u.username));
			map.put("updatedAt", r.get(t.updatedAt));
			map.put("createdAt", r.get(u.createdAt));
			list.add(map);
		}
		return list;
	}

}
