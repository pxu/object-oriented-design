package patterns.behavioral.strategy.strategies;

import patterns.behavioral.strategy.elevator.Elevator;
import patterns.behavioral.strategy.elevator.Request;

import java.util.List;
import java.util.Random;

public class RandomStrategy implements IStrategy {
    @Override
    public Elevator assignElevator(List<Elevator> elevatorList, Request request) {
        Random random = new Random();
        int randomNum = random.nextInt(elevatorList.size());
        return elevatorList.get(randomNum);
    }
}
