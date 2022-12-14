package com.labs.additional.model;

import com.labs.additional.service.surface.type.SurfaceType;
import com.labs.additional.service.surface.type.WideSurfaceType;

public class Surface {
    public final CoefficientsA coefficientsA;
    public final SurfaceValues values;
    public final WideSurfaceType wideType;
    public final SurfaceType type;

    public Surface(CoefficientsA coefficientsA, SurfaceValues values,
                   WideSurfaceType wideType, SurfaceType type) {

        this.coefficientsA = coefficientsA;
        this.values = values;
        this.wideType = wideType;
        this.type = type;
    }

    public CoefficientsA getCoefficientsA() {
        return coefficientsA;
    }

    public SurfaceValues getValues() {
        return values;
    }

    public WideSurfaceType getWideType() {
        return wideType;
    }

    public SurfaceType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Surface surface = (Surface) o;

        return coefficientsA.equals(surface.coefficientsA);
    }

    @Override
    public int hashCode() {
        return coefficientsA.hashCode();
    }
}
