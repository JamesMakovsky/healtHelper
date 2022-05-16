package training;

import java.util.*;

import training.exercise.*;
import training.exercise.inventory.*;

import serializer.Serializer;

import base.InitializeInterface;

/*
	Training:
	ID - уникальный идентификатор
	String name - название
	String description - описание
	enum TrainingStage - [0, 1, 2] - этап тренировки: разминка, основная часть, заминка
	Boolean isInventoryUsed - флаг использования инвентаря в тренировке
	ArrayList<ArrayList<Exercise>> exercises - список списков упражнений, используемых в тренировке
*/


public class Training implements InitializeInterface<Training> {
	// Уникальный номер тренировки
	private Integer ID;
	public void setID(Integer ID) { this.ID = ID; }
	public Integer getID() { return ID; }

	// Название тренировки
	private String name;
	public void setName(String name) { this.name = name; }
	public String getName() { return name; }

	// Описание тренировки
	private String description;
	public void setDescription(String description) { this.description = description; }
	public String getDescription() { return description; }
	

	/* 
		Список упражнений:
		Упражнения делятся на три группы [TrainingStage]: 
		0. Разминка				WARMUP_EXERCISE
		1. Основные упражнения 	MAIN_EXERCISE
		2. Заминка 				COOLDOWN_EXERCISE
	*/
	public static final int WARMUP_EXERCISE = 0;
	public static final int MAIN_EXERCISE = 1;
	public static final int COOLDOWN_EXERCISE = 2;

	// Список упражнений
	private ArrayList<Exercise> exercises;
	public void setExercises(ArrayList<Exercise> exercises) { this.exercises = exercises; }
	public ArrayList<Exercise> getExercises() { return exercises; }


	// Добавление упражнений в тренировку
	private void addExerciseHidden(Exercise ex) {
		exercises.add(ex);
		calculateWholeTrainingTime();
	}

	// Добавление в список упражнений нового по экземпляру упражнения
	public void addExercise(Exercise ex) {
		addExerciseHidden(ex);
	}

	public void addExercise(Serializer serializer, Integer ID, Integer times, Integer apr) {
		Exercise ex = new Exercise(serializer, ID);
		ex.setTimes(times);
		ex.setApproaches(apr);
		addExerciseHidden(ex);
	}

	// Добавление в список упражнений нового (без инвентаря)
	public void addExercise(Serializer serializer, String name, String description, ArrayList<Integer> bodyPartIDList, Integer apr, Integer tms) {
		Exercise ex = new Exercise(serializer, name, description, bodyPartIDList, apr, tms);
		addExerciseHidden(ex);
	}

	// Удаление упражнения
	public void deleteExercise(int index) {
		if (index < exercises.size()) {
			this.exercises.remove(index);
		}
	}

	// interface method implementation
	public void initiateByID(Serializer serializer, Integer ID) {
		try {
			Training training = (Training)serializer.createObjectByID(Training.class, ID);
			customClone(training);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public void initiateFromJson(Serializer serializer, String json) {
		try {
			Training training = (Training)serializer.createObjectFromJSON(Training.class, json);
			customClone(training);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public void customClone(Training clone) {
		this.ID = clone.getID();
		this.name = clone.getName();
		this.description = clone.getDescription();
		this.exercises = clone.getExercises();
	}

	private void InitializeExerciseArray() {
		exercises = new ArrayList<Exercise>();
	}
	
	// default constructor
	public Training() {
		InitializeExerciseArray();
	}

	// default constructor
	public Training(String name, String description) {
		this.name = name;
		this.description = description;
		InitializeExerciseArray();
	}

	public Training(Serializer serializer, Integer ID) {
		initiateByID(serializer, ID);
	}

	// Время полной тренировки
	// private (???) wholeTrainingTime;
	private void calculateWholeTrainingTime() { }
}