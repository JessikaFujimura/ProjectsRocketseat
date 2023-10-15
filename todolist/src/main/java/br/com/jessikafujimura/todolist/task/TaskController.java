package br.com.jessikafujimura.todolist.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jessikafujimura.todolist.exception.DateValidationException;
import br.com.jessikafujimura.todolist.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/task")
    public ResponseEntity<TaskModel> createTask(@RequestBody TaskModel taskModel, HttpServletRequest httpServletRequest){
        taskModel.setIdUser((UUID) httpServletRequest.getAttribute("idUser"));
        validateDate(taskModel);
        var taskSaved = this.taskRepository.save(taskModel);
        return new ResponseEntity<TaskModel>(taskSaved, null, HttpStatus.CREATED);
    }

    private void validateDate(TaskModel taskModel) {
        var currentDate = LocalDateTime.now();
        if(currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndAt())){
          throw new DateValidationException("A data de inicío/término deve ser maior que a data atual");
        }
        if(taskModel.getStartAt().isAfter(taskModel.getEndAt())){
          throw new DateValidationException("A data de inicío deve ser menor que a data de término");
        }

    }

    @GetMapping()
    public ResponseEntity<List<TaskModel>> listTasks(HttpServletRequest httpServletRequest){
        var tasks = taskRepository.findAllByIdUser((UUID) httpServletRequest.getAttribute("idUser"));
        return ResponseEntity.ok().body(tasks);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity updateTask(@PathVariable UUID uuid, @RequestBody TaskModel taskModel, HttpServletRequest httpServletRequest) {
    
        var taskPersisted = taskRepository.findById(uuid).orElse(null);

        if(Objects.isNull(taskPersisted)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada");
        }

        var user = (UUID) httpServletRequest.getAttribute("idUser");

        if(!user.equals(taskPersisted.getIdUser())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuário não tem permissão");

        }

        Utils.copyNonNullProperties(taskModel, taskPersisted);
        return ResponseEntity.ok().body(taskRepository.save(taskPersisted));
    }
    
}
