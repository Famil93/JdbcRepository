package az.spring.familbudaqjdbc.dao.impl;

import az.spring.familbudaqjdbc.dao.EmployeeDao;
import az.spring.familbudaqjdbc.model.Employee;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class EmployeeDaoImpl implements EmployeeDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;




    @Override
    public void insert(Employee employee) {
        String query= "insert into employee(name,surname,age,salary) values (:name,:surname,:age,:salary)";

        SqlParameterSource sqlParameterSource= new MapSqlParameterSource()
                .addValue("name", employee.getName())
                        .addValue("surname",employee.getSurname())
                                .addValue("age",employee.getAge())
                                        .addValue("salary",employee.getSalary());

        namedParameterJdbcTemplate.update(query,sqlParameterSource);
    }

    @Override
    public void update(Employee employee) {
    String query="update employee set name=:name,surname=:surname,age=:age,salary=:salary where id=:id";
    SqlParameterSource sqlParameterSource= new MapSqlParameterSource()
            .addValue("name",employee.getName())
                    .addValue("surname",employee.getSurname())
                            .addValue("age",employee.getAge())
                                    .addValue("salary",employee.getSalary())
                                            .addValue("id",employee.getId());
    namedParameterJdbcTemplate.update(query,sqlParameterSource);
    }

    @Override
    public void delete(int id) {
    String query="delete from employee where id=:id";
    SqlParameterSource sqlParameterSource= new MapSqlParameterSource()
            .addValue("id",id);
    namedParameterJdbcTemplate.update(query,sqlParameterSource);
    }

    @Override
    public Employee getEmployeeById(int id) {
        String query = "select * from employee where id=:id";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("id", id);
        Employee employee = namedParameterJdbcTemplate.queryForObject(query, sqlParameterSource, new BeanPropertyRowMapper<Employee>(Employee.class));
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        String query = "select * from employee";
        List<Employee> employees=namedParameterJdbcTemplate.query(query,new BeanPropertyRowMapper<Employee>(Employee.class));
        return employees;
    }


    @Override
      public long count() {
        String query="select count(*) from employee";
    Long count= namedParameterJdbcTemplate.queryForObject(query,new MapSqlParameterSource(),Long.class);
    return count;
    }
    private static class EmployeeRowMapper implements RowMapper<Employee>{

        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Employee(
              rs.getInt("id"),
               rs.getString("name"),
                    rs.getString("surname"),
                    rs.getInt("age"),
            rs.getDouble("salary")
            );

    }
}

}
