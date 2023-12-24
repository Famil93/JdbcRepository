package az.spring.familbudaqjdbc;

import az.spring.familbudaqjdbc.config.SpringJdbcConfig;
import az.spring.familbudaqjdbc.dao.EmployeeDao;
import az.spring.familbudaqjdbc.model.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JdbcApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringJdbcConfig.class);
        EmployeeDao employeeDao=applicationContext.getBean(EmployeeDao.class);

        Employee emp= new Employee();
        emp.setName("Elvin");
        emp.setSurname("Huseynzade");
        emp.setAge(26);
        emp.setSalary(1500);
       employeeDao.insert(emp);

       employeeDao.delete(3);
       long count= employeeDao.count();
        System.out.println("count:"+count);
        Employee employee=employeeDao.getEmployeeById(1);
        System.out.println(employeeDao);
        System.out.println(employeeDao.getAllEmployees());

    }
}