package com.ssg.finalex2.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.ssg.finalex2.dto.TodoDTO;
import com.ssg.finalex2.entity.TodoEntity;

public interface TodoSearch {

    Page<TodoEntity> search1(Pageable pageable);

    Page<TodoDTO> searchDTO(Pageable pageable);
}
