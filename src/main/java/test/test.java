package test;

import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class test {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        
        System.out.println("✅ Hibernate fonctionne, la table a été créée !");
        
        tx.commit();
        session.close();
        HibernateUtil.getSessionFactory().close();
    }
}