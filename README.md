# Dragolandia

## Introducción
**Dragolandia** es una aplicación de aventura medieval desarrollada en Java que simula batallas épicas por el control de un bosque mágico. Utilizando tecnologías como **Maven** e **Hibernate**, el proyecto implementa un sistema de persistencia completo con MySQL.

### Contexto del Juego

En el mundo de Dragolandia, un grupo de magos valientes se enfrenta a hordas de monstruos que han invadido un bosque sagrado. Los magos, con sus poderosos hechizos, deben eliminar a todos los monstruos y derrotar al monstruo jefe para reclamar el bosque. Un dragón protector ayuda en la batalla atacando al líder enemigo.

### Características Principales

- **Sistema de combate por turnos**: Magos lanzan hechizos, monstruos atacan y el dragón exhala fuego.
- **Gestión de vida**: Los personajes muertos son eliminados automáticamente del combate.
- **Persistencia de datos**: Todas las entidades se almacenan en una base de datos MySQL mediante Hibernate.
- **Patrón MVC**: Arquitectura organizada en Modelo-Vista-Controlador.
- **CRUD completo**: Operaciones de creación, lectura, actualización y eliminación para todas las entidades.


## Análisis
### Diagrama de clases

```mermaid
---
config:
  look: classic
  theme: mc
---
classDiagram
    direction TB

    class Mago {
        int id
        String nombre
        int vida
        int nivelMagia
        List~MagoHechizo~ magoHechizos
        +añadirConjuro(Hechizo)
        +lanzarHechizo(Monstruo)
        +lanzarHechizo(Monstruo, Hechizo)
    }

    class Monstruo {
        int id
        String nombre
        int vida
        TipoMonstruo tipo
        int fuerza
        +atacar(Mago)
    }

    class TipoMonstruo {
        <<enumeration>>
        OGRO
        TROLL
        ESPECTRO
        +random()
    }

    class Dragon {
        int id
        String nombre
        int intensidadFuego
        int resistencia
        Bosque bosque
        +exhalar(Monstruo)
        +atacar(Monstruo)
    }

    class Bosque {
        int id
        String nombre
        int nivelPeligro
        Monstruo monstruoJefe
        List~Monstruo~ monstruos
        +addMonstruo(Monstruo)
        +cambiarJefe(Monstruo)
        +mostrarJefe()
    }

    class Hechizo {
        int id
        String nombre
        +efecto(Monstruo)
        +efecto(List~Monstruo~)
    }

    class BolaDeFuego {
        +efecto(List~Monstruo~)
    }
    class BolaDeNieve {
        +efecto(Monstruo)
    }
    class Rayo {
        +efecto(Monstruo)
    }
    class Meteorito {
        +efecto(List~Monstruo~)
    }

    class MagoHechizo {
        int id
        Mago mago
        Hechizo hechizo
    }

    class ControladorPrincipal {
        -ControladorMago contrMago
        -ControladorMonstruo contrMonstruo
        -ControladorBosque contrBosque
        -ControladorDragon contrDragon
        -ControladorHechizos contrHechizos
        +crearPartida(...)
        +usarHechizo(String, List~Monstruo~)
        +atacaMago()
        +atacaMons()
        +juegoActivo() : boolean
        +limpiarMuertos()
        +mostrarEstado()
        +limpiarBaseDatos()
    }

    class ControladorMago {
        -Mago mago
        +crearMago(String, List~Integer~)
        +gardarMago()
        +eliminarMago(Mago)
        +actualizarMago(Mago)
        +listarMagos() : List~Mago~
        +buscarMago(int) : Mago
    }

    class ControladorMonstruo {
        -Monstruo monstruo
        +crearMonstruo(String)
        +newMonstruo(String) : Monstruo
        +gardarMonstruo()
        +eliminarMonstruo(Monstruo)
        +actualizarMonstruo(Monstruo)
        +listarMonstruos() : List~Monstruo~
        +buscarMonstruo(int) : Monstruo
    }

    class ControladorBosque {
        -Bosque bosque
        +crearBosque(String, Monstruo, List~Monstruo~)
        +gardarBosque(Bosque)
        +eliminarBosque(Bosque)
        +actualizarBosque(Bosque)
        +listarBosques() : List~Bosque~
        +buscarBosque(int) : Bosque
        +setMonstruoJefe(Monstruo)
        +setMonstruosBosque(List~Monstruo~)
        +getBosque() : Bosque
    }

    class ControladorDragon {
        -Dragon dragon
        +crearDragon(String, Bosque) : Dragon
        +gardarDragon(Dragon)
        +eliminarDragon(Dragon)
        +actualizarDragon(Dragon)
        +listarDragones() : List~Dragon~
        +buscarDragon(int) : Dragon
        +getDragon() : Dragon
    }

    class ControladorHechizos {
        +gardarHechizos()
        +getHechizo(int) : Hechizo
        +listarHechizos() : List~Hechizo~
        +buscarHechizo(int) : Hechizo
        +eliminarHechizo(Hechizo)
        +actualizarHechizo(Hechizo)
    }

    class HibernateSingleton {
        +getInstance() : HibernateSingleton
        +getEntityManager() : EntityManager
    }

    Hechizo <|-- BolaDeFuego
    Hechizo <|-- BolaDeNieve
    Hechizo <|-- Rayo
    Hechizo <|-- Meteorito

    Mago "1" o-- "many" MagoHechizo
    Hechizo "1" o-- "many" MagoHechizo

    Bosque "1" -- "1" Monstruo : monstruoJefe
    Bosque "1" -- "many" Monstruo : monstruos
    Dragon "1" -- "1" Bosque

    Monstruo --> TipoMonstruo

    ControladorPrincipal ..> ControladorMago
    ControladorPrincipal ..> ControladorMonstruo
    ControladorPrincipal ..> ControladorBosque
    ControladorPrincipal ..> ControladorDragon
    ControladorPrincipal ..> ControladorHechizos

    ControladorMago ..> Mago
    ControladorMonstruo ..> Monstruo
    ControladorBosque ..> Bosque
    ControladorDragon ..> Dragon
    ControladorHechizos ..> Hechizo

    HibernateSingleton
```

