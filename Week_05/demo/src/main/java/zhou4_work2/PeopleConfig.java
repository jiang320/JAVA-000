package zhou4_work2;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


// https://github.com/yangxing9/JAVA-000/blob/main/Week_05/src/main/java/work2/BookConfig.java
@Configuration
public class PeopleConfig {

    @Bean("people")
    public People getPeople(){
        return  new People("shasha","male");
    }

}
