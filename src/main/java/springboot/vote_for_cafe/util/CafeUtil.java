package springboot.vote_for_cafe.util;

import springboot.vote_for_cafe.model.Cafe;
import springboot.vote_for_cafe.to.CafeTo;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class CafeUtil {

    private CafeUtil() {
    }

    public static List<CafeTo> getTos(List<Cafe> cafes, Map<String, Integer> scores) {
        Map<String, Cafe> cafeMap = cafes.stream()
                .collect(Collectors.toMap(Cafe::getName, cafe -> cafe));

        return scores.entrySet().stream()
                .map(entry -> createTo(cafeMap.get(entry.getKey()), entry.getValue()))
                .collect(Collectors.toList());
    }

    public static CafeTo createTo(Cafe cafe, int scores) {
        return new CafeTo(cafe.getId(), cafe.getName(), scores);
    }

}
