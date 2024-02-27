# GameArenaSubmissions

---
## JumpyShip by Finlay Smith:

- press space to jump past the asteroids
- randomly generated game functions including:
    - random star background on game start
    - random position of hole where asteroid gap is
    - all these are determined on a seed from the public setSeed() method in the SpaceArena class
    - if no seed is provided, it generates a random one

---
#### DO NOT RESIZE WINDOW IN MAIN AFTER SpaceArena CREATION, This BREAKS the game, and allows the ship to fall into the abyss below where the SpaceArena class was initially defined. 
To be clear, you can have different size games, but that must be defined initially in the constructor, and should not be changed.

---
### To Run, run the main method, in the main class