package com.sparkminds.fresher_project_backend.annotation;

public enum ComparisonType {

    LESS_THAN {
        @Override
        public boolean compare(Comparable first, Comparable second) {
            return first.compareTo(second) < 0;
        }
    },
    LESS_THAN_OR_EQUAL {
        @Override
        public boolean compare(Comparable first, Comparable second) {
            return first.compareTo(second) <= 0;
        }
    },
    GREATER_THAN {
        @Override
        public boolean compare(Comparable first, Comparable second) {
            return first.compareTo(second) > 0;
        }
    },
    GREATER_THAN_OR_EQUAL {
        @Override
        public boolean compare(Comparable first, Comparable second) {
            return first.compareTo(second) >= 0;
        }
    },
    EQUAL {
        @Override
        public boolean compare(Comparable first, Comparable second) {
            return first.compareTo(second) == 0;
        }
    },
    NOT_EQUAL {
        @Override
        public boolean compare(Comparable first, Comparable second) {
            return first.compareTo(second) != 0;
        }
    };
    public abstract boolean compare(Comparable first, Comparable second);
}
