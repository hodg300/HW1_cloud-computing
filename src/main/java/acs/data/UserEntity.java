package acs.data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import acs.annotations.Email;
import acs.annotations.NotEmptyFields;
import org.hibernate.annotations.Formula;

import acs.annotations.Password;

@Entity
@Table(name="USERS")
public class UserEntity {

    @Id
    @Email
    private String email;        // EMAIL PK VARCHAR(255)

    @NotEmpty
    private String firstName;    // FIRST VARCHAR(255)

    @NotEmpty
    private String lastName;    // LAST VARCHAR(255)

    @Password
    private String password;    // PASSWORD VARCHAR(255)

    @NotNull
    private Date birthdate;    // CREATED_TIME_STAMP TIMESTAMP

    @NotEmptyFields
    @ElementCollection(targetClass = String.class)
    private Set<String> roles;

    @Formula("DATE_PART('YEAR', AGE(birthdate))::int")
    private int age;    // calculated field by birth date


    public UserEntity() {
        this.roles = new HashSet<>();
    }

    public UserEntity(String email, String firstName, String lastName, String password, Date birthdate, Set<String> roles) {
        super();
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.birthdate = birthdate;
        this.roles = roles;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date date) {
        this.birthdate = date;
    }

    public void setAge(int age) {
        this.age=age;
    }

    public int getAge(){
        return this.age;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public int getAge(LocalDate birthdate) {
//        int age = LocalDate.now().getYear() - birthdate.getYear() - 1;
//        if (LocalDate.now().getMonthValue() > birthdate.getMonthValue()) {
//            // had birthday
//            age++;
//        } else if (LocalDate.now().getMonthValue() == birthdate.getMonthValue()) {
//            if (LocalDate.now().getDayOfMonth() >= birthdate.getDayOfMonth()) {
//                // had birthday
//                age++;
//            }
//        }
//        return age;
//    }
}
