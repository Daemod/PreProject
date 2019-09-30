package ru.severyuchin.entitys;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "role")
    private String role;
    @Column(name = "name", unique = true)
    private String name;
    @Column(name = "password")
    private int password;
    @Column(name = "work")
    private String work;
    @Column(name = "age")
    private int age;

    public User() {
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password.hashCode();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User(String name, String password, String work, int age) {
        this.name = name;
        this.work = work;
        this.age = age;
        setPassword(password);
    }

    public User(long id, String password, String name, String work, int age) {
        this(name, password, work, age);
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
