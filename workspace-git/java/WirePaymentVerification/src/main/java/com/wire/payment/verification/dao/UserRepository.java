package com.wire.payment.verification.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.wire.payment.entity.TUserDetail;

public interface UserRepository extends CrudRepository<TUserDetail, Integer> {
	@Query("SELECT u FROM TUserDetail u WHERE u.userName = :username")
	public TUserDetail getUserByUsername(@Param("username") String username);
}
