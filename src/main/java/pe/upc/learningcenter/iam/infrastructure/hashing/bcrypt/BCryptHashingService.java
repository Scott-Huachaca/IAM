package pe.upc.learningcenter.iam.infrastructure.hashing.bcrypt;

import pe.upc.learningcenter.iam.application.internal.outboundservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface BCryptHashingService extends HashingService, PasswordEncoder {

}
