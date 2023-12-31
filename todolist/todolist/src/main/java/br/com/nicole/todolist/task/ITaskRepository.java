package br.com.nicole.todolist.task;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ITaskRepository extends JpaRepository<TaskModel, UUID> {

    org.netbeans.modules.progress.spi.TaskModel save(org.netbeans.modules.progress.spi.TaskModel taskModel);
    List<TaskModel> findByIdUser(UUID idUser);
    
    
}
