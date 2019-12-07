package kuchen;

import hersteller.HerstellerImplementation;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class KuchenParser {

    public Kuchen getKuchenInfo(String str) {
        String[] split = str.split(" ");
        split = removeEmptySpaces(split);
        String kuchenType = split[0];
        try {
            switch (kuchenType.toLowerCase()) {
                case "kuchen":
                    return new KuchenImplementation(
                            new BigDecimal(split[1].trim()),
                            new HerstellerImplementation(split[2].trim()),
                            getAllergeneInfo(split[3]),
                            Integer.parseInt(split[4].trim()),
                            Duration.ofHours(Long.parseLong(split[5].trim()))
                    );
                case "kremkuchen":
                    return new KremkuchenImplementation(
                            new BigDecimal(split[1].trim()),
                            new HerstellerImplementation(split[2].trim()),
                            getAllergeneInfo(split[3]),
                            Integer.parseInt(split[4].trim()),
                            Duration.ofHours(Long.parseLong(split[5].trim())),
                            split[6].trim()
                    );
                case "obstkuchen":
                    return new ObstkuchenImplementation(
                            new BigDecimal(split[1].trim()),
                            new HerstellerImplementation(split[2].trim()),
                            getAllergeneInfo(split[3]),
                            Integer.parseInt(split[4].trim()),
                            Duration.ofHours(Long.parseLong(split[5].trim())),
                            split[6].trim()
                    );
                case "obsttorte":
                    return new ObsttorteImplementation(
                            new BigDecimal(split[1].trim()),
                            new HerstellerImplementation(split[2].trim()),
                            getAllergeneInfo(split[3]),
                            Integer.parseInt(split[4].trim()),
                            Duration.ofHours(Long.parseLong(split[5].trim())),
                            split[6].trim(),
                            split[7].trim()
                    );
            }
        } catch (Exception e){
            return null;
        }
        return null;
    }

    private String[] removeEmptySpaces(String[] split) {
        return Arrays.stream(split)
                .filter(item -> !item.equals(""))
                .toArray(String[]::new);
    }


    private List<Allergen> getAllergeneInfo(String str) {
        return Stream.of(str.split(","))
                .map(item -> Allergen.valueOf(item.trim()))
                .collect(Collectors.toList());
    }

    private boolean checkIfAnyNumberInString(String str) {
        char[] charsOfInput = str.toCharArray();
        for (char each : charsOfInput) {
            if (Character.isDigit(each)) {
                return true;
            }
        }
        return false;
    }

}
