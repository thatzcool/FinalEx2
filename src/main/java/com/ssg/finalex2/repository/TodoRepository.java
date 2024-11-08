package com.ssg.finalex2.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.ssg.finalex2.dto.TodoDTO;
import com.ssg.finalex2.entity.TodoEntity;
import com.ssg.finalex2.repository.search.TodoSearch;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<TodoEntity, Long> , TodoSearch {

    @Query("select t from TodoEntity t ")
    Page<TodoEntity> listAll(Pageable pageable);

//    @Query(
//            value = "select * from tbl_todos t " ,
//            countQuery = " select count(*) from tbl_todos ",
//            nativeQuery = true
//    )
//    Page<TodoEntity> listAll(Pageable pageable);

    @Query("select t from TodoEntity t where t.mno = :mno")
    Optional<TodoDTO> getDTO(@Param("mno") Long mno);

}