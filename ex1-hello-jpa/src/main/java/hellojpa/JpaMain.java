package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager entityManager =  emf.createEntityManager();

        EntityTransaction tx =  entityManager.getTransaction();
        tx.begin();

        try {
            //추가 입력 시
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloA");
//            entityManager.persist(member);

            //찾기 시
//            Member findMember = entityManager.find(Member.class,1L);
//            System.out.println("findMember.getId(): "+findMember.getId());
//            System.out.println("findMember.getName(): "+findMember.getName());

            //찾은 데이터의 내용을 변경이 적용 가능
//            findMember.setName("HelloJPA");
            //삭제 시
//            entityManager.remove(findMember);

            //수정
            List<Member> result = entityManager.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(5)
                    .setMaxResults(8)
                    .getResultList();

            for (Member member: result) {
                System.out.println("member: "+ member.getName()+ ", "+member.getId());
            }

            tx.commit();
        }
        catch (Exception e){
            tx.rollback();
        }
        finally {
            entityManager.close();
            emf.close();
        }

    }
}
