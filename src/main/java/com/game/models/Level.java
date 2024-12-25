package main.java.com.game.models;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Level {

    private List<List<Integer>> bridges;
    private ArrayList<Bridge> bridge_objs = new ArrayList<Bridge>();
    private Color bridgeColor;

    private List<List<Integer>> ladders;
    private ArrayList<Ladder> ladder_objs = new ArrayList<Ladder>();

    private List<List<Integer>> hammers;
    private ArrayList<Hammer> hammer_objs = new ArrayList<Hammer>();

    private List<Integer> target;
    Rectangle targetRect = new Rectangle();

    Group root;

    private Level(Builder builder) {
        this.root = builder.root;
        this.bridges = builder.bridges;
        this.bridgeColor = builder.bridgeColor;
        this.ladders = builder.ladders;
        this.hammers = builder.hammers;
        this.target = builder.target;
    }

    public static class Builder {
        private List<List<Integer>> bridges = new ArrayList<>();
        private Color bridgeColor;
        private List<List<Integer>> ladders = new ArrayList<>();
        private List<List<Integer>> hammers = new ArrayList<>();
        private List<Integer> target = new ArrayList<>();
        private Group root;

        public Builder(Group root) {
            this.root = root;
        }

        public Builder setBridgeColor(Color color) {
            this.bridgeColor = color;
            return this;
        }

        public Builder addBridge(List<Integer> bridge) {
            this.bridges.add(bridge);
            return this;
        }

        public Builder addLadder(List<Integer> ladder) {
            this.ladders.add(ladder);
            return this;
        }

        public Builder addHammer(List<Integer> hammer) {
            this.hammers.add(hammer);
            return this;
        }

        public Builder setTarget(List<Integer> target) {
            this.target = target;
            return this;
        }

        public Level build() {
            return new Level(this);
        }
    }

    public ArrayList<Bridge> createBridges() {
        root.getChildren().add(targetRect);
        for (List<Integer> bridge : bridges) {
            bridge_objs.add(new Bridge(bridge.get(0), bridge.get(1), bridge.get(2), bridgeColor, root));
        }
        return bridge_objs;
    }

    public ArrayList<Ladder> createLadders() {
        for (List<Integer> ladder : ladders) {
            ladder_objs.add(new Ladder(ladder.get(0), ladder.get(1), ladder.get(2), root));
        }
        return ladder_objs;
    }

    public ArrayList<Hammer> createHammers() {
        for (List<Integer> hammer : hammers) {
            hammer_objs.add(new Hammer(hammer.get(0), hammer.get(1), root));
        }
        return hammer_objs;
    }

    public List<Integer> getTarget() {
        return target;
    }

    public void destroy() {
        for (Hammer hammer : hammer_objs) {
            hammer.clear();
        }
        for (Bridge bridge : bridge_objs) {
            bridge.clear();
        }
        for (Ladder ladder : ladder_objs) {
            ladder.clear();
        }
    }
}
