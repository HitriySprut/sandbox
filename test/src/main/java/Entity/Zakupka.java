package Entity;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "zakupka")
public class Zakupka {
  public Integer getId() {
    return Id;
  }

  public String getDescription() {
    return description;
  }

  public String getCond() {
    return cond;
  }

  public void setId(Integer id) {
    Id = id;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setCond(String cond) {
    this.cond = cond;
  }

  private Integer Id;
  private String description;
  private String cond;

  public Zakupka(int id, String description, String condition)
  {
    Id = id;
    this.description = description;
    this.cond = condition;
  }
}
