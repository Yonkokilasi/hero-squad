import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Hero {
    private String Name;
    private int Age;
    private String Power;
    private String Weakness;
    private int SquadId;

    public Hero(String name, int age ,String power,String weakness) {
        this.name = Name;
        this.age = Age;
        this.power = Power;
        this.weakness = Weakness;
        this.squadId = SquadId;
    }
    public String getName() {
        return Name;
    }
    public int getAge() {
        return Age;
    }
    public String getPower() {
        return Power;
    }
    public String getWeakness() {
        return Weakness;
    }
    public int getId() {
        return id;
    }
    public static List<Task> all() {
      String sql = "SELECT id, description, categoryId FROM tasks";
      try(Connection con = DB.sql2o.open()) {
       return con.createQuery(sql).executeAndFetch(Task.class);
      }
    }

    @Override
    public boolean equals(Object otherTask){
      if (!(otherTask instanceof Task)) {
        return false;
      } else {
        Task newTask = (Task) otherTask;
        return this.getDescription().equals(newTask.getDescription()) &&
               this.getId() == newTask.getId() &&
               this.getCategoryId() == newTask.getCategoryId();
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

}
