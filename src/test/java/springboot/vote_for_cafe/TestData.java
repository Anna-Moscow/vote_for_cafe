package springboot.vote_for_cafe;

import springboot.vote_for_cafe.web.MatcherFactory;
import springboot.vote_for_cafe.model.*;
import springboot.vote_for_cafe.to.CafeTo;

import java.time.LocalDate;
import java.util.*;

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

    public static final MatcherFactory.Matcher<Cafe> CAFE_IGNORE_MATCHER =
            MatcherFactory.usingIgnoringFieldsComparator(Cafe.class, "dishes", "votes");

    public static final MatcherFactory.Matcher<Cafe> CAFE_MATCHER =
            MatcherFactory.usingIgnoringFieldsComparator(Cafe.class);

    public static final MatcherFactory.Matcher<CafeTo> CAFE_TO_MATCHER =
            MatcherFactory.usingIgnoringFieldsComparator(CafeTo.class);




    public static final Dish dish1 = new Dish(DISH1_ID,"Салат Цезарь", 500);
    public static final Dish dish2 = new Dish(DISH1_ID + 1,"Лингвини с лососем", 600);

    public static final List<Dish> dishes = List.of(dish1, dish2);

   public static final Cafe cafe1 = new Cafe(CAFE1_ID,"Ресторан Достоевский");
    public static final Cafe cafe2 = new Cafe(CAFE1_ID + 1,"Кафе Эстерхази");

    public static final List<Cafe> cafes = List.of(cafe1, cafe2);



    public static Dish getNewDish() {
        return new Dish(null, "Созданное блюдо", 100);
    }

    public static Cafe getNewCafe() {
        return new Cafe(null, "Созданное кафе");
    }

    // голоса отсюда начинаются
    public static final int USER1_ID = 1;
       public static final User user1 = new User(USER1_ID, "Джон", "Смит", "smith@yandex.ru", "password1", Role.USER);
    public static final User user4 = new User(USER1_ID + 3, "Анна", "Каренина", "karenina@yandex.ru", "password4", Role.USER);


    public static final int VOTE1_ID = 1;
    public static final CafeVote vote1 = new CafeVote(VOTE1_ID, LocalDate.now().atTime(10, 0));
    public static final CafeVote vote4 = new CafeVote(VOTE1_ID + 3,LocalDate.now().atTime(1, 0));

    //public static final List<CafeTo> votes = new ArrayList<>();
    public static final Map<String, Integer> votes = new HashMap<>();

    static {
      //  votes.add (cafe1);
        votes.put (cafe1.getName(), 2);
        votes.put (cafe2.getName(), 0);
        cafe1.setDishes(dishes);
        //cafe1.setVotes(List.of(vote1));
        //cafe2.setVotes(List.of(vote4));
        //user1.setVotes(List.of(vote1));
       // user4.setVotes(List.of(vote4));
    } // правильно ли так делать?
}
