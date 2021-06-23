package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL_ID = START_SEQ;

    public static final Meal MEAL1 = new Meal(MEAL_ID + 2, LocalDateTime.of(2021, Month.JANUARY, 22, 8, 0), "Завтрак", 550);
    public static final Meal MEAL2 = new Meal(MEAL_ID + 3, LocalDateTime.of(2021, Month.JANUARY, 22, 15, 15), "Обед", 1050);
    public static final Meal MEAL3 = new Meal(MEAL_ID + 4, LocalDateTime.of(2021, Month.JANUARY, 22, 20, 0), "Ужин", 650);
    public static final Meal MEAL4 = new Meal(MEAL_ID + 5, LocalDateTime.of(2021, Month.JANUARY, 12, 9, 30), "Завтрак", 500);
    public static final Meal MEAL5 = new Meal(MEAL_ID + 6, LocalDateTime.of(2021, Month.JANUARY, 12, 14, 30), "Обед", 1150);
    public static final Meal MEAL6 = new Meal(MEAL_ID + 7, LocalDateTime.of(2021, Month.JANUARY, 12, 19, 45), "Ужин", 200);

    public static final List<Meal> meals = Arrays.asList(MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1);

    public static Meal getNew() {
        return new Meal(null, LocalDateTime.of(2021, Month.JANUARY, 2, 13, 30), "Новый обед", 750);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(MEAL1);
        updated.setDateTime(MEAL1.getDateTime());
        updated.setDescription("Обновленный Обед");
        updated.setCalories(650);
        return updated;
    }
}
