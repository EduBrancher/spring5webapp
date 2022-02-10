package guru.springframework.spring5webapp.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Publisher publisher;

    private String title;

    private String isbn;
    //private ArrayList<Author> authors;

    @ManyToMany
    @JoinTable(name = "author_books",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authorSet = new HashSet<>();


    public Set<Author> getAuthors() {
        return authorSet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthorSet(Set<Author> authorSet) {
        this.authorSet = authorSet;
    }

    public String getTitle() {
        return this.title;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    /*public ArrayList<Author> getAuthors() {
        return this.authors;
    }*/
    public Book(){

    }
    public Book(String title, String isbn){
        this.title = title;
        this.isbn = isbn;
    }

    public Book(String title, String isbn, Author[] authors){
        this(title, isbn);
        if (authors == null){
            return;
        }
       /* this.authors = new ArrayList<Author>();
        for (int i = 0; i < authors.length; i++){
            this.authors.add(authors[i]);
        }*/
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (this.id != null){
            return this.id.equals(book.id);
        }
        else{ //this id is null, check if the given object also has null id
            return book.id == null;
        }
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
