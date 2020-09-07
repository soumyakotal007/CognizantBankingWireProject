package com.wire.payment.initiation.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.wire.payment.entity.TUserDetail;

public interface UserRepository extends CrudRepository<TUserDetail, Long> {
	@Query("SELECT u FROM TUserDetail u WHERE u.userName = :username")
	public TUserDetail getUserByUsername(@Param("username") String username);
}
