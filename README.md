# 🧑‍🚀 Among Us - Console Edition (Java & MySQL)

¡Bienvenido a la nave! Este proyecto es un motor de juego basado en turnos inspirado en el popular juego *Among Us*, ejecutado íntegramente en la consola de comandos y respaldado por una base de datos relacional para la persistencia del estado de la partida.

Este proyecto ha sido desarrollado como una práctica avanzada de arquitectura de software, demostrando el uso de buenas prácticas, separación de responsabilidades y conexión a bases de datos.

## 🚀 Características Principales

* **Sistema de Turnos y Roles:** Gestión dinámica de tripulantes con roles específicos (`Capitán`, `Médico`, `Ingeniero` e `Impostor`), cada uno con habilidades únicas (convocar votaciones, examinar, reparar o eliminar/sabotear).
* **Persistencia de Datos Real:** Todo el estado de la nave (salas saboteadas, tareas completadas, estado vital de los jugadores) se guarda y se recupera desde una base de datos **MySQL**.
* **Lógica de Votación:** Sistema completo de votaciones de emergencia con detección de empates y expulsiones.
* **Gestión de Tareas:** Reparto equitativo de tareas y recálculo dinámico de las condiciones de victoria si un tripulante es eliminado.

## 🛠️ Stack Tecnológico y Arquitectura

* **Lenguaje:** Java (JDK 8+)
* **Base de Datos:** MySQL
* **Librerías:** JDBC (Java Database Connectivity)
* **Arquitectura:**
    * **Patrón DAO (Data Access Object):** Total separación entre la lógica del juego (`Nave`, `Main`) y las consultas SQL (`TripulanteDAO`, `SalaDAO`, `TareaDAO`).
    * **POO Avanzada:** Uso extensivo de clases abstractas, interfaces (`Trabajable`, `Saboteable`, `Votable`), herencia y polimorfismo.
    * **Patrón Singleton:** Implementado en la clase `DBUtil` para garantizar una única conexión eficiente a la base de datos.

## ⚙️ Instalación y Configuración

1. **Clonar el repositorio:**
   ```bash
   git clone https://github.com/techNico-Escolano/among-us.git

## 👥 Autores
- Nicolás Escolano García -> Lógica del Juego, Main, Entidades e Integración
- Aitor Mateos Gamazo -> Implementación DAO, Base de Datos e Interfaces
