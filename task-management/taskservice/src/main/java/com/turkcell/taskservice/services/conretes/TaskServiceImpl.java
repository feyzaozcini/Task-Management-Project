package com.turkcell.taskservice.services.conretes;


import com.turkcell.common.events.KafkaTaskEvent;
import com.turkcell.common.events.KafkaTaskUpdateEvent;
import com.turkcell.taskservice.clients.ProjectGetResponse;
import com.turkcell.taskservice.clients.ProjectServiceClient;
import com.turkcell.taskservice.clients.UserGetResponse;
import com.turkcell.taskservice.clients.UserServiceClient;
import com.turkcell.taskservice.config.kafka.producer.KafkaProducer;
import com.turkcell.taskservice.config.kafka.properties.TaskCreatedTopicProperties;
import com.turkcell.taskservice.config.kafka.properties.TaskUpdatedTopicProperties;
import com.turkcell.taskservice.entities.Task;
import com.turkcell.taskservice.entities.enums.TaskStatus;
import com.turkcell.taskservice.repositories.TaskRepository;
import com.turkcell.taskservice.services.abstracts.TaskService;
import com.turkcell.taskservice.services.dtos.requests.TaskRequest;
import com.turkcell.taskservice.services.dtos.requests.TaskSearchRequest;
import com.turkcell.taskservice.services.dtos.requests.TaskUpdateRequest;
import com.turkcell.taskservice.services.dtos.responses.*;
import com.turkcell.taskservice.services.mappers.TaskMapper;
import com.turkcell.taskservice.services.rules.TaskBusinessRules;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;


@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    @Autowired
    private final TaskRepository taskRepository;

    @Autowired
    private final UserServiceClient userServiceClient;

    @Autowired
    private final ProjectServiceClient projectServiceClient;

    private final TaskBusinessRules taskBusinessRules;

    private final KafkaProducer kafkaProducer;

    private final TaskCreatedTopicProperties taskCreatedTopicProperties;

    private final TaskUpdatedTopicProperties taskUpdatedTopicProperties;

    @Override
    public TaskResponse addTask(TaskRequest request) {
        Task task= TaskMapper.INSTANCE.taskFromRequest(request);
        task.setStartDate(LocalDateTime.now());
        task.setActive(true);
        task=taskRepository.save(task);

        ProjectGetResponse project = projectServiceClient.getProjectById(task.getProjectId());
        List<UserGetResponse> users=userServiceClient.getUsersByIds(task.getUserIds());

        TaskResponse response= TaskMapper.INSTANCE.responseFromTask(task);

        response.setProject(project);
        response.setUsers(users);

        kafkaProducer.sendMessage(
                taskCreatedTopicProperties.getTopicName(),
                KafkaTaskEvent.builder().id(response.getId()).taskName(response.getTaskName()).description(response.getDescription()).startDate(response.getStartDate()).deadline(response.getDeadline()).build(),
                response.getId().toString()
        );

        return response;
    }


    @Override
    public TaskUpdateResponse updateTask(TaskUpdateRequest request) {
        taskBusinessRules.isTaskExist(request.getId());
        Task task=taskRepository.findById(request.getId()).orElseThrow();
        TaskMapper.INSTANCE.updateTaskFromUpdateRequest(request,task);
        task=taskRepository.save(task);

        TaskUpdateResponse response=TaskMapper.INSTANCE.updateResponseFromTask(task);

        //Kafka Topic mesajı

        kafkaProducer.sendMessage(
                taskUpdatedTopicProperties.getTopicName(),
                KafkaTaskUpdateEvent.builder().id(response.getId()).taskName(response.getTaskName()).description(response.getDescription()).deadline(response.getDeadline()).build(),
                response.getId().toString()
        );

        return response;
    }

    public TaskResponse buildResponse(Task task){
        TaskResponse response = TaskMapper.INSTANCE.responseFromTask(task);
        response.setProject(projectServiceClient.getProjectById(task.getProjectId()));
        response.setUsers(userServiceClient.getUsersByIds(task.getUserIds()));
        return response;
    }

    @Override
    public List<TaskResponse> getAllTasks() {
        List<TaskResponse> responses = new ArrayList<>();
        for(Task task : taskRepository.findAll()){
            responses.add(buildResponse(task));
            taskRepository.save(task);
        }
        return responses;
    }

    @Override
    public TaskResponse getTaskById(Integer id) {
        taskBusinessRules.isTaskExist(id);
        return buildResponse(taskRepository.findById(id).orElseThrow());
    }

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<TaskSearchResponse> searchTask(TaskSearchRequest request) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<TaskSearchResponse> cq = cb.createQuery(TaskSearchResponse.class);
        Root<Task> task = cq.from(Task.class);

        List<Predicate> predicates = new ArrayList<>();

        if (request.getId() != null) {
            predicates.add(cb.equal(task.get("id"), request.getId()));
        }

        if (request.getTaskName() != null && !request.getTaskName().isEmpty()) {
            predicates.add(cb.like(task.get("taskName"), "%" + request.getTaskName() + "%"));
        }

        if (request.getProjectId() != null) {
            predicates.add(cb.equal(task.get("projectId"), request.getProjectId()));
        }

        cq.select(cb.construct(TaskSearchResponse.class,
                task.get("id"),
                task.get("taskName"),
                task.get("projectId")
        )).where(cb.and(predicates.toArray(new Predicate[0])));


        return entityManager.createQuery(cq).getResultList();
    }


}
