package springboot.vote_for_cafe;

import springboot.vote_for_cafe.controller.MatcherFactory;
import springboot.vote_for_cafe.model.Cafe;
import springboot.vote_for_cafe.model.Dish;

import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.time.LocalDateTime.of;

public class TestData {
    public static final int CAFE1_ID = 1;
    public static final int DISH1_ID = 1;
    public static final String USER_MAIL = "smith@yandex.ru";
    public static final String ADMIN_MAIL = "petrov@yandex.ru";

    public static final MatcherFactory.Matcher<Dish> DISH_MATCHER =
            MatcherFactory.usingIgnoringFieldsComparator(Dish.class);

    public static final MatcherFactory.Matcher<Dish> DISH_IGNORE_CAFE_MATCHER =
            MatcherFactory.usingIgnoringFieldsComparator(Dish.class, "cafe");

    public static final MatcherFactory.Matcher<Cafe> CAFE_MATCHER =
            MatcherFactory.usingIgnoringFieldsComparator(Cafe.class);

    public static final Dish dish1 = new Dish(DISH1_ID,"Салат Цезарь", 500);
    public static final Dish dish2 = new Dish(DISH1_ID + 1,"Лингвини с лососем", 600);

    public static final List<Dish> dishes = List.of(dish1, dish2);

   public static final Cafe cafe1 = new Cafe(CAFE1_ID,"Ресторан Достоевский");

    static {
        cafe1.setDishes(dishes);
         } // правильно ли так делать?

    public static Dish getNew() {
        return new Dish(null, "Созданное блюдо", 100);
    }

}
