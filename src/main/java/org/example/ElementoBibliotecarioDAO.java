package org.example;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_elementobibliotecario")
@NamedQuery(name = "ricercaElementoBibliotecarioPerISBN",query = "SELECT l FROM ElementoBibliotecario l WHERE l.codiceISBN = :codiceISBN")
@NamedQuery(name = "ricercaElementoBibliotecarioPerAnnoPubblicazione",query = "SELECT l FROM ElementoBibliotecario l WHERE l.annoPubblicazione = :annoPubblicazione")
@NamedQuery(name = "ricercaElementoBibliotecarioPerTitoloOparte",query = "SELECT l FROM ElementoBibliotecario l WHERE l.titolo LIKE CONCAT('%', :titolo, '%')")
public class ElementoBibliotecarioDAO {
    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true, name = "codice_isbn")
    private String codiceISBN;
    @Column(name = "titolo")
    private String titolo;
    @Column(name = "anno_pubblicazione")
    private int annoPubblicazione;
    @Column(name = "numero_pagine")
    private int numeroPagine;
    private final EntityManager em;

    public ElementoBibliotecarioDAO(EntityManager em) {
        this.em = em;
    }
    public void save(ElementoBibliotecario elementoBibliotecario) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(elementoBibliotecario);
        transaction.commit();
        System.out.println("ElementoBibliotecario" + elementoBibliotecario.getTitolo() + " aggiunto correttamente al database!");
    }

    public ElementoBibliotecario findById(long id) {
        return em.find(ElementoBibliotecario.class,id);
    }


    public void findByIdAndDelete(long id) {
        ElementoBibliotecario found = this.findById(id);

        if (found != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(found);
            transaction.commit();
            System.out.println("ElementoBibliotecario" + found.getTitolo() + "rimosso correttamente dal database!");
        } else {
            System.out.println("ElementoBibliotecario con id " + id + " non trovato");
        }


    }

    public void findByISBNAndDelete(String isbn) {
        TypedQuery<ElementoBibliotecario> findByISBN = em.createQuery("SELECT l FROM ElementoBibliotecario l WHERE l.codiceISBN = :isbn",ElementoBibliotecario.class);
        findByISBN.setParameter("isbn",isbn);
        ElementoBibliotecario found = findByISBN.getSingleResult();
        if (found != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(found);
            transaction.commit();
            System.out.println("ElementoBibliotecario " + found.getTitolo() + " rimosso correttamente dal database!");
        } else {
            System.out.println("ElementoBibliotecario con isbn " + isbn + " non trovato");
        }
    }}
