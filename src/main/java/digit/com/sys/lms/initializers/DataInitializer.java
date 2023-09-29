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
import java.util.Random;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final CollegeRepository collegeRepository;

    College college = new College();
    Library library = new Library();

    public void initData() {


        college.setAddress("sahrawi street-zarqa");
        college.setName("hashemite university");

        library.setAddress(college.getName());
        library.setName(college.getName() + " library");
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
        college.setStudents(students);
        college.setCollegeEmployees(collegeEmployees);
        college.setLibrary(library);

        collegeRepository.save(college);

    }


    public List<CollegeEmployee> initCollegeEmployees(List<CollegeEmployee> collegeEmployees) {

        String[] names = {"ashraf", "khaled", "ronaldo", "ameen", "mousa"};
        String[] departments = {"accounting", "Health Sciences", "Business Administration", "Environmental Science", "Fine Arts"};

        IntStream.range(0, 5).forEach(i -> {
            CollegeEmployee collegeEmployee = new CollegeEmployee();

            collegeEmployee.setName(names[i]);
            collegeEmployee.setDepartment(departments[i]);
            collegeEmployee.setEmployeeType("Prof " + (i + 1));
            collegeEmployee.setDateOfBirth(new Date());
            collegeEmployee.setEmail(names[i] + "@hu.com");
            collegeEmployee.setPhoneNumber("079345678" + i);
            collegeEmployee.setCollege(college);

            collegeEmployees.add(collegeEmployee);
        });
        return collegeEmployees;
    }

    public List<Student> initStudents(List<Student> students) {

        String[] names = {"feras", "rami", "omar", "ali", "hussam"};
        String[] majors = {"Computer Science", "Biology", "History", "Mathematics", "Psychology"};

        IntStream.range(0, 5).forEach(i -> {
            Student student = new Student();

            student.setDateOfBirth(new Date());
            student.setEmail(names[i] + "@hu.com");
            student.setPhoneNumber("123456789" + i);
            student.setMajor(majors[i]);
            student.setName(names[i]);
            student.setCollege(college);

            students.add(student);
        });
        return students;
    }

    public List<Librarian> initLibrarians(List<Librarian> librarians) {

        String[] names = {"ameer", "abdullah", "ayman", "osama", "zakarya"};

        IntStream.range(0, 5).forEach(i -> {
            Librarian librarian = new Librarian();
            librarian.setEmployeeType("librarian");
            librarian.setDateOfBirth(new Date());
            librarian.setEmail(names[i] + "@hu.com");
            librarian.setPhoneNumber("123456789" + i);
            librarian.setName(names[i]);
            librarian.setLibrary(library);
            librarian.setCollege(college);

            librarians.add(librarian);
        });
        return librarians;
    }


    private List<LibraryItem> initLibraryItems(List<LibraryItem> libraryItems) {

        String[] authors = {"J.K. Rowling", "George Orwell", "Harper Lee", "J.R.R. Tolkien", "Agatha Christie"};
        String[] titles = {"Harry Potter and the Sorcerer's Stone", "1984", "To Kill a Mockingbird", "The Hobbit", "Murder on the Orient Express"};

        Random random = new Random();
        IntStream.range(0, 5).forEach(i -> {
            LibraryItem libraryItem = new LibraryItem();

            libraryItem.setAvailable(true);
            libraryItem.setAuthor(authors[i]);
            libraryItem.setYearPublished(1950 + random.nextInt(50));
            libraryItem.setLibraryItemType(LibraryItemType.BOOK);
            libraryItem.setName(titles[i]);
            libraryItem.setQuantity(10 + random.nextInt(20));

            libraryItem.setLibrary(library);


            libraryItems.add(libraryItem);
        });
        return libraryItems;

    }

}

