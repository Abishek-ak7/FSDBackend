package form.backend.controller;

import form.backend.repository.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import form.backend.model.Employee;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @PostMapping
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
        try {
            if (employeeRepository.existsByEmployeeId(employee.getEmployeeId())) {
                return ResponseEntity.badRequest().body("Employee ID already exists.");
            }
            if (employeeRepository.existsByEmail(employee.getEmail())) {
                return ResponseEntity.badRequest().body("Email already exists.");
            }
            employeeRepository.save(employee);
            return ResponseEntity.ok("Employee added successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Internal Server Error: " + e.getMessage());
        }
    }

    @GetMapping("/")
    public String Home() {
        return "Hi to the web";
    }
    
    
    
}
