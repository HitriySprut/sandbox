import Entity.Zakupka;
import org.hibernate.Session;

/**
 * Created by admin on 02.05.2018.
 */
public class Main {
    public static int startProcessing(){
        int errorCode = 0;
        try {
            Solution.parseGosuslugi();
        } catch (Exception e) {
            errorCode=1;
        }
        return errorCode;
    }

    public static void main(String[] args) {
       //startProcessing();
        System.out.println("Maven + Hibernate + MySQL");
        Zakupka user = new Zakupka(123, "Test", "ifnot");
        System.out.println(Zakupka.class.toString());
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();


        session.save(user);
        session.getTransaction().commit();
    }
}
