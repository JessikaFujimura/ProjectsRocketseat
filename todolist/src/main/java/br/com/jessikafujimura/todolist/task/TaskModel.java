package br.com.jessikafujimura.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "tb_task")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID taskId;

    private String description;

    @Column(length = 50)
    private String title;
    
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String priority;

    private UUID idUser;

    @CreationTimestamp
    private LocalDateTime createAt;

    public void setTitle(String title) throws Exception{
        if(title.length() > 50){
            throw new Exception("Tamanho do título excedido");
        }
        this.title = title;
    }
    
}
