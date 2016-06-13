package common.selection;

public enum DISTRIBUTION_STRATEGY {

    MIN_CPU_UTILIZATION(1, "Min. of CPU utilization"),

    MIN_RAM_UTILIZATION(2, "Min. of RAM utilization"),

    MIN_ACTIVATED_RULES(3, "Min. number of activated rules");

    private String description;

    private int number;

    DISTRIBUTION_STRATEGY(int number, String description) {
        this.description = description;
        this.number = number;
    }

    public static DISTRIBUTION_STRATEGY getByNumber(int number) {

        for (DISTRIBUTION_STRATEGY strategy : DISTRIBUTION_STRATEGY.values()) {
            if (strategy.getNumber() == number) {
                return strategy;
            }
        }

        return MIN_CPU_UTILIZATION;
    }

    public static DISTRIBUTION_STRATEGY getByDescription(String description) {

        for (DISTRIBUTION_STRATEGY strategy : DISTRIBUTION_STRATEGY.values()) {
            if (strategy.getClass().equals(description)) {
                return strategy;
            }
        }

        return MIN_CPU_UTILIZATION;
    }

    public String getDescription() {
        return description;
    }

    public int getNumber() {
        return number;
    }

}
