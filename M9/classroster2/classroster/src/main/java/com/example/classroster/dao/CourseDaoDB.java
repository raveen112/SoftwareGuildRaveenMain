
import com.example.classroster.dao.CourseDao;
import com.example.classroster.dao.StudentDaoDB.StudentMapper;
import com.example.classroster.dao.TeacherDaoDB.TeacherMapper;
import com.example.classroster.dto.Course;
import com.example.classroster.dto.Student;
import com.example.classroster.dto.Teacher;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CourseDaoDB implements CourseDao {
    
    @Autowired
    JdbcTemplate jdbc;
    
    final String SELECT_COURSE_BY_ID = "SELECT id, name, description, teacherId FROM course WHERE id = ?";
    final String SELECT_ALL_STUDENTS = "SELECT id, name, description, teacherId FROM course";
    final String SELECT_TEACHER_FOR_COURSE = "SELECT t.* FROM teacher t JOIN course c ON c.teacherId = t.id WHERE c.id = ?";
    final String SELECT_STUDENTS_FOR_COURSE = "SELECT s.* FROM student s JOIN course_student cs ON cs.studentId = s.id WHERE cs.courseId = ?";
    final String SELECT_ALL_COURSES = "SELECT id, name, description, teacherId FROM course";
    final String SELECT_COURSES_FOR_TEACHER = "SELECT id, name, description, teacherId FROM course WHERE teacherId = ?";
    final String SELECT_COURSES_FOR_STUDENT = "SELECT c.* FROM course c JOIN course_student cs ON cs.courseId = c.Id WHERE cs.studentId = ?";
    final String INSERT_COURSE = "INSERT INTO course(name, description, teacherId) VALUES(?,?,?)";
    final String SELECT_LAST_ID = "SELECT LAST_INSERT_ID()";
    final String INSERT_COURSE_STUDENT = "INSERT INTO course_student(courseId, studentId) VALUES(?,?)";
    final String UPDATE_COURSE = "UPDATE course SET name = ?, description = ?, teacherId = ? WHERE id = ?";
    final String DELETE_COURSE_STUDENT = "DELETE FROM course_student WHERE courseId = ?";
    final String DELETE_COURSE = "DELETE FROM course WHERE id = ?";
    

    @Override
    public Course getCourseById(int id) {
        try{
            Course course = jdbc.queryForObject(SELECT_COURSE_BY_ID, new CourseMapper(), id);
            course.setTeacher(getTeacherForCourse(id));
            course.setStudents(getStudentsForCourse(id));
            return course;
        }catch(DataAccessException ex){
            return null;
        }
    }
    
    private Teacher getTeacherForCourse(int id){
        return jdbc.queryForObject(SELECT_TEACHER_FOR_COURSE, new TeacherMapper(), id);
    }
    
    private List<Student> getStudentsForCourse(int id){
        return jdbc.query(SELECT_STUDENTS_FOR_COURSE, new StudentMapper(), id);
    }
 
    @Override
    public List<Course> getAllCourses() {
    List<Course> courses = jdbc.query(SELECT_ALL_COURSES, new CourseMapper());
    associateTeacherAndStudents(courses);
    return courses;
    }
    
    private void associateTeacherAndStudents(List<Course> courses){
        for (Course course : courses){
            course.setTeacher(getTeacherForCourse(course.getId()));
            course.setStudents(getStudentsForCourse(course.getId()));
        }
    }

    @Override
    @Transactional
    public Course addCourse(Course course) {
        jdbc.update(INSERT_COURSE, 
                course.getName(),
                course.getDescription(),
                course.getTeacher().getId());
        int newId = jdbc.queryForObject(SELECT_LAST_ID, Integer.class);
        course.setId(newId);
        insertCourseStudent(course);
        
        return course;
    }
    
    private void insertCourseStudent(Course course){
        for(Student student : course.getStudents()){
            jdbc.update(INSERT_COURSE_STUDENT, 
                    course.getId(),
                    student.getId());
        }
    }

    @Override
    @Transactional
    public void updateCourse(Course course) {
        jdbc.update(UPDATE_COURSE, 
                course.getName(),
                course.getDescription(),
                course.getTeacher().getId(),
                course.getId());
        jdbc.update(DELETE_COURSE_STUDENT, course.getId());
        insertCourseStudent(course);
    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {
        jdbc.update(DELETE_COURSE_STUDENT, id);
        jdbc.update(DELETE_COURSE, id);
    }

    @Override
    public List<Course> getCoursesForTeacher(Teacher teacher) {
        List<Course> courses = jdbc.query(SELECT_COURSES_FOR_TEACHER, new CourseMapper(), teacher.getId());
        associateTeacherAndStudents(courses);
        return courses;
    }

    @Override
    public List<Course> getCoursesForStudent(Student student) {
        List<Course> courses = jdbc.query(SELECT_COURSES_FOR_STUDENT, new CourseMapper(), student.getId());
        associateTeacherAndStudents(courses);
        return courses;
    }
    
    public static final class CourseMapper implements RowMapper<Course>{
        @Override
        public Course mapRow(ResultSet rs, int index) throws SQLException {
            Course course = new Course();
            course.setId(rs.getInt("id"));
            course.setName(rs.getString("name"));
            course.setDescription(rs.getString("description"));
            
            return course;
        }
        
    }
    
}
