package org.example.entities;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("catalogo_bibliotecario");
    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        UtenteDAO ud = new UtenteDAO(em);
        ElementoBibliotecarioDAO ld = new ElementoBibliotecarioDAO(em);
        PrestitoDAO pd = new PrestitoDAO(em);
        Utente utente1 = new Utente("Maria","Rossa", LocalDate.now().minusYears(24),156);
        Libro libro = new Libro("46d2f498","Cadrega",1997,200,"AldoGiovanniGiacomo","Avventura");
        System.out.println("Ricerca_per_codice_ISBN");
        System.out.println(ld.findByISBN("46d2f498"));
        System.out.println("ricerca_per_anno_di_pubblicazione");
        ld.findByAnnoPubblicazione(1949).forEach(System.out::println);
        System.out.println("Ricerca_per_autore");
        ld.findByAutore("AldoGiovanniGiacomo").forEach(System.out::println);
        System.out.println("Ricerca_per_titolo");
        ld.findByTitolo("Cadrega").forEach(System.out::println);
        Prestito prestito = new Prestito(ud.findById(6),ld.findById(5),LocalDate.now());
        System.out.println("Ricerca_prestiti_in_corso");
        pd.getPrestatiByTessera(232).forEach(System.out::println);
        System.out.println("Ricerca_prestiti_scaduti_e_non_restituiti");
        pd.getScadutiNonRestituiti().forEach(System.out::println);
    }
}