## Diseño
### Diagrama entidad relación

```mermaid
---
config:
  look: classic
  theme: mc
---
erDiagram
    BOSQUES ||--|| MONSTRUOS : tiene_jefe
    BOSQUES ||--o{ BOSQUES_MONSTRUOS : contiene
    MONSTRUOS ||--o{ BOSQUES_MONSTRUOS : habita_en
    DRAGONES ||--|| BOSQUES : protege
    MAGOS ||--o{ MAGO_HECHIZO : tiene
    HECHIZOS ||--o{ MAGO_HECHIZO : conocido_por
    

    MAGOS {
        int id PK
        string nombre
        int vida
        int nivelMagia
    }

    MONSTRUOS {
        int id PK
        string nombre
        int vida
        enum tipo
        int fuerza
    }

    BOSQUES {
        int id PK
        string nombre
        int nivelPeligro
        int monstruoJefe_id FK
    }

    DRAGONES {
        int id PK
        string nombre
        int intensidadFuego
        int resistencia
        int bosque_id FK
    }

    HECHIZOS {
        int id PK
        string nombre
        string dtype
    }

    MAGO_HECHIZO {
        int id PK
        int mago_id FK
        int hechizo_id FK
    }

    BOSQUES_MONSTRUOS {
        int bosque_id FK
        int monstruo_id FK
    }

    BOLA_DE_FUEGO {
        int id PK
    }

    BOLA_DE_NIEVE {
        int id PK
    }

    RAYO {
        int id PK
    }

    METEORITO {
        int id PK
    }

    HECHIZOS ||--o| BOLA_DE_FUEGO : hereda
    HECHIZOS ||--o| BOLA_DE_NIEVE : hereda
    HECHIZOS ||--o| RAYO : hereda
    HECHIZOS ||--o| METEORITO : hereda
```