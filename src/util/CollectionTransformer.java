package util;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class CollectionTransformer {

    public  List<Entry<String, Double>> mapToList(Map<String, Double> map) {
        return map.entrySet().stream()
                .sorted(Comparator.comparing(Entry::getKey))
                .collect(Collectors.toList());
  }
}
