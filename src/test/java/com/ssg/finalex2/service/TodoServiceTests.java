package com.ssg.finalex2.service;

import com.ssg.finalex2.dto.PageRequestDTO;
import com.ssg.finalex2.dto.TodoDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.time.LocalDate;


@SpringBootTest
public class TodoServiceTests {

    @Autowired
    private TodoService todoService;


    @Test
    public void testRegister() {

        TodoDTO todoDTO = new TodoDTO();
        todoDTO.setTitle("Test Todo");
        todoDTO.setWriter("user00");
        todoDTO.setDueDate(LocalDate.of(2025, 12, 23));

        TodoDTO resultDTO = todoService.register(todoDTO);

        System.out.println(resultDTO);
    }

    @Test
    public void testRead() {

        Long mno = 100L;//현재 DB에 없는 번호

        TodoDTO todoDTO = todoService.read(mno);

        System.out.println(todoDTO);
    }

    @Test
    public void testRemove() {

        Long mno = 3L;

        todoService.remove(mno);
    }

    @Test
    public void testModify() {

        TodoDTO todoDTO = new TodoDTO();
        todoDTO.setMno(102L);
        todoDTO.setTitle("수정된 제목");
        todoDTO.setWriter("fix1");
        todoDTO.setDueDate(LocalDate.now());

        todoService.modify(todoDTO);
    }
    @Test
    public void testList() {

        //page 1,  size 10
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        Page<TodoDTO> result = todoService.getList(pageRequestDTO);

        System.out.println("PREV: " + result.previousPageable());
        System.out.println("NEXT: " + result.nextPageable());
        System.out.println("TOTAL: " + result.getTotalElements());

        result.getContent().forEach(todoDTO -> System.out.println(todoDTO));

    }


}
