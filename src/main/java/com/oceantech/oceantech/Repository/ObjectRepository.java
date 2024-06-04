package com.oceantech.oceantech.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.oceantech.oceantech.Model.Object;

public interface ObjectRepository extends JpaRepository<Object, Long> {

    @Query(" SELECT o FROM Object o WHERE MONTH(o.recycleDate) = ?2 AND o.user.name = ?1 ")
    Page<Object> findByUserNameAndMes(String user, Integer mes, Pageable pageable);

    @Query(" SELECT o FROM Object o WHERE MONTH(o.recycleDate) = ?1 ")
    Page<Object> findByMes(Integer mes, Pageable pageable);

    Page<Object> findByUserName(String user, Pageable pageable);
}
