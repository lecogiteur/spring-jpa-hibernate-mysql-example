package com.techprimers.jpa.springjpahibernateexample;

import com.techprimers.jpa.springjpahibernateexample.model.Users;
import com.techprimers.jpa.springjpahibernateexample.repository.UsersRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringJpaHibernateExampleApplicationTests {

	private final UUID uuidUserWhichNotWork_A = UUID.fromString("cbfaed14-bb65-11e7-abc4-cec278b6b50a");
	private final UUID uuidUserWhichWork_B = UUID.fromString("44af28c2-bbdb-11e7-abc4-cec278b6b50a");

    @Autowired
	UsersRepository usersRepository;

    @PersistenceContext
	EntityManager em;

	/**
	 * Not work because "cbfaed14-bb65-11e7-abc4-cec278b6b50a" has no team  and we set on entity User the fetch mode to EAGER
	 * <p>
	 * Caused by: java.lang.IllegalArgumentException: Can not set java.lang.Integer field com.techprimers.jpa.springjpahibernateexample.model.Users.id to java.util.UUID
	 * at sun.reflect.UnsafeFieldAccessorImpl.throwSetIllegalArgumentException(UnsafeFieldAccessorImpl.java:167)
	 * at sun.reflect.UnsafeFieldAccessorImpl.throwSetIllegalArgumentException(UnsafeFieldAccessorImpl.java:171)
	 * at sun.reflect.UnsafeFieldAccessorImpl.ensureObj(UnsafeFieldAccessorImpl.java:58)
	 * at sun.reflect.UnsafeObjectFieldAccessorImpl.get(UnsafeObjectFieldAccessorImpl.java:36)
	 * at java.lang.reflect.Field.get(Field.java:393)
	 * at org.hibernate.property.access.spi.GetterFieldImpl.get(GetterFieldImpl.java:39)</p>
	 */
	@Test
	public void not_work_1() {
		usersRepository.findOne(uuidUserWhichNotWork_A);
	}

	/**
	 * Work because "44af28c2-bbdb-11e7-abc4-cec278b6b50a" has a team and we set on entity User the fetch mode to EAGER
	 */
	@Test
	public void work_1() {
		usersRepository.findOne(uuidUserWhichWork_B);
	}

	/**
	 * Not work because "cbfaed14-bb65-11e7-abc4-cec278b6b50a" has no team  and we set on entity User the fetch mode to EAGER
	 * <p>
	 * Caused by: java.lang.IllegalArgumentException: Can not set java.lang.Integer field com.techprimers.jpa.springjpahibernateexample.model.Users.id to java.util.UUID
	 * at sun.reflect.UnsafeFieldAccessorImpl.throwSetIllegalArgumentException(UnsafeFieldAccessorImpl.java:167)
	 * at sun.reflect.UnsafeFieldAccessorImpl.throwSetIllegalArgumentException(UnsafeFieldAccessorImpl.java:171)
	 * at sun.reflect.UnsafeFieldAccessorImpl.ensureObj(UnsafeFieldAccessorImpl.java:58)
	 * at sun.reflect.UnsafeObjectFieldAccessorImpl.get(UnsafeObjectFieldAccessorImpl.java:36)
	 * at java.lang.reflect.Field.get(Field.java:393)
	 * at org.hibernate.property.access.spi.GetterFieldImpl.get(GetterFieldImpl.java:39)</p>
	 */
	@Test
	public void not_work_2() {
		em.find(Users.class, uuidUserWhichNotWork_A);
	}

	/**
	 * Work because "44af28c2-bbdb-11e7-abc4-cec278b6b50a" has a team and we set on entity User the fetch mode to EAGER
	 */
	@Test
	public void work_2() {
		em.find(Users.class, uuidUserWhichWork_B);
	}

	/**
	 * Work ! If we use a query builder
	 */
	@Test
	public void work_3_with_query_builder() {
		final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		final CriteriaQuery<Users> criteriaQuery = criteriaBuilder.createQuery(Users.class);
		final Root<Users> criteriaFrom = criteriaQuery.from(Users.class);
		final Predicate condition = criteriaBuilder.equal(criteriaFrom.get("uuid"), uuidUserWhichNotWork_A);
		criteriaQuery.where(condition);
		final TypedQuery<Users> query = em.createQuery(criteriaQuery);
		final Users result = query.getSingleResult();
	}

	/**
	 * Work because "44af28c2-bbdb-11e7-abc4-cec278b6b50a" has a team and we set on entity User the fetch mode to EAGER
	 */
	@Test
	public void work_3() {
		final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		final CriteriaQuery<Users> criteriaQuery = criteriaBuilder.createQuery(Users.class);
		final Root<Users> criteriaFrom = criteriaQuery.from(Users.class);
		final Predicate condition = criteriaBuilder.equal(criteriaFrom.get("uuid"), uuidUserWhichWork_B);
		criteriaQuery.where(condition);
		final TypedQuery<Users> query = em.createQuery(criteriaQuery);
		final Users result = query.getSingleResult();
	}

	/**
	 * Work ! If we use a query builder
	 */
	@Test
	public void work_4_with_query_builder() {
		final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		final CriteriaQuery<Users> criteriaQuery = criteriaBuilder.createQuery(Users.class);
		final Root<Users> criteriaFrom = criteriaQuery.from(Users.class);
		criteriaFrom.fetch("teams", JoinType.LEFT);
		final Predicate condition = criteriaBuilder.equal(criteriaFrom.get("uuid"), uuidUserWhichNotWork_A);
		criteriaQuery.where(condition);
		final TypedQuery<Users> query = em.createQuery(criteriaQuery);
		final Users result = query.getSingleResult();
	}

	/**
	 * Work because "44af28c2-bbdb-11e7-abc4-cec278b6b50a" has a team and we set on entity User the fetch mode to EAGER
	 */
	@Test
	public void work_4() {
		final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		final CriteriaQuery<Users> criteriaQuery = criteriaBuilder.createQuery(Users.class);
		final Root<Users> criteriaFrom = criteriaQuery.from(Users.class);
		criteriaFrom.fetch("teams", JoinType.LEFT);
		final Predicate condition = criteriaBuilder.equal(criteriaFrom.get("uuid"), uuidUserWhichWork_B);
		criteriaQuery.where(condition);
		final TypedQuery<Users> query = em.createQuery(criteriaQuery);
		final Users result = query.getSingleResult();
	}
}
