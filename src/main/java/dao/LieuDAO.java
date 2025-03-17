package dao;

import model.LieuModel;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class LieuDAO {
	
	// CREATE (ajouter un lieu)
    public void saveLieu(LieuModel lieu) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(lieu);
        transaction.commit();
        session.close();
    }

    // READ (récupérer un lieu par ID)
    public LieuModel getLieuById(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        LieuModel lieu = (LieuModel) session.get(LieuModel.class, id);
        session.close();
        return lieu;
    }
    
    /**
     * Get all Users
     * @return
     */
    @SuppressWarnings("unchecked")
    // READ (récupérer tous les lieux)
    public List<LieuModel> getAllLieux() {
        Session session = null;
        Transaction transaction = null;
        List<LieuModel> lieux = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            lieux = session.createQuery("from LieuModel").list();


            transaction.commit();

            // Debugging
            if (lieux == null || lieux.isEmpty()) {
                System.out.println("⚠ Aucun lieu trouvé en base de données !");
            } else {
                System.out.println("✅ Lieux trouvés : " + lieux.size());
            }
        } 
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } 
        finally {
            if (session != null) {
                session.close();  // ✅ Fermer la session ici
            }
        }

        return lieux;
    }



    // UPDATE (modifier un lieu)
    public void updateLieu(LieuModel lieu) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(lieu);
        transaction.commit();
        session.close();
    }

    // DELETE (supprimer un lieu)
    public void deleteLieu(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        LieuModel lieu = (LieuModel) session.get(LieuModel.class, id);
        if (lieu != null) {
            session.delete(lieu);
        }
        transaction.commit();
        session.close();
    }

}