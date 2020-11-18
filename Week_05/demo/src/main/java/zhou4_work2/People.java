package zhou4_work2;


import org.springframework.stereotype.Component;

@Component("annotationPeople")
public class People {
    private String name;
    private String sex;

    public People(){

    }
    public People(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }
    public void printPeople(){
        System.out.println("name:"+ this.name+ ",sex:"+this.sex);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
