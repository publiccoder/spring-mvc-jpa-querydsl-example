package com.test.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.test.entity.QUser;
import com.test.entity.User;
import com.test.repository.UserRepository;

@Service
@Transactional
public class UserService extends ServiceTemplate<User, Integer> {

	public List<User> crud(Integer page) {
		User entity = new User();

		entity.setUsername("18647958991");
		entity.setPassword("123456");
		entity.setCreatedAt(Calendar.getInstance().getTime());
		entity = this.save(entity);

		entity.setPassword("654321" + System.currentTimeMillis());
		entity = this.save(entity);

		QUser q = QUser.user;
		UserRepository repository = (UserRepository) this.repository;
		Page<User> users = repository.findAll(q.id.gt(entity.getId() - 100),
				new QPageRequest(page, 10, new OrderSpecifier<>(Order.DESC, q.id)));

//		this.delete(entity);

		return users.getContent();
	}

}
