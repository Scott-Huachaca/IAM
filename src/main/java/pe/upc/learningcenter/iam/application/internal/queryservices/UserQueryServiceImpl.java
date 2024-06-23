package pe.upc.learningcenter.iam.application.internal.queryservices;

import pe.upc.learningcenter.iam.domain.model.aggregates.User;
import pe.upc.learningcenter.iam.domain.model.queries.GetAllUsersQuery;
import pe.upc.learningcenter.iam.domain.model.queries.GetUserByIdQuery;
import pe.upc.learningcenter.iam.domain.model.queries.GetUserByUsernameQuery;
import pe.upc.learningcenter.iam.domain.services.UserQueryService;
import pe.upc.learningcenter.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserQueryServiceImpl implements UserQueryService {
    private final UserRepository userRepository;

    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> handle(GetAllUsersQuery query) {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return userRepository.findById(query.userId());
    }

    @Override
    public Optional<User> handle(GetUserByUsernameQuery query) {
        return userRepository.findByUsername(query.username());
    }
}
