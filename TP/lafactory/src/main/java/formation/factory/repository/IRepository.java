package formation.factory.repository;

import java.util.List;

public interface IRepository<T,PK> {
	List<T> findAll();
	T findById(PK id);
	void create(T obj);
	T update(T obj);
	void deleteById(PK id);
}
