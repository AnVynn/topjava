package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepository.class);

    private final Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.meals.forEach(meal -> this.save(meal, 1));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        log.info("save {} with id= {}", meal, userId);
            if (meal.isNew()) {
                meal.setId(counter.incrementAndGet());
            }
            else {
                Meal meal1 = repository.get(meal.getId());
                if (meal1 != null && meal1.getUserId() != userId){
                    return null;
                }
            }
            repository.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        log.info("delete {} with id= {}", id, userId);
        Meal meal = repository.get(id);
        return meal != null && meal.getUserId() == userId && repository.remove(id) != null;
    }

    @Override
    public Meal get(int id, int userId) {
        log.info("get {} with id= {}", id, userId);
        Meal meal = repository.get(id);
        return meal != null && meal.getUserId() == userId ? meal : null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        log.info("getAll {}", userId);
        return getSortedByLocalDate(userId, LocalDate.MIN, LocalDate.MAX);
    }

    @Override
    public List<Meal> getSortedByLocalDate(int userId, LocalDate startDate, LocalDate endDate) {
        log.info("getSortedByLocalDate with id= {}", userId);
        return repository.values()
                .stream()
                .filter(meal -> meal.getUserId() == userId && DateTimeUtil.isBetweenHalfOpen(meal.getDate(), startDate, endDate))
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }
}

