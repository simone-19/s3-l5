package org.example;

public class PrestitoDAO {
    private final EntityManager em;

    public PrestitoDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Prestito prestito) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(prestito);
        transaction.commit();
        System.out.println("Prestito di" + prestito.getElementoPrestato().getTitolo()+ " aggiunto correttamente al database!");
    }

    public Prestito findById(long id) {
        return em.find(Prestito.class,id);
    }


    public void findByIdAndDelete(long id) {
        Prestito found = this.findById(id);

        if (found != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(found);
            transaction.commit();
            System.out.println("Prestito di " + found.getElementoPrestato().getTitolo() + " rimosso correttamente!");
        } else {
            System.out.println("Prestito con id " + id + " non trovato");
        }

    }

    public List<ElementoBibliotecario> getPrestatiByTessera(long numeroTessera){
        TypedQuery<ElementoBibliotecario> findByTessera = em.createNamedQuery("Ricerca_prestiti_in_corso",ElementoBibliotecario.class);
        findByTessera.setParameter("numTessera",numeroTessera);
        return findByTessera.getResultList();
    }

    public List<Prestito> getScadutiNonRestituiti(){
        TypedQuery<Prestito> findScadutiNonRestituiti = em.createNamedQuery("Ricerca_prestiti_scaduti_e_non_restituiti",Prestito.class);
        return findScadutiNonRestituiti.getResultList();
    }
}
