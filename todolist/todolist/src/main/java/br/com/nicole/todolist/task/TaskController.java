package br.com.nicole.todolist.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private ITaskRepository taskRepository;

    private static final String STRING = "/";

    @PostMapping(STRING)
    public ResponseEntity<String> create(@RequestBody TaskModel taskModel, HttpServletRequest request) {
        System.out.println("Chegou no controller");
        var idUser = getIdUser(request);
        taskModel.setIdUser((UUID) idUser);
        
        var currentDate = LocalDateTime.now();
        if (currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndAt())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("A data de início/data do término deve ser posterior à data atual");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Tarefa criada com sucesso!");
    }

    @GetMapping("/")
    public List<TaskModel> list(HttpServletRequest request) {
        var idUser = getIdUser(request);
        var tasks = this.taskRepository.findByIdUser((UUID) idUser);
        return tasks;
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody TaskModel taskModel, @PathVariable UUID id, HttpServletRequest request) {
        var task = this.taskRepository.findById(id).orElse(null);

        if (task == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tarefa não encontrada");
        }

        var idUser = getIdUser(request);

        if (!task.getIdUser().equals(idUser)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não tem permissão para alterar essa tarefa");
        }

        task.setDescription(taskModel.getDescription());
        task.setStartAt(taskModel.getStartAt());
        task.setEndAt(taskModel.getEndAt());

        return ResponseEntity.status(HttpStatus.OK).body("Tarefa atualizada com sucesso!");
    }

    private Object getIdUser(HttpServletRequest request) {
        var idUser = request.getAttribute("idUser");
        return idUser;
    }
}
