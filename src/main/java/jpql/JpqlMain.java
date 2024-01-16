package jpql;

import javax.persistence.*;
import javax.persistence.criteria.From;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class JpqlMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {


            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("관리자");
            member.setAge(10);
            member.setTeam(team);
            member.setMemberType(MemberType.ADMIN);


            em.persist(member);


            em.flush();
            em.clear();


            String query = "select t.members From Team t";

            Collection resultList = em.createQuery(query, Collection.class).getResultList();
            for (Object o : resultList) {
                System.out.println("o = " + o);
                
            }


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }
}
