package game;

import game.board.BoardType;
import game.board.HexBoard;
import game.board.IBoardSupplier;
import game.board.SquareBoard;
import game.organism.animals.*;
import game.organism.plants.*;
import ui.AbilityStatus;

import java.awt.*;

public class WorldFactory {
    public static World createWorld(int width, int height, AbilityStatus abilityStatus, BoardType boardType) {
        Player player = new Player(new Point(1, 1), abilityStatus);
        IBoardSupplier boardSupplier = boardType == BoardType.SQUARE ? new SquareBoard(width, height) : new HexBoard(width, height);
        World world = new World(width, height, boardSupplier, player);
        world.getOrganisms().spawnOrganism(player);
        world.getOrganisms().spawnOrganism(new Fox(new Point(5, 4)));
        world.getOrganisms().spawnOrganism(new Fox(new Point(7, 8)));
        world.getOrganisms().spawnOrganism(new Fox(new Point(2, 1)));
        world.getOrganisms().spawnOrganism(new Fox(new Point(10, 5)));
        world.getOrganisms().spawnOrganism(new Wolf(new Point(10, 2)));
        world.getOrganisms().spawnOrganism(new Wolf(new Point(18, 12)));
        world.getOrganisms().spawnOrganism(new Sheep(new Point(1, 1)));
        world.getOrganisms().spawnOrganism(new Sheep(new Point(4, 3)));
        world.getOrganisms().spawnOrganism(new Sheep(new Point(7, 5)));
        world.getOrganisms().spawnOrganism(new Sheep(new Point(7, 2)));
        world.getOrganisms().spawnOrganism(new Antelope(new Point(8, 9)));
        world.getOrganisms().spawnOrganism(new Antelope(new Point(15, 3)));
        world.getOrganisms().spawnOrganism(new Antelope(new Point(2, 3)));
        world.getOrganisms().spawnOrganism(new Turtle(new Point(1, 5)));
        world.getOrganisms().spawnOrganism(new Turtle(new Point(4, 10)));
        world.getOrganisms().spawnOrganism(new Turtle(new Point(6, 10)));
        world.getOrganisms().spawnOrganism(new Grass(new Point(1, 9)));
        world.getOrganisms().spawnOrganism(new Grass(new Point(2, 2)));
        world.getOrganisms().spawnOrganism(new Grass(new Point(2, 5)));
        world.getOrganisms().spawnOrganism(new Grass(new Point(3, 5)));
        world.getOrganisms().spawnOrganism(new Grass(new Point(4, 5)));
        world.getOrganisms().spawnOrganism(new Dandelion(new Point(10, 15)));
        world.getOrganisms().spawnOrganism(new Guarana(new Point(4, 13)));
        world.getOrganisms().spawnOrganism(new Guarana(new Point(13, 2)));
        world.getOrganisms().spawnOrganism(new Belladonna(new Point(15, 15)));
        world.getOrganisms().spawnOrganism(new Belladonna(new Point(8, 1)));
        world.getOrganisms().spawnOrganism(new Belladonna(new Point(4, 8)));
        world.getOrganisms().spawnOrganism(new Belladonna(new Point(10, 5)));
        world.getOrganisms().spawnOrganism(new SosnowskyWeed(new Point(3, 8)));
        world.getOrganisms().spawnOrganism(new SosnowskyWeed(new Point(9, 2)));
        return world;
    }
}
