package common.employee;

import java.util.List;
import jdbc.JdbcTemplate;
import jdbc.RowMapper;

public class EmployeeDAO {
  public void addEmployee(Employee employee) {
    JdbcTemplate template = new JdbcTemplate();
    String sql = "insert into EMPLOYEES (birth, department, gender, name) values(?,?,?,?)";
    template.executeUpdate(sql, employee.getBirth(), employee.getDepartment(), employee.isGender(), employee.getName());
  }

  public Employee findByEmployeeId(String employeeId) {
    RowMapper<Employee> rm = rs ->
        new Employee(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getBoolean("gender"),
            rs.getString("birth"),
            rs.getString("department")
        );

    JdbcTemplate template = new JdbcTemplate();
    String sql = "select * from EMPLOYEES where id = ?";
    return template.executeQuery(sql, rm, employeeId);
  }

  public void removeEmployee(int employeeId) {
    JdbcTemplate template = new JdbcTemplate();
    String sql = "delete from EMPLOYEES where id = ?";
    template.executeUpdate(sql, employeeId);
  }

  public void updateEmployee(Employee employee) {
    JdbcTemplate template = new JdbcTemplate();
    String sql = "update EMPLOYEES set birth = ?, department = ?, gender = ?, name = ? where id = ?";
    template.executeUpdate(sql,
        employee.getBirth(), employee.getDepartment(), employee.isGender(), employee.getName(), employee.getEmployeeId()
    );
  }

  public List<Employee> findEmployees() {
    RowMapper<Employee> rm = rs ->
        new Employee(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getBoolean("gender"),
            rs.getString("birth"),
            rs.getString("department")
        );

    JdbcTemplate template = new JdbcTemplate();
    String sql = "select * from EMPLOYEES";
    return template.list(sql, rm);
  }
}

