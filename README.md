# Java-Chess
A simple Java chess program, currently do not looking for a GUI just terminal project. Practice OOP design. 

### What this project is aiming for acheive
This project is my first project plan, intension is to get a job. The aim can be broken into following part
1. **Understanding and apply OOP design pattern**. Chess is not a complicate game, but certainly can contain lot's of room for designing those classes. 
2. **Programming by contract**. I will aim for a well documentated design of everything I need to implement, build a solid ground for future GUI extension. 
3. **Robust Programming**. I would like to make this project maintainable in the future. One approach would be using **test driven** development, and lots of refactoring in the process. 
4. **Documentation**. I would want to learn to document my project, first thing I did is to make format like my school assignment, in a certain way, I do felt it's well documented. 

### 0. Verion Log
- 1.0 Friday 21/10/2022 Project Launch

### 1. Pharase 1
##### Key Objective
- Load Java Project
- Design for the class Board
    - Contains information of the whole board. Should contain a key API for the future GUI extension. 
    - Able to move pieces, simulate the game. Should contain a key API for the future GUI extension. 
    - Move Class represent a move.
- Design for the class Piece
    - Pawn
    - Knight
    - Bishop
    - Castle
    - King
    - Queen

Pharase 1 completed.

### 2. Pharase 2
- Add several chess feature
    - Castle on king side, queen side
    - En passant
    - Pawn promote
    - Illegel move if will get check mated
    - Stale mate
    - Wining condition
- Add testing for the chess board
- Add an output format, for moves and board
- Move general ultility into boardUlty class.
- Add Player and Move class, to implenmt all above, 
- Transit the single board, to board only represent a state. 