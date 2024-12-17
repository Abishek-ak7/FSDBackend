package form.backend.repository;

import form.backend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByEmployeeId(Long employeeId);
    boolean existsByEmail(String email);
}
