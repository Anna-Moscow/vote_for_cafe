package springboot.vote_for_cafe.web.util;

import org.springframework.util.Assert;
import springboot.vote_for_cafe.model.Cafe;
import springboot.vote_for_cafe.model.CafeVote;
import springboot.vote_for_cafe.model.Dish;
import springboot.vote_for_cafe.to.CafeTo;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestData {

    public static final MatcherFactory.Matcher<Dish> DISH_MATCHER =
            MatcherFactory.usingIgnoringFieldsComparator(Dish.class);

    public static final MatcherFactory.Matcher<Dish> DISH_IGNORE_CAFE_MATCHER =
            MatcherFactory.usingIgnoringFieldsComparator(Dish.class, "cafe");

    public static final MatcherFactory.Matcher<Cafe> CAFE_MATCHER =
            MatcherFactory.usingIgnoringFieldsComparator(Cafe.class, "dishes", "votes");

    public static final MatcherFactory.Matcher<CafeTo> CAFE_TO_MATCHER =
            MatcherFactory.usingIgnoringFieldsComparator(CafeTo.class);

    public static final MatcherFactory.Matcher<CafeVote> CAFE_VOTE_MATCHER =
            MatcherFactory.usingIgnoringFieldsComparator(CafeVote.class, "cafe", "user", "created");

    public static final int CAFE1_ID = 1;
    public static final int DISH1_ID = 1;
    public static final String USER_MAIL = "smith@yandex.ru";
    public static final String ADMIN_MAIL = "petrov@yandex.ru";

    public static final Dish dish1 = new Dish(DISH1_ID, "Салат Цезарь", 500);
    public static final Dish dish2 = new Dish(DISH1_ID + 1, "Лингвини с лососем", 600);
    public static final List<Dish> dishes = List.of(dish1, dish2);

    public static final Cafe cafe1 = new Cafe(CAFE1_ID, "Ресторан Достоевский");
    public static final Cafe cafe2 = new Cafe(CAFE1_ID + 1, "Кафе Эстерхази");
    public static final List<Cafe> cafes = List.of(cafe1, cafe2);

    public static Dish getNewDish() {
        return new Dish(null, "Созданное блюдо", 100);
    }

    public static Cafe getNewCafe() {
        return new Cafe(null, "Созданное кафе");
    }

    public static CafeVote getNewCafeVote() {
        return new CafeVote(null, LocalDateTime.now());
    }

    public static final Map<String, Integer> votes = new HashMap<>();

    public static int getCafeId(Cafe cafe) {
        Assert.notNull(cafe.getId(), "Entity must have id");
        return cafe.getId();
    }

    public static int getDishId(Dish dish) {
        Assert.notNull(dish.getId(), "Entity must have id");
        return dish.getId();
    }

    public static int getCafeVoteId(CafeVote cafeVote) {
        Assert.notNull(cafeVote.getId(), "Entity must have id");
        return cafeVote.getId();
    }

    static {
        votes.put(cafe1.getName(), 2);
        votes.put(cafe2.getName(), 0);
        cafe1.setDishes(dishes);
    }

}
