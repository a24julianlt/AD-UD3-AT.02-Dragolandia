# Dragolandia

## Introducción
Tarea donde, usando Java, Maven e Hibernate, hacemos una pelea entre un mago y un monstruo para hacerse con el control del bosque.

## Análisis
### Diagrama de clases




## Diseño
### Diagrama entidad relación

---
config:
  look: classic
  theme: mc
---
classDiagram
direction TB
    class Mago {
	    -String nombre
	    -int vida
	    -int nivelMagia
	    +lanzarHechizo(Monstruo monstruo)
    }

    class TipoMonstruo {
      <<enumeration>>
	    ogro
	    troll
	    espectros
	    +random()
    }

    class Monstruo {
	    -String nombre
	    -int vida
	    -TipoMonstruo tipo
	    -int fuerza
	    +atacar(Mago mago)
    }

    class Bosque {
	    -String nombre
	    -int nivelPeligro
	    -Monstruo monstruoJefe
	    +cambiarJefe(Monstruo monstruo)
    }

    class ControladorMago{
      -Mago mago
    }

    class ControladorMonstruo {
      -Monstruo monstruo
    }

    class ControladorBosque {
      -Bosque bosque
      -ControladorMago contrMago
      -ControladorMonstruo contrMonstruo
      +finPartida() : boolean
      +atacaMago()
      +atacaMons()
      +ganaMago() : boolean
    }

    ControladorMago --> Mago
    ControladorMonstruo --> Monstruo
    ControladorBosque --> ControladorMonstruo
    ControladorBosque --> ControladorMago
    ControladorBosque --> Bosque

    Monstruo --> TipoMonstruo
    