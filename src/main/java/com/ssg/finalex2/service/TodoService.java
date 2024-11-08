package com.ssg.finalex2.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ssg.finalex2.dto.PageRequestDTO;
import com.ssg.finalex2.dto.TodoDTO;
import com.ssg.finalex2.entity.TodoEntity;
import com.ssg.finalex2.exception.EntityNotFoundException;
import com.ssg.finalex2.repository.TodoRepository;

import java.util.Optional;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoDTO register(TodoDTO todoDTO) {

        //DTO를 엔티티 객체로 변환한다.
        TodoEntity todoEntity = todoDTO.toEntity();

        //todoRepository를 이용해서 저장한다.
        todoRepository.save(todoEntity);

        //DTO에 저장된 번호를 저장해서 반환한다.

        return new TodoDTO(todoEntity);

    }

//    public TodoDTO read(Long mno) {
//
//       java.util.Optional<TodoDTO> result = todoRepository.getDTO(mno);
//
//        TodoDTO todoDTO = result.orElseThrow();
//
//        return todoDTO;
//    }

    public TodoDTO read(Long mno) {

        Optional<TodoDTO> result = todoRepository.getDTO(mno);

        TodoDTO todoDTO =
                result.orElseThrow(
                        () -> new EntityNotFoundException("Todo " + mno + " not found")
                );

        return todoDTO;
    }

    public void remove(Long mno) {

        Optional<TodoEntity> result = todoRepository.findById(mno);

        TodoEntity todoEntity = result.orElseThrow(
                () -> new EntityNotFoundException("Todo " + mno + " not found"));

        todoRepository.delete(todoEntity);

    }

    public TodoDTO modify(TodoDTO todoDTO) {

        Optional<TodoEntity> result = todoRepository.findById(todoDTO.getMno());

        TodoEntity todoEntity = result.orElseThrow(
                () -> new EntityNotFoundException("Todo " + todoDTO.getMno() + " not found"));

        todoEntity.changeTitle(todoDTO.getTitle());
        todoEntity.changeWriter(todoDTO.getWriter());
        todoEntity.changeDueDate(todoDTO.getDueDate());

        return new TodoDTO(todoEntity);
    }

    public Page<TodoDTO> getList(PageRequestDTO pageRequestDTO) {

        Sort sort = Sort.by("mno").descending();
        Pageable pageable = pageRequestDTO.getPageable(sort);

        return todoRepository.searchDTO(pageable);
    }




}