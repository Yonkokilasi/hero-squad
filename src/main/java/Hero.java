import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Hero {
    private String name;
    private int age;
    private String power;
    private String weakness;
    private int squadId;
    private int id;

    public Hero(String name, int age ,String power,String weakness) {
        this.name = name;
        this.age = age;
        this.power = power;
        this.weakness = weakness;
        this.squadId = squadId;

    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public String getPower() {
        return power;
    }
    public String getWeakness() {
        return weakness;
    }
    public int getId() {
        return id;
    }
    public int getSquadId() {
        return squadId;
    }

    public static List<Hero> all() {
      String sql = "SELECT id, name, squadId,age,power,weakness FROM heroes";
      try(Connection con = DB.sql2o.open()) {
       return con.createQuery(sql).executeAndFetch(Hero.class);
      }
    }

    @Override
    public boolean equals(Object otherHero){
      if (!(otherHero instanceof Hero)) {
        return false;
      } else {
        Task newTask = (Hero) otherHero;
        return this.getName().equals(newHero.getName()) &&
               this.getId() == newHero.getId() &&
               this.getCategoryId() == newHero.getCategoryId();
      }
    }

    public void save() {
      try(Connection con = DB.sql2o.open()) {
        String sql = "INSERT INTO tasks(description, categoryId) VALUES (:description, :categoryId)";
        this.id = (int) con.createQuery(sql, true)
          .addParameter("description", this.description)
          .addParameter("categoryId", this.categoryId)
          .executeUpdate()
          .getKey();
      }
    }

    public static Task find(int id) {
      try(Connection con = DB.sql2o.open()) {
        String sql = "SELECT * FROM tasks where id=:id";
        Task task = con.createQuery(sql)
          .addParameter("id", id)
          .executeAndFetchFirst(Task.class);
        return task;
      }
    }
  }
