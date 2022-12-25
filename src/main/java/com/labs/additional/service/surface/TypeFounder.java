package com.labs.additional.service.surface;

import com.labs.additional.service.surface.type.SurfaceType;
import com.labs.additional.service.surface.type.WideSurfaceType;

import java.util.*;

public class TypeFounder {
    private double I1, I2, I3, I4, K3, lambda1, lambda2, lambda3;

    public void setValues(Map<String, Double> values) {
        this.I1 = values.get("I1");
        this.I2 = values.get("I2");
        this.I3 = values.get("I3");
        this.I4 = values.get("I4");
        this.K3 = values.get("K3");
        this.lambda1 = values.get("lambda1");
        this.lambda2 = values.get("lambda2");
        this.lambda3 = values.get("lambda3");
    }

    public WideSurfaceType getWideType() {

        if (I3 != 0) {
            return WideSurfaceType.FULL_SQUARE;
        }

        if (I2 != 0 && I4 != 0) {
            return WideSurfaceType.PARABOLOID;
        }

        if (I2 != 0 && K3 != 0) {
            return WideSurfaceType.CYLINDER;
        }

        if (I1 != 0 && I4 == 0 && K3 != 0) {
            return  WideSurfaceType.PARABOLIC_CYLINDER;
        }

        return WideSurfaceType.PLANE;
    }

    public SurfaceType getType() {

        switch (getWideType()) {
            case FULL_SQUARE:
                return findFullSquareType();
            case PARABOLOID:
                return findParaboloidType();
            case CYLINDER:
                return findCylinderType();
            case PARABOLIC_CYLINDER:
                return SurfaceType.PARABOLIC_CYLINDER;
            case PLANE:
                return SurfaceType.PLANE;
        }
        return null;
    }

    private SurfaceType findFullSquareType() {
        double sign1 = Math.signum(lambda1), sign2 = Math.signum(lambda2), sign3 = Math.signum(lambda3);
        boolean sameSign = sign1 == sign2 && sign1 == sign3;
        double value = I4 / I3;
        double valueSing = Math.signum(value);

        // Сфероїд та його підвиди
        if (sameSign) {
            if (value >= 0) return SurfaceType.IMAGINARY_ELLIPSOID;
            if (lambda1 == lambda2 && lambda1 == lambda3) return SurfaceType.SPHERE;
            if (lambda1 == lambda2 || lambda1 == lambda3 || lambda2 == lambda3) return SurfaceType.SPHEROID;
            return SurfaceType.ELLIPSOID;
        }

        if (value == 0) {
            return SurfaceType.CONE;
        }

        if ((valueSing == sign1 && valueSing == sign2) || (valueSing == sign1 && valueSing == sign3)
                || (valueSing == sign2 && valueSing == sign3))  {
            return SurfaceType.TWO_LEAF_HYPERBOLOID;
        }

        return SurfaceType.ONE_LEAF_HYPERBOLOID;
    }

    private SurfaceType findParaboloidType() {
        if (Math.signum(lambda1) != Math.signum(lambda2)) return SurfaceType.HYPERBOLIC_PARABOLOID;
        if (lambda1 != lambda2) return SurfaceType.ELLIPTICAL_PARABOLOID;
        return SurfaceType.CIRCULAR_PARABOLOID;
    }

    private SurfaceType findCylinderType() {
        if (Math.signum(lambda1) != Math.signum(lambda2)) return SurfaceType.HYPERBOLIC_CYLINDER;
        if (lambda1 != lambda2) return SurfaceType.ELLIPTICAL_CYLINDER;
        return SurfaceType.CIRCULAR_CYLINDER;
    }
}
