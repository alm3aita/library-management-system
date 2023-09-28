package digit.com.sys.lms;

import digit.com.sys.lms.initializers.DataInitializer;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@RequiredArgsConstructor
public class LibrayManagementSystemApplication {

    private final DataInitializer dataInitializer;

    public static void main(String[] args) {
        SpringApplication.run(LibrayManagementSystemApplication.class, args);
    }


    @PostConstruct
    public void initializeData() {
        dataInitializer.initData();
    }

}
