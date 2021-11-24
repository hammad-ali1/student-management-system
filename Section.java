public class Section {
    private String name;
    private String course1;
    private String course2;
    private String course3;
    private String course4;
    public Section(String name, String c1, String c2, String c3, String c4){
        this.name = name;
        course1 = c1;
        course2 = c2;
        course3 = c3;
        course4 = c4;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCourse1() {
        return course1;
    }
    public void setCourse1(String course1) {
        this.course1 = course1;
    }
    public String getCourse2() {
        return course2;
    }
    public void setCourse2(String course2) {
        this.course2 = course2;
    }
    public String getCourse3() {
        return course3;
    }
    public void setCourse3(String course3) {
        this.course3 = course3;
    }
    public String getCourse4() {
        return course4;
    }
    public void setCourse4(String course4) {
        this.course4 = course4;
    }
}
