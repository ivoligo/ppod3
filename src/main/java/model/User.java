package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users3")
public class User {
    @Id
    @Column(name =  "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="login")
    private String login;

    @Column(name = "age")
    private int age;

    @Column(name = "city")
    private String city;

    @Column(name = "password")
    private String password;

    public User(){
    }

    public User(String login, int age, String city, String password) {
        this.login = login;
        this.age = age;
        this.city = city;
        this.password = password;
    }

    public User(long id, String login, int age, String city, String password) {
        this.id = id;
        this.login = login;
        this.age = age;
        this.city = city;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getAge(){
        return age;
    }

    public void setAge(int age){
        this.age = age;
    }

    public String getCity(){
        return city;
    }

    public void setCity(String email){
        this.city = city;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (age != user.age) return false;
        if (!Objects.equals(login, user.login)) return false;
        if (!Objects.equals(city, user.city)) return false;
        return Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", age=" + age +
                ", city='" + city + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
