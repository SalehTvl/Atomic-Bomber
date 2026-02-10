# Atomic Bomber (Java Arcade Clone)

![Java](https://img.shields.io/badge/Java-17%2B-orange)
![JavaFX](https://img.shields.io/badge/GUI-JavaFX-blue)
![Data](https://img.shields.io/badge/Data-Gson%2FJSON-yellow)

## ✈️ Overview

**Atomic Bomber** is a retro-style 2D arcade game simulation developed as part of the Advanced Programming course. The game recreates the thrill of classic bomber games where the player pilots a jet, dropping bombs to destroy city structures and enemies while dodging anti-aircraft fire and other aerial threats.

The project is built entirely in **Java**, utilizing **JavaFX** for the graphical interface and rendering engine, and implements **Google Gson** for data persistence (user profiles and high scores).

## ✨ Key Features

*   **Dynamic Gameplay:** Smooth flight mechanics and projectile physics handled via a custom game loop (`AnimationTimer`).
*   **Procedural City Generation:** The terrain and buildings are randomly generated for each level, ensuring no two runs are the same.
*   **Enemy AI:** Includes various enemy types such as Tanks (ground-to-air), Jets (air-to-air), and bunkers.
*   **Collision Detection:** Precise hitbox calculation for bombs, planes, and buildings using JavaFX `Bounds`.
*   **Power-ups & Bonuses:** Collectible items to upgrade weaponry (e.g., Cluster Bombs) or restore health (Nuke ability).
*   **Data Persistence:** Player progress, settings, and high scores are serialized and saved as JSON files using the **Gson** library.
*   **Responsive UI:** Interactive menus, pause screens, and real-time HUD (Heads-Up Display) for ammo and health.

## 🛠️ Tech Stack & Architecture

*   **Language:** Java (JDK 17 or higher)
*   **Frontend & Rendering:** JavaFX (Canvas & Scene Graph)
*   **Data Handling:** Google Gson (for JSON serialization/deserialization)
*   **Design Pattern:**
    *   **MVC (Model-View-Controller):** Separates game logic (Model) from the rendering (View) and input handling (Controller).
    *   **Game Loop Pattern:** Uses `AnimationTimer` for consistent frame updates (60 FPS).
    *   **Factory Pattern:** (Optional - if used) For spawning different types of enemies or entities.

## 🚀 Installation & Run

1.  **Clone the repository:**
```bash
git clone https://github.com/SalehTvl/Atomic-Bomber.git
```

2. **Open in IDE:**
Open the project folder in IntelliJ IDEA (recommended) or Eclipse.

3. **Dependencies:**
Ensure your project (Maven/Gradle) downloads the required libraries:
*   org.openjfx:javafx-controls
*   org.openjfx:javafx-fxml
*   org.openjfx:javafx-media
*   com.google.code.gson:gson

4. **Run the Game:**
Find the ```AtomicBomber.java``` file in ```src/main/java/org/example/``` and run it.

