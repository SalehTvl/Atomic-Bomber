package org.example.Model;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import org.example.Model.GameObject.*;
import java.util.ArrayList;
import javafx.scene.shape.Rectangle;

public class Wave {

    private final int number;
    private final Game game;
    private final Pane pane;
    private final int height = 900;
    private final int width = 1570;
    private final int groundHeight = 100;
    private int remainingBpms;
    private int remainingTanks;
    private int remainingTrucks;
    private int remainingMigs;
    private int remainingBunkers;
    private int remainingBuildings;
    private int shotBombs;
    private int hitBombs;
    public ArrayList<Transition> animations = new ArrayList<>();
    public ArrayList<BPM> Bpms = new ArrayList<>();
    public ArrayList<Tank> tanks = new ArrayList<>();
    public ArrayList<Truck> trucks = new ArrayList<>();
    public ArrayList<Bunker> bunkers = new ArrayList<>();
    public ArrayList<Building> buildings = new ArrayList<>();
    public ArrayList<Rectangle> shotWeaponry = new ArrayList<>();



    public Wave(Game game, Pane pane, int number) {
        this.game = game;
        this.pane = pane;
        this.number = number;
    }

    public Game getGame() {
        return game;
    }

    public Pane getPane() {
        return pane;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getGroundHeight() {
        return groundHeight;
    }

    public int getRemainingBpms() {
        return remainingBpms;
    }

    public int getNumber() {
        return number;
    }

    public void setRemainingBpms(int remainingBpms) {
        this.remainingBpms = remainingBpms;
    }

    public int getRemainingTanks() {
        return remainingTanks;
    }

    public void setRemainingTanks(int remainingTanks) {
        this.remainingTanks = remainingTanks;
    }

    public int getRemainingTrucks() {
        return remainingTrucks;
    }

    public void setRemainingTrucks(int remainingTrucks) {
        this.remainingTrucks = remainingTrucks;
    }

    public int getRemainingMigs() {
        return remainingMigs;
    }

    public int getRemainingBunkers() {
        return remainingBunkers;
    }

    public int getRemainingBuildings() {
        return remainingBuildings;
    }

    public int getShotBombs() {
        return shotBombs;
    }

    public int getHitBombs() {
        return hitBombs;
    }

    public ArrayList<Transition> getAnimations() {
        return animations;
    }

    public ArrayList<BPM> getBpms() {
        return Bpms;
    }

    public ArrayList<Tank> getTanks() {
        return tanks;
    }

    public ArrayList<Truck> getTrucks() {
        return trucks;
    }

    public ArrayList<Bunker> getBunkers() {
        return bunkers;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public ArrayList<Rectangle> getShotWeaponry() {
        return shotWeaponry;
    }

    public void setRemainingMigs(int remainingMigs) {
        this.remainingMigs = remainingMigs;
    }

    public void setRemainingBunkers(int remainingBunkers) {
        this.remainingBunkers = remainingBunkers;
    }

    public void setRemainingBuildings(int remainingBuildings) {
        this.remainingBuildings = remainingBuildings;
    }

    public void setShotBombs(int shotBombs) {
        this.shotBombs = shotBombs;
    }

    public void setHitBombs(int hitBombs) {
        this.hitBombs = hitBombs;
    }

    public void addToAnimations(Transition animation) {
        this.animations.add(animation);
    }

    public void addToBpms(BPM Bpm) {
        this.Bpms.add(Bpm);
    }

    public void addToTanks(Tank tank) {
        this.tanks.add(tank);
    }

    public void addToTrucks(Truck truck) {
        this.trucks.add(truck);
    }

    public void addToBunkers(Bunker bunker) {
        this.bunkers.add(bunker);
    }

    public void addToBuildings(Building building) {
        this.buildings.add(building);
    }

    public void addToShotWeaponry(Rectangle weaponry) {
        this.shotWeaponry.add(weaponry);
    }
}
