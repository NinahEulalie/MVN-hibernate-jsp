package dao;

import model.AffecterModel;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class AffecterDAO {
	
	// CREATE (ajouter une affectation)
    public void saveAffectation(AffecterModel affectation) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(affectation);
        transaction.commit();
        session.close();
    }

    // READ (récupérer une affectation par ID)
    public AffecterModel getAffectationById(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        AffecterModel affectation = (AffecterModel) session.get(AffecterModel.class, id);
        session.close();
        return affectation;
    }
    
    /**
     * Get all Users
     * @return
     */
    @SuppressWarnings("unchecked")
    // READ (récupérer toutes les affectations)
    public List<AffecterModel> getAllAffectations() {
        Session session = null;
        Transaction transaction = null;
        List<AffecterModel> affectations = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            affectations = session.createQuery("from AffecterModel").list();


            transaction.commit();

            // Debugging
            if (affectations == null || affectations.isEmpty()) {
                System.out.println("⚠ Aucune affectation trouvé en base de données !");
            } else {
                System.out.println("✅ Affectations trouvées : " + affectations.size());
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();  // ✅ Fermer la session ici
            }
        }

        return affectations;
    }



    // UPDATE (modifier une affectation)
    public void updateAffectation(AffecterModel affectation) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(affectation);
        transaction.commit();
        session.close();
    }

    // DELETE (supprimer un lieu)
    public void deleteAffectation(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        AffecterModel affectation = (AffecterModel) session.get(AffecterModel.class, id);
        if (affectation != null) {
            session.delete(affectation);
        }
        transaction.commit();
        session.close();
    }

}