package jpql;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

public class JpqlMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            
            Member member = new Member();
            member.setUsername("member1");
            member.setAge(23);
            em.persist(member);

            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setAge(34);
            em.persist(member2);

            List<Member> resultList = em.createQuery("select m from Member m where m.username = :username", Member.class)
                    .setParameter("username", "member1")
                    .getResultList();
            for (Member member1 : resultList) {
                System.out.println("member1 = " + member1);
                
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
