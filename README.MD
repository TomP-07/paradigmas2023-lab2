## Laboratorio 2 :Orientacion a Objetos

### Introduccion
En este laboratorio  implementamos un lector automático de feeds, como aplicación de consola.

## Desarrollo

Basicamente lo que hicimos fue  obtener los url concretos de los distintos feeds del archivo, en la carpeta suscription.json. Luego realizamos la consulta “http request” al servidor del feed , donde se encarga de realizar efectivamente el pedido de feed al servidor de noticias y si todo sale exitosamente me devolvera un feed RSS
Luego de esto parseamos RSS y REDITT , para obtener el contenido de los diferentes artículos ,donde solo extrajimos  el title, description,pubDate  y el link,que finalmente mostramos en pantalla  ,en ambos casos utilizamos librerias ,para RSS utilizamos la librera org.w3c.dom.* y para REDITT utilizamos org.json , para hacer ambos parseos utilizamos  tambien los archivos de feed.java que modela la lista de articulos de un determinado feed y article.java que modela el contenido de un articulo ,esto nos facilito mucho el parseo de ambos.

Una vez realizado el parseo empezamos a realizar la categorización de las entidades nombradas. Para esto, detectamos las entidades nombradas con las heurísticas provistas por la cátedra más una nueva heurística que agregamos. Modificamos el main para poder ejecutar el programa con argumentos y seleccionar alguna de las 3 heurísticas dadas.

Una vez determinadas las entidades nombradas tomamos dos caminos disjuntos para la categorización:

- Forma 1:

En esta implementación seguimos la especificación a pie de letra dada por la cátedra, que fue utilizar interfaces para poder hacer herencia múltiple sobre entidades nombradas y sus categorías. Pese a que en nuestra opinión esto es absolutamente innecesario, genera un código inmantenible y solo genera confusión, lo implementamos de este modo. Básicamente las entidades nombradas son clases con herencia simple, luego hicimos interfaces que representan las distintas categorías tales como fútbol, política, deportes, etc. Dadas estas clases e interfaces hicimos la herencia múltiple especificada por el enunciado para crear clases de la forma EventoconCine,EventoconMusica, etc. Las cuales son entidades nombradas con sus respectivas categorías. Dichas clases compuestas las instanciamos de manera “representativa” cuando computamos las entidades nombradas en Article, donde se provee un prototipo de como seria el procedimiento para la creación de dichas entidades nombradas y como se popularian con datos. Justamente como toda esta implementación resultó ser una muy mala práctica en nuestra opinión hicimos una segunda forma, que probablemente se acerca más a una implementación real.

- Forma 2:

En esta segunda implementación lo que hicimos fue hacer a las entidades nombradas subclase de NamedEntity, dichas subclases son de la forma Persona, Compania, País, etc. Luego tuvimos otra jerarquía como el enunciado especifica que son subclases de Subject de la forma de Cultura, Política, Deportes, etc. ¿Cómo hicimos la unión entre ambos conjuntos de clases? Simplemente hicimos que las entidades nombradas tengan un campo que representa la categoría asociada, luego si la entidad nombrada es Person y su subject es Politics, estamos hablando de una Persona relacionada a la categoría Política. El principal y fundamental beneficio de esto es la flexibilidad y mantenibilidad del código, porque podemos representar las relaciones fácilmente sin mucho código extra, a su vez también obtuvimos ciertos beneficios de eficiencia dado que cada clase de categoría tiene las entidades nombradas asociadas a dicha categoría y los artículos tienen el listado de categorías presentes en dicho artículo, haciendo que ciertas operaciones sean poco costosas.

### Conclusiones:
En conclusión respecto al laboratorio, estuvo interesante el hecho de implementar los parser y el request de HTTP, aunque tal vez faltó aprovecharse del modelo código-datos que los objetos proveen, dado que por ejemplo los parser fueron trivialmente implementados como una función que tranquilamente pudo haber sido estática y no haber necesitado de objetos, hubiese estado bueno de que fuera muy útil implementarlo de algún modo que involucre datos y métodos en un solo objeto. Por otro lado, lo que si no nos pareció muy bien fue la especificación dada para la categorización de entidades nombradas, solo porque el objetivo sea implementar la herencia múltiple en Java no parece muy buena idea hacerla de una manera tan forzada que tiene demasiadas desventajas en nuestra opinión. Exceptuando esta última parte, el resto del laboratorio estuvo bastante bueno y sirvió para tener una buena de de orientado a objetos y Java.