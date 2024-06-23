package pe.upc.learningcenter.user.application.services.queryservices;

import org.springframework.stereotype.Service;
import pe.upc.learningcenter.user.domain.model.aggregates.Teacher;
import pe.upc.learningcenter.user.domain.model.querys.GetAllTeachersQuery;
import pe.upc.learningcenter.user.domain.service.TeacherQueryService;
import pe.upc.learningcenter.user.infrastructure.persistence.jpa.TeacherRepository;

import java.util.List;

@Service
public class TeacherQueryServiceImpl implements TeacherQueryService {
    private final TeacherRepository teacherRepository;

    public TeacherQueryServiceImpl(TeacherRepository teacherRepository) { this.teacherRepository = teacherRepository; }

    @Override
    public List<Teacher> handle(GetAllTeachersQuery query) { return teacherRepository.findAll(); }
}
