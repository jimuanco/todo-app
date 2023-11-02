package myproject.todoapp.controller;

import lombok.RequiredArgsConstructor;
import myproject.todoapp.dto.ResponseDTO;
import myproject.todoapp.dto.TodoDTO;
import myproject.todoapp.model.TodoEntity;
import myproject.todoapp.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto) {

        try {
            String temporaryUserId = "temporary-user";

            TodoEntity todoEntity = TodoDTO.toEntity(dto);

            todoEntity.assignLoggedInUserID(temporaryUserId); // TODO 인증, 인가 기능 추가 후 수정

            List<TodoEntity> todoEntities = todoService.create(todoEntity);

            List<TodoDTO> dtos = todoEntities.stream().map(TodoDTO::new).collect(Collectors.toList());

            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            String error = e.getMessage();
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping
    public ResponseEntity<?> retrieveTodoList() {
        String temporaryUserId = "temporary-user"; // TODO 인증, 인가 기능 추가 후 수정

        List<TodoEntity> todoEntities = todoService.retrieve(temporaryUserId);

        List<TodoDTO> dtos = todoEntities.stream().map(TodoDTO::new).collect(Collectors.toList());

        ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

        return ResponseEntity.ok().body(response);
    }

    @PutMapping
    public ResponseEntity<?> updateTodo(@RequestBody TodoDTO dto) {
        String temporaryUserId = "temporary-user"; // TODO 인증, 인가 기능 추가 후 수정

        TodoEntity todoEntity = TodoDTO.toEntity(dto);

        todoEntity.assignLoggedInUserID(temporaryUserId);

        List<TodoEntity> todoEntities = todoService.update(todoEntity);

        List<TodoDTO> dtos = todoEntities.stream().map(TodoDTO::new).collect(Collectors.toList());

        ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

        return ResponseEntity.ok().body(response);

    }

    @DeleteMapping
    public ResponseEntity<?> deleteTodo(@RequestBody TodoDTO dto) {
        try {
            String temporaryUserId = "temporary-user"; // TODO 인증, 인가 기능 추가 후 수정

            TodoEntity todoEntity = TodoDTO.toEntity(dto);
            todoEntity.assignLoggedInUserID(temporaryUserId);

            List<TodoEntity> todoEntities = todoService.delete(todoEntity);

            List<TodoDTO> dtos = todoEntities.stream().map(TodoDTO::new).collect(Collectors.toList());
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            String error = e.getMessage();
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
            return ResponseEntity.ok().body(response);
        }
    }
}
