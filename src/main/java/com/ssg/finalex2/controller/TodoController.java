package com.ssg.finalex2.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.ssg.finalex2.dto.PageRequestDTO;
import com.ssg.finalex2.dto.TodoDTO;
import com.ssg.finalex2.service.TodoService;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/todos")
@Log4j2
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;


    @PostMapping("")
    public ResponseEntity<TodoDTO> register(@RequestBody @Validated TodoDTO todoDTO) {

        log.info("register............");
        log.info(todoDTO);

        //자동 생성은 번호가 null인 경우에 insert
        todoDTO.setMno(null);

        return ResponseEntity.ok(todoService.register(todoDTO));
    }

    @GetMapping("/{mno}")
    public ResponseEntity<TodoDTO> read( @PathVariable("mno") Long mno) {

        log.info("read............");
        log.info(mno);

        return ResponseEntity.ok(todoService.read(mno));
    }

    @PutMapping("/{mno}")
    public ResponseEntity<TodoDTO> modify(@PathVariable("mno") Long mno, @RequestBody TodoDTO todoDTO) {

        log.info("modify............");
        log.info(mno);
        log.info(todoDTO);
        //DTO에 번호를 저장한다.
        todoDTO.setMno(mno);

        TodoDTO modifiedTodoDTO = todoService.modify(todoDTO);

        return ResponseEntity.ok(modifiedTodoDTO);
    }

    @DeleteMapping("/{mno}")
    public ResponseEntity<Map<String, String>> remove(@PathVariable("mno") Long mno) {

        log.info("remove............");
        log.info(mno);

        todoService.remove(mno);

        //void타입이기 때문에 다른 결과들처럼 JSON결과를 만들어서 반환한다.
        Map<String, String> result = Map.of("result", "success");

        return ResponseEntity.ok(result);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<TodoDTO>> list(@Validated PageRequestDTO pageRequestDTO) {

        log.info("list............");

        return ResponseEntity.ok(todoService.getList(pageRequestDTO));
    }




}
