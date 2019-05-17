package com.test.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.test.entity.QTest;
import com.test.entity.QUser;
import com.test.entity.Test;

@Service
@Transactional
public class TestService extends ServiceTemplate<Test, Integer> {

	public List<Map<String, ?>> test() {
		Test entity = new Test();
		entity.setTest(System.currentTimeMillis() + "");
		this.save(entity);

		QTest t = QTest.test1;
		QUser u = QUser.user;
		return this.queryFactory.select(t.id, u.username, t.updatedAt, u.createdAt).from(t).join(u).on(t.id.eq(u.id)).where(u.id.gt(0))
				.orderBy(new OrderSpecifier<>(Order.DESC, t.createdAt)).offset(0).limit(10).fetch().stream().map(r -> {
					Map<String, Object> map = new HashMap<>();
					map.put("id", r.get(t.id));
					map.put("username", r.get(u.username));
					map.put("updatedAt", r.get(t.updatedAt));
					map.put("createdAt", r.get(u.createdAt));
					return map;
				}).collect(Collectors.toList());
	}

}
