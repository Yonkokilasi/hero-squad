import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Squad {
    private String name;
    private int id;

    public Squad(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public static List<Squad> all() {
      String sql = "SELECT id, name FROM squads";
      try(Connection con = DB.sql2o.open()) {
        return con.createQuery(sql).executeAndFetch(Squad.class);
      }
    }

    public int getId() {
      return id;
    }

   public static Squad find(int id) {
       try(Connection con = DB.sql2o.open()) {
         String sql = "SELECT * FROM squads where id=:id";
         Squad Squad = con.createQuery(sql)
           .addParameter("id", id)
           .executeAndFetchFirst(Squad.class);
         return Squad;
       }
     }

   public List<Hero> getHeroes() {
     try(Connection con = DB.sql2o.open()) {
       String sql = "SELECT * FROM heroes where SquadId=:id";
       return con.createQuery(sql)
         .addParameter("id", this.id)
         .executeAndFetch(Hero.class);
     }
   }

   @Override
   public boolean equals(Object otherSquad) {
     if (!(otherSquad instanceof Squad)) {
       return false;
     } else {
       Squad newSquad = (Squad) otherSquad;
       return this.getName().equals(newSquad.getName()) &&
              this.getId() == newSquad.getId();
     }
   }

    public void save() {
      try(Connection con = DB.sql2o.open()) {
        String sql = "INSERT INTO squads(name) VALUES (:name)";
        this.id = (int) con.createQuery(sql, true)
          .addParameter("name", this.name)
          .executeUpdate()
          .getKey();
      }
    }

  }

}
