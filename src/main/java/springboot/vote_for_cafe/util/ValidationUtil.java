package springboot.vote_for_cafe.util;

import springboot.vote_for_cafe.error.IllegalRequestDataException;
import springboot.vote_for_cafe.model.Cafe;
import springboot.vote_for_cafe.model.CafeVote;
import springboot.vote_for_cafe.model.Dish;

public final class ValidationUtil {

    private ValidationUtil() {
    }

    public static void checkNew(Dish dish) {
        if (dish.getId() != null) {
            throw new IllegalRequestDataException("Dish must be new (id=null)");
        }
    }

    public static void checkNew(Cafe cafe) {
        if (cafe.getId() != null) {
            throw new IllegalRequestDataException("Cafe must be new (id=null)");
        }
    }

    public static void checkNew(CafeVote cafeVote) {
        if (cafeVote.getId() != null) {
            throw new IllegalRequestDataException("Vote must be new (id=null)");
        }
    }

}
