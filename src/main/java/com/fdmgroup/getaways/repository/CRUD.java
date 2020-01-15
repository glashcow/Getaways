package com.fdmgroup.getaways.repository;

import java.util.List;

public interface CRUD<T> {

	void create(T t);
	T readOneById(long id);
	List<T> readAll();
	void update(T t);
	void delete(T t);

}
