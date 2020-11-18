package zhou4_work2;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//https://github.com/yangxing9/JAVA-000/tree/main/Week_05
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=PeopleConfig.class)
public class mytest {

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

    @Autowired
    private  People people;

    public  void play(){
        people.printPeople();
        ApplicationContext context = new AnnotationConfigApplicationContext(PeopleScan.class);
        People annotationBook = context.getBean("annotationPeople", People.class);
        annotationBook.printPeople();

        People bookXml = (People) applicationContext.getBean("peopleXml");
        bookXml.printPeople();
    }
}
