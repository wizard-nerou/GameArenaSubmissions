## JumpyShip by Finlay Smith:

- Press space to jump past the asteroids.
- Randomly generated game functions including:
    - Random star background generation on game start:
      - there are 2 layers of stars, close ones, which are the larger stars, and far ones, which appear as the very small dots of stars.
      - each star has a random size and position, but the far stars appear 2x smaller than the close ones (on average)
      - large stars (close and far) have a chance to appear as slightly blue or red. All other stars are white.
    - Random position where asteroid gap is. Can never generate too close to the edge though.
    - *All these are determined on a seed from the public setSeed() method in the SpaceArena class*
    - *If no seed is provided, it generates a random one*
- Asteroid Walls increase in speed, dependent on how many points you have.
- 

---
#### DO NOT RESIZE WINDOW IN MAIN AFTER SpaceArena CREATION, This BREAKS the game, and allows the ship to fall into the abyss below the playable area where the SpaceArena class was initially defined.
To be clear, you can have different size games, but that must be defined initially in the constructor, and should not be changed.

---
### To Run, run the main method, in the main class, or use the jar file in 'releases' part of this repository