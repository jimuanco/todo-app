package myproject.todoapp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myproject.todoapp.model.TodoEntity;
import myproject.todoapp.persistence.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public List<TodoEntity> create(final TodoEntity entity) {
        //Validations
        validate(entity);

        todoRepository.save(entity);

        log.info("Entity id : {} id saved.", entity.getId());

        return todoRepository.findByUserId(entity.getUserId());
    }

    private void validate(TodoEntity entity) {
        if (entity == null) {
            log.warn("Entity cannot be null.");
            throw new RuntimeException("Entity cannot be null.");
        }

        if (entity.getUserId() == null) {
            log.warn("Unknown user.");
            throw new RuntimeException("Unknown user.");
        }
    }

}
