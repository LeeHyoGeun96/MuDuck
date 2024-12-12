package MuDuck.MuDuck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
@EntityScan("MuDuck.MuDuck.domain")  // 엔티티 스캔 위치
@EnableJpaRepositories("MuDuck.MuDuck.repository")  // 레포지토리 스캔 위치
public class MuDuckApplication {

    public static void main(String[] args) {
        SpringApplication.run(MuDuckApplication.class, args);
    }
}