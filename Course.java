public class Course {
    private String courseCode;
    private String courseName;
    public Course(String code, String name){
        courseCode = code;
        courseName = name;
    }
    public String getCourseCode() {
        return courseCode;
    }
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

}
