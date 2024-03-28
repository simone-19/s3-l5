package org.example;

public class UtenteDAO {
    private final EntityManager em;

    public UtenteDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Utente utente) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(utente);
        transaction.commit();
        System.out.println("Utente " + utente.getNome() + " aggiunto correttamente al database!");
    }
    public Utente findById(long id) {
        return em.find(Utente.class,id);
    }
    public void findByIdAndDelete(long id) {
        Utente found = this.findById(id);

        if (found != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(found);
            transaction.commit();
            System.out.println("Utente " + found.getNome() + " rimosso correttamente!");
        } else {
            System.out.println("Utente con id " + id + " non trovato");
        }
    }
}