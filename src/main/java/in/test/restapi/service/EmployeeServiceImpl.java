package in.test.restapi.service;

import ch.qos.logback.core.pattern.parser.OptionTokenizer;
import in.test.restapi.model.Employee;
import in.test.restapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository eRepository;

    @Override
    public List<Employee> getEmployees() {
        return eRepository.findAll();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return eRepository.save(employee);
    }

    @Override
    public Employee getSingleEmployee(Long id) {
        Optional<Employee> employee =  eRepository.findById(id); // return empty if null otherwise value, value will get through get()
        if(employee.isPresent()){
            return employee.get();
        }
        throw new RuntimeException("Employee is not present against id:"+id);
    }

    @Override
    public void deleteEmployee(Long id) {
        eRepository.deleteById(id);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return eRepository.save(employee);
    }
}
