package com.labs.additional.surface.type;

public enum SurfaceType {

    // Full square
    IMAGINARY_ELLIPSOID("Уявний еліпсоїд"),
    ELLIPSOID("Еліпсоїд"),
    SPHEROID("Сфероїд"),
    SPHERE("Сфера"),
    ONE_LEAF_HYPERBOLOID("Гіперболоїд однолистовий"),
    TWO_LEAF_HYPERBOLOID("Гіперболоїд дволистовий"),
    CONE("Конус"),

    // Cylinders
    ELLIPTICAL_CYLINDER("Еліптичний циліндр"),
    HYPERBOLIC_CYLINDER("Гіперболічний циліндр"),
    CIRCULAR_CYLINDER("Коловий циліндр"),

    // Paraboloids
    ELLIPTICAL_PARABOLOID("Еліптичний параболоїд"),
    HYPERBOLIC_PARABOLOID("Гіперболічний параболоїд"),
    CIRCULAR_PARABOLOID("Коловий параболоїд"),

    PARABOLIC_CYLINDER("Параболічний циліндр"),

    PLANE("Площина");

    private final String name;

    SurfaceType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
