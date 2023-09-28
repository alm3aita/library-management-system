package digit.com.sys.lms.repositories;

import digit.com.sys.lms.models.entities.members.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
}
