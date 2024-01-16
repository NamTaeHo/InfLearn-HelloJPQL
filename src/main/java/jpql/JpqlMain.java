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


            Team teamA = new Team();
            teamA.setName("팀A");
            em.persist(teamA);

            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("팀b");
            em.persist(teamB);

            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("회원1");
            member1.setAge(10);
            member1.setTeam(teamA);
            member1.setMemberType(MemberType.USER);

            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.setAge(18);
            member2.setTeam(teamA);
            member2.setMemberType(MemberType.USER);

            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("회원3");
            member3.setAge(11);
            member3.setTeam(teamB);
            member3.setMemberType(MemberType.USER);

            em.persist(member3);


            em.flush();
            em.clear();


            String query = "select distinct t from Team t join fetch t.members";

            List<Team> resultList = em.createQuery(query, Team.class).getResultList();
            for (Team team : resultList) {
                System.out.println("Team.getName() = " + team.getName()+" members  = " + team.getMembers().size());
                for(Member member : team.getMembers()){
                    System.out.println("member = " + member);
                }


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
