# boardgame_AI
A program that writes LISP code to play a board game.

The computer is given a turn-based game. It is placed on a 50x50 board with red, green, and gray tiles.
The red tiles hurt you, the green tiles heal you, and the gray tiles do nothing. The computer can give code for
its piece to run each turn. The goal of the game is to get the highest health.
# Commands

The program has several commands in a LISP-y format it can use to control its player.
It can use commands to interact with its environment such as
MOVE_UP, MOVE_DOWN, MOVE_LEFT, and MOVE_RIGHT.

It also as commands to get analyze its environment, such as
GET_TILE_HERE, GET_TILE_UP, GET_TILE_DOWN, GET_TILE_LEFT, 
and GET_TILE_RIGHT.

It can use control flow commands such as IF, IF_NOT, WHILE_NOT,
and composite commands.

It also has constants for comparison such as GREEN, RED, and GRAY.

# Playing the Game

When run, the program iterates through possible commands it could give to the board piece to run.
It tests each set of commands several times to find which one performs best. When it finishes testing
the first hundred thousand possible commands it could run, it prints out the best code snippet it found,
and displays an example game.

The following is an example output of the program:

```
(composite
	(composite
		MOVE_UP
		MOVE_DOWN)
	(WHILE GREEN != GET_TILE_HERE
		MOVE_RIGHT))
```

Which results in the following gameplay:

![Step1](https://github.com/mattBoros/boardgame_AI/blob/master/step_1.png?raw=true)


![Step2](https://github.com/mattBoros/boardgame_AI/blob/master/step_2.png?raw=true)

In one step, the board piece moves up and then back down, and then moves right as long as the current tile is not green. When it reaches a green tile, it stops.
