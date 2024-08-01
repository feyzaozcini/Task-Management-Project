package com.turkcell.projectservice.services.concretes;

import com.turkcell.projectservice.core.utils.types.NotFoundException;
import com.turkcell.projectservice.entities.Project;
import com.turkcell.projectservice.repositories.ProjectRepository;
import com.turkcell.projectservice.services.abstracts.ProjectService;
import com.turkcell.projectservice.services.dtos.requests.ProjectAddRequest;
import com.turkcell.projectservice.services.dtos.requests.ProjectSearchRequest;
import com.turkcell.projectservice.services.dtos.requests.ProjectUpdateRequest;
import com.turkcell.projectservice.services.dtos.responses.CreatedProjectResponse;
import com.turkcell.projectservice.services.dtos.responses.ProjectGetResponse;
import com.turkcell.projectservice.services.dtos.responses.ProjectSearchResponse;
import com.turkcell.projectservice.services.dtos.responses.ProjectUpdateResponse;
import com.turkcell.projectservice.services.mappers.ProjectMapper;
import com.turkcell.projectservice.services.rules.ProjectBusinessRules;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectBusinessRules projectBusinessRules;



    @Override
    public CreatedProjectResponse addProject(ProjectAddRequest request) {
        Project project= ProjectMapper.INSTANCE.projectFromAddRequest(request);
        project.setCreatedDate(LocalDateTime.now());
        project.setActive(true);
        projectRepository.save(project);
        return ProjectMapper.INSTANCE.getResponseFromCreatedProject(project);
    }

    @Override
    public List<ProjectGetResponse> getAllProject() {
        projectBusinessRules.checkIfAnyProjectIsExist();
        return projectRepository.findAll().stream().filter(Project::getActive).map((project)-> ProjectMapper.INSTANCE.getResponseFromProject(project)).collect(Collectors.toList());
    }

    @Override
    public ProjectGetResponse getProjectById(int id) {
        projectBusinessRules.checkIfProjectExistsById(id);
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found with id: " + id));

        if (!project.getActive()) {
            throw new NotFoundException("Project is not active");

        }
        return ProjectMapper.INSTANCE.getResponseFromProject(project);

    }

    @Override
    public ProjectUpdateResponse updateProject(ProjectUpdateRequest request) {
        projectBusinessRules.checkIfProjectExistsById(request.getId());
        Project project=projectRepository.findById(request.getId()).orElseThrow();
        project.setUpdatedDate(LocalDateTime.now());
        ProjectMapper.INSTANCE.projectFromUpdateRequest(request,project);
        projectRepository.save(project);
        return ProjectMapper.INSTANCE.getResponseFromUpdatedProduct(project);
    }

    @Override
    public void deleteProjectById(int id) {
        projectBusinessRules.checkIfProjectExistsById(id);
        Project project=projectRepository.findById(id).orElseThrow();
        project.setActive(false);
        project.setDeletedDate(LocalDateTime.now());
        projectRepository.save(project);
    }

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<ProjectSearchResponse> searchProject(ProjectSearchRequest request) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProjectSearchResponse> cq = cb.createQuery(ProjectSearchResponse.class);
        Root<Project> project = cq.from(Project.class);

        List<Predicate> predicates = new ArrayList<>();

        if (request.getId() != null) {
            predicates.add(cb.equal(project.get("id"), request.getId()));
        }

        if (request.getProjectName() != null && !request.getProjectName().isEmpty()) {
            predicates.add(cb.like(project.get("projectName"), "%" + request.getProjectName() + "%"));
        }

        if (request.getOwner() != null && !request.getOwner().isEmpty()) {
            predicates.add(cb.like(project.get("owner"), "%" + request.getOwner() + "%"));
        }

        cq.select(cb.construct(ProjectSearchResponse.class,
                project.get("id"),
                project.get("projectName"),
                project.get("owner"),
                project.get("description"),
                project.get("active")
        )).where(cb.and(predicates.toArray(new Predicate[0])));


        return entityManager.createQuery(cq).getResultList();

    }
    }
