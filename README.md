# Dragolandia

## Introducción
Tarea donde, usando Java, Maven e Hibernate, hacemos una pelea entre un mago y un monstruo para hacerse con el control del bosque.

## Análisis
### Diagrama de clases
---
config:
  look: classic
  theme: mc
---
classDiagram
    direction TB

    class ControladorPrincipal {
      -ControladorBosque contrBosque
      -ControladorMonstruo contrMonstruo
      -ControladorMago contrMago
      -ControladorDragon contrDragon
      -ControladorHechizos contrHechizos

      +crearPartida(String nombreMago, List<Integer> hechizos, String nombreMons, List<String> monstruos, String nombreBosque, String nombreDragon)
      +usarHechizo(String hechizo, List<Monstruo> monstruos)
      +atacaMago()
      +atacaMons()
      +ganaMago() : boolean
      +finPartida() : boolean

      +gardarMago()
      +gardarMonstruo()
      +gardarBosque()
      +gardarDragon()
    }


    class ControladorMago {
      -Mago mago
      -ControladorHechizos contrHechizos
      -HibernateSingleton database

      +ControladorMago()
      +crearMago(String nombre, List<Integer> hechizos)
      +gardarMago()
    }

    class Mago {
      -int id
      -String nombre
      -int vida
      -int nivelMagia
      -List<Hechizo> conjuros

      +Mago()
      +Mago(String nombre)
      +lanzarHechizo(Monstruo monstruo)
    }


    class ControladorMonstruo {
        -Monstruo monstruo
        -HibernateSingleton database

        +ControladorMonstruo()
        +crearMonstruo(String nombre)
        +newMonstruo(String nombre)
        +gardarMonstruo()
    }

    class Monstruo {
      -int id
      -String nombre
      -int vida
      -TipoMonstruo tipo
      -int fuerza

      +Monstruo()
      +Monstruo(String nombre)
      +atacar(Mago mago)
    }

    class TipoMonstruo {
        <<enumeration>>
        ogro
        troll
        espectros
        +random()
    }

    
    class ControladorBosque {
      -Bosque bosque
      -HibernateSingleton database
      
      +finPartida() : boolean
      +atacaMago()
      +atacaMons()
      +ganaMago() : boolean
    }

    class Bosque {
        -String nombre
        -int nivelPeligro
        -Monstruo monstruoJefe
        +cambiarJefe(Monstruo monstruo)
    }

    

    

    ControladorPrincipal --> ControladorMago
    ControladorPrincipal --> ControladorBosque
    ControladorPrincipal --> ControladorMonstruo


    ControladorMago --> Mago
    ControladorMonstruo --> Monstruo
    ControladorBosque --> Bosque
    Monstruo --> TipoMonstruo
    


## Diseño
### Diagrama entidad relación
