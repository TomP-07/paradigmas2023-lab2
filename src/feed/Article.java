package feed;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import namedEntity.NamedEntity;
import namedEntity.clasificacion.entity.Evento;
import namedEntity.clasificacion.entity.Producto;
import namedEntity.clasificacion.entity.persona.Apellido;
import namedEntity.clasificacion.entity.persona.Persona;
import namedEntity.clasificacion.entity.persona.Titulo;
import namedEntity.clasificacion.multiple.ApellidoconFutbolero;
import namedEntity.clasificacion.multiple.EventoconCine;
import namedEntity.clasificacion.multiple.EventoconMusica;
import namedEntity.clasificacion.multiple.TituloconCine;
import namedEntity.clasificacion.tema.deportes.Futbol;
import namedEntity.clasificacion.tema.politica.Politica;
import namedEntity.heuristic.Heuristic;

/*Esta clase modela el contenido de un articulo (ie, un item en el caso del rss feed) */

public class Article {
    private String title;
    private String text;
    private Date publicationDate;
    private String link;
    private Heuristic heuristic;

    private boolean namedEntityListCalculated = false;

    private List<NamedEntity> namedEntityList = new ArrayList<NamedEntity>();

    public Article(String title, String text, Date publicationDate, String link) {
        super();
        this.title = title;
        this.text = text;
        this.publicationDate = publicationDate;
        this.link = link;
    }

    public String getHeuristic() {
        if (this.heuristic != null) {
            return this.heuristic.getHeuristicName();
        } else {
            return "Sin Heuristica";
        }
    }

    public void setHeuristic(Heuristic heuristic) {
        this.heuristic = heuristic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Article [title=" + title + ", text=" + text + ", publicationDate=" + publicationDate + ", link=" + link
                + "]";
    }


    public NamedEntity getNamedEntity(String namedEntity) {
        for (NamedEntity n : namedEntityList) {
            if (n.getName().compareTo(namedEntity) == 0) {
                return n;
            }
        }
        return null;
    }

    public void computeNamedEntities(Heuristic h) {
        // al computar las entidades nombradas lo habilitamos
        this.namedEntityListCalculated = true;
        this.heuristic = h;
        String text = this.getTitle() + " " + this.getText();

        String charsToRemove = ".,;:()'!?\n";
        for (char c : charsToRemove.toCharArray()) {
            text = text.replace(String.valueOf(c), "");
        }

        for (String word : text.split(" ")) {
            if (h.isEntity(word)) {
                NamedEntity ne = this.getNamedEntity(word);
                if (ne == null) {
                    String category = h.getCategory(word); //politica pais
                    String entity = "";//persona org 
                    if (category.equalsIgnoreCase("futbol") && entity.equalsIgnoreCase("apellido")) {
                        //intancio la clase apellidoconFutbolero
                        this.namedEntityList.add(new ApellidoconFutbolero(word, category, 1, word, word, word));
                    }
                    if (category.equalsIgnoreCase("evento") &&  entity.equalsIgnoreCase("cine")) {
                        this.namedEntityList.add(new EventoconCine(word, category, 1, category, word, word));   
                    }
                    //ver tema de la frequencia
                    if (category.equalsIgnoreCase("titulo") &&  entity.equalsIgnoreCase("cine")){
                        this.namedEntityList.add(new TituloconCine(word, category, 1, word, word, word));

                    } 
                    if (category.equalsIgnoreCase("evento") &&  entity.equalsIgnoreCase("musica")) {
                        this.namedEntityList.add(new EventoconMusica(word, category, 1, category, word, word));
                    }

                    // if(category!="N/C"){
					// 	category.increment()
                    // }
                    this.namedEntityList.add(new NamedEntity(word, category, 1));
                } else {
                    ne.incFrequency();
                }
            }
        }
    }


    public void prettyPrint() {
        System.out.println("**********************************************************************************************");
        System.out.println("Title: " + this.getTitle());
        System.out.println("Publication Date: " + this.getPublicationDate());
        System.out.println("Link: " + this.getLink());
        System.out.println("Text: " + this.getText());
        // si estan calculadas la entidades, printeamos cada nombre
        if (this.namedEntityListCalculated) {
            System.out.println("Heuristica: " + this.getHeuristic());
            System.out.println("Entidades nombradas:");
            for (NamedEntity entity : this.namedEntityList)
                entity.prettyPrint();
        }
        System.out.println("**********************************************************************************************");

    }


}



