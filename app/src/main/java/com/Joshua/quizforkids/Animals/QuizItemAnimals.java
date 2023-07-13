package com.Joshua.quizforkids.Animals;

public class QuizItemAnimals {
    private String animalName;
    private String resImageName;

    public QuizItemAnimals(String name, String resImageName){
        this.animalName = name;
        this.resImageName = resImageName;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public String getResImageName() {
        return resImageName;
    }

    public void setResImageName(String resImageName) {
        this.resImageName = resImageName;
    }

}
