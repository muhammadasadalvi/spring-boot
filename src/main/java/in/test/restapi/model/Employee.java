package in.test.restapi.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.EnableMBeanExport;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_employee")
public class Employee {

    // to make the below field primary key we use @Id annotation
    // if the column and field name is same then we can get rid of @column annotation but
    // where you want different names you can use @column annotation

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    //    @JsonProperty("first_name") => this is used as alias and this will map the first_name to name
//    @NotNull(message = "Name Field Must Not Be Null")
//    @NotEmpty(message = "Name Field should not be Null")
    @NotBlank(message = "Name Field should not be Null") // it will check for both of above it actually check for characters
    private String name;
//    @JsonIgnore => this is used to ignore the filed during json response
    private Long age = 0L;
    private String location;
    @Email(message = "must ne a valid email address")
    private String email;
    @NotNull(message = "Department should not be null")
    private String department;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;
}
