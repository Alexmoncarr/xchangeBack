package xchange.x_change.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("xchange.x_change.domain")
@EnableJpaRepositories("xchange.x_change.repos")
@EnableTransactionManagement
public class DomainConfig {
}
