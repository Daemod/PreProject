package model;

public class User {
    private long id;
    private String name;
    private String work;
    private int age;

    public User(String name, String work, int age) {
        this.name = name;
        this.work = work;
        this.age = age;
    }

    public User(long id, String name, String work, int age) {
        this(name, work, age);
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
