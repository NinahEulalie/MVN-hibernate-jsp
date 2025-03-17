package dao;

import model.EmployeModel;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class EmployeDAO {

    // CREATE (ajouter un employé)
    public void saveEmploye(EmployeModel employe) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(employe);
        transaction.commit();
        session.close();
    }

    // READ (récupérer un employé par ID)
    public EmployeModel getEmployeById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession(); // Ouvre une nouvelle session
        Transaction transaction = null;
        EmployeModel employe = null;

        try {
            transaction = session.beginTransaction(); // ✅ Démarre une transaction
            employe = session.get(EmployeModel.class, id);
            transaction.commit(); // ✅ Valide la transaction
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // ❌ En cas d'erreur, rollback
            }
            e.printStackTrace();
        } finally {
            session.close(); // ✅ Ferme la session après utilisation
        }

        return employe;
    }

    
    
    /**public EmployeModel getEmployeById(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        EmployeModel employe = (EmployeModel) session.get(EmployeModel.class, id);
        session.close();
        return employe;
    }*/
    
    /**
     * Get all Users
     * @return
     */
    @SuppressWarnings("unchecked")
    // READ (récupérer tous les employés)
    public List<EmployeModel> getAllEmployes() {
        Session session = null;
        Transaction transaction = null;
        List<EmployeModel> employes = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            employes = session.createQuery("from EmployeModel").list();


            transaction.commit();

            // Debugging
            if (employes == null || employes.isEmpty()) {
                System.out.println("⚠ Aucun employé trouvé en base de données !");
            } else {
                System.out.println("✅ Employés trouvés : " + employes.size());
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

        return employes;
    }



    // UPDATE (modifier un employé)
    public void updateEmploye(EmployeModel employe) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(employe);
        transaction.commit();
        session.close();
    }

    // DELETE (supprimer un employé)
    public void deleteEmploye(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        EmployeModel employe = (EmployeModel) session.get(EmployeModel.class, id);
        if (employe != null) {
            session.delete(employe);
        }
        transaction.commit();
        session.close();
    }
}