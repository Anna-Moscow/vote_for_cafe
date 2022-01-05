package springboot.vote_for_cafe.util;

import springboot.vote_for_cafe.error.IllegalRequestDataException;
import springboot.vote_for_cafe.model.Cafe;
import springboot.vote_for_cafe.model.Dish;

public class ValidationUtil {
    public static void checkNew(Dish dish) {
        if (dish.getId() != null) {
            throw new IllegalRequestDataException( "Dish must be new (id=null)");
        }
    }

    public static void checkNew(Cafe cafe) {
        if (cafe.getId() != null) {
            throw new IllegalRequestDataException("Cafe must be new (id=null)");
        }
    }
    // добавть вызов метода в save servicA
    //возможно еще нужен для  user (если будет регистрация в profileController?)
    // можно все унаследовать от интерфейса HasId как в примере
}