package in.test.restapi.controllers;

import in.test.restapi.model.Employee;
import in.test.restapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.Valid;
import java.util.List;


// rest controller is combination of controller + response body annotation
@RestController
//@RequestMapping("/api/v1") instead this we can use in properties file
public class EmployeeController {

    /*localhost:8080/api/v1*/

    @Autowired
    private EmployeeService eService;

    // we are fetching data from properties file
    @Value("${app.name: Application Name}")
    private String appName;

    // get value from properties file
    @Value("${app.version: version1}")
    private String appVersion;

    @GetMapping("")
    public ResponseEntity<String> appInfo(){
        return new ResponseEntity<>(appName+" "+appVersion, HttpStatus.OK);
    }

//    @RequestMapping(value = "/employees", method = RequestMethod.GET) this is general method we will be specific everytime

    // get all employees
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployees(){
        return new ResponseEntity<List<Employee>>(eService.getEmployees(),HttpStatus.OK);
    }

    // note if we have the same name of parameter, if we don't give name with annotation then it will be ok.
    // get single employee against id
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id){
        return new ResponseEntity<Employee>(eService.getSingleEmployee(id),HttpStatus.OK);
    }

    //add employee
    @PostMapping("/employees")
    public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee){
        return new ResponseEntity<Employee>(eService.saveEmployee(employee), HttpStatus.CREATED);
    }

    // update employee against employee id
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id,@RequestBody Employee employee){
        employee.setId(id);
        return new ResponseEntity<>(eService.updateEmployee(employee), HttpStatus.OK);
    }

    // delete employee against an id
    @DeleteMapping("/employees")
    public ResponseEntity<HttpStatus> getEmployees(@RequestParam("id") Long id){
        eService.deleteEmployee(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);

    }
}
