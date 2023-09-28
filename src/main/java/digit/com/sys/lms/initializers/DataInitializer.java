package digit.com.sys.lms.initializers;

import digit.com.sys.lms.models.entities.institutions.College;
import digit.com.sys.lms.models.entities.institutions.Library;
import digit.com.sys.lms.models.entities.materials.LibraryItem;
import digit.com.sys.lms.models.entities.members.CollegeEmployee;
import digit.com.sys.lms.models.entities.members.Librarian;
import digit.com.sys.lms.models.entities.members.Student;
import digit.com.sys.lms.models.enums.LibraryItemType;
import digit.com.sys.lms.repositories.CollegeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final CollegeRepository collegeRepository;

    College college = new College();
    Library library = new Library();

    public void initData() {


        college.setAddress("3esa district");
        college.setName("3esa");

        library.setAddress("3esa jam3a");
        library.setName("3esa library");
        library.setCollege(college);

        List<Student> students = new ArrayList<>();
        students = initStudents(students);


        List<CollegeEmployee> collegeEmployees = new ArrayList<>();
        collegeEmployees = initCollegeEmployees(collegeEmployees);

        List<Librarian> librarians = new ArrayList<>();
        librarians = initLibrarians(librarians);

        List<LibraryItem> libraryItems = new ArrayList<>();
        libraryItems = initLibraryItems(libraryItems);


        library.setLibrarians(librarians);
        library.setLibraryItems(libraryItems);

        college.setAddress("3esa district");
        college.setName("3esa");
        college.setStudents(students);
        college.setCollegeEmployees(collegeEmployees);
        college.setLibrary(library);

        collegeRepository.save(college);

    }


    public List<CollegeEmployee> initCollegeEmployees(List<CollegeEmployee> collegeEmployees) {
        IntStream.range(0, 5).forEach(i -> {
            CollegeEmployee collegeEmployee = new CollegeEmployee();

            collegeEmployee.setName("Employee " + (i + 1));
            collegeEmployee.setDepartment("dep " + (i + 1));
            collegeEmployee.setEmployeeType("Type " + (i + 1));
            collegeEmployee.setDateOfBirth(new Date());
            collegeEmployee.setEmail("employee" + (i + 1) + "@example.com");
            collegeEmployee.setPhoneNumber("123456789" + i);
            collegeEmployee.setCollege(college);

            collegeEmployees.add(collegeEmployee);
        });
        return collegeEmployees;
    }

    public List<Student> initStudents(List<Student> students) {
        IntStream.range(0, 5).forEach(i -> {
            Student student = new Student();

            student.setDateOfBirth(new Date());
            student.setEmail("student" + (i + 1) + "@example.com");
            student.setPhoneNumber("123456789" + i);
            student.setMajor("Major " + (i + 1)); // Set the major
            student.setName("stud" + 1);
            student.setCollege(college);

            students.add(student);
        });
        return students;
    }

    public List<Librarian> initLibrarians(List<Librarian> librarians) {
        IntStream.range(0, 5).forEach(i -> {
            Librarian librarian = new Librarian();
            librarian.setEmployeeType("Type " + (i + 1));
            librarian.setDateOfBirth(new Date());
            librarian.setEmail("librarian" + (i + 1) + "@example.com");
            librarian.setPhoneNumber("123456789" + i);
            librarian.setName("libr" + 1);
            librarian.setLibrary(library);
            librarian.setCollege(college);

            librarians.add(librarian);
        });
        return librarians;
    }


    private List<LibraryItem> initLibraryItems(List<LibraryItem> libraryItems) {
        IntStream.range(0, 5).forEach(i -> {
            LibraryItem libraryItem = new LibraryItem();

            libraryItem.setAvailable(true);
            libraryItem.setAuthor("Author " + (i + 1)); // Set the Author
            libraryItem.setYearPublished(2000 + i); // Set the yearPublished
            libraryItem.setLibraryItemType(LibraryItemType.BOOK);
            libraryItem.setName("Book " + (i + 1)); // Set the name
            libraryItem.setQuantity(12);

            libraryItem.setLibrary(library);


            libraryItems.add(libraryItem);
        });
        return libraryItems;

    }

}

