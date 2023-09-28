package digit.com.sys.lms.repositories;

import digit.com.sys.lms.models.entities.institutions.College;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollegeRepository extends JpaRepository<College, String> {


    @Query(value = "SELECT id FROM STUDENT " +
            "UNION ALL " +
            "SELECT id FROM LIBRARIAN " +
            "UNION ALL " +
            "SELECT id FROM COLLEGE_EMPLOYEE", nativeQuery = true)
    List<String> findAllIdsFromAllUsers();
}
