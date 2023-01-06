package com.labs.additional.service.surface.type;

import com.labs.additional.excepiton.LambdaIsNotPresentException;
import com.labs.additional.model.SurfaceValues;
import com.labs.additional.service.surface.calculation.equation.cubic.CubicRoots;

public class SurfaceTypeFounder {
    private final double I1, I2, I3, I4, K3;
    private final CubicRoots cubicRoots;

    public SurfaceTypeFounder(SurfaceValues surfaceValues) {
        this.I1 = surfaceValues.getI1();
        this.I2 = surfaceValues.getI2();
        this.I3 = surfaceValues.getI3();
        this.I4 = surfaceValues.getI4();
        this.K3 = surfaceValues.getK3();
        this.cubicRoots = surfaceValues.getCubicRoots();
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
        return switch (getWideType()) {
            case FULL_SQUARE -> findFullSquareType();
            case PARABOLOID -> findParaboloidType();
            case CYLINDER -> findCylinderType();
            case PARABOLIC_CYLINDER -> SurfaceType.PARABOLIC_CYLINDER;
            case PLANE -> SurfaceType.PLANE;
        };
    }

    private SurfaceType findFullSquareType() {
        double lambda1 = cubicRoots.getRoot1().orElseThrow(
                () -> new LambdaIsNotPresentException("lambda1 must be present in full squares"));
        double lambda2 = cubicRoots.getRoot2().orElseThrow(
                () -> new LambdaIsNotPresentException("lambda2 must be present in full squares"));
        double lambda3 = cubicRoots.getRoot3().orElseThrow(
                () -> new LambdaIsNotPresentException("lambda3 must be present in full squares"));

        double sign1 = Math.signum(lambda1), sign2 = Math.signum(lambda2), sign3 = Math.signum(lambda3);
        boolean sameSign = ( (sign1 == sign2) && (sign1 == sign3) );
        double value = I4 / I3;
        double valueSing = Math.signum(value);

        if (sameSign) {
            if (sign1 == valueSing) return SurfaceType.IMAGINARY_ELLIPSOID;
            return findExistingEllipsoidType(lambda1, lambda2, lambda3);
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

    private SurfaceType findExistingEllipsoidType(double lambda1, double lambda2, double lambda3) {
        if (lambda1 == lambda2 && lambda1 == lambda3) return SurfaceType.SPHERE;
        if (lambda1 == lambda2 || lambda1 == lambda3 || lambda2 == lambda3) return SurfaceType.SPHEROID;
        return SurfaceType.ELLIPSOID;
    }

    private SurfaceType findParaboloidType() {
        double lambda1 = cubicRoots.getRoot1().orElseThrow(
                () -> new LambdaIsNotPresentException("lambda1 must be present in paraboloids"));
        double lambda2 = cubicRoots.getRoot2().orElseThrow(
                () -> new LambdaIsNotPresentException("lambda2 must be present in paraboloids"));

        if (Math.signum(lambda1) != Math.signum(lambda2)) return SurfaceType.HYPERBOLIC_PARABOLOID;
        if (lambda1 != lambda2) return SurfaceType.ELLIPTICAL_PARABOLOID;
        return SurfaceType.CIRCULAR_PARABOLOID;
    }

    private SurfaceType findCylinderType() {
        double lambda1 = cubicRoots.getRoot1().orElseThrow(
                () -> new LambdaIsNotPresentException("lambda1 must be present in cylinders"));
        double lambda2 = cubicRoots.getRoot2().orElseThrow(
                () -> new LambdaIsNotPresentException("lambda2 must be present in cylinders"));

        if (Math.signum(lambda1) != Math.signum(lambda2)) return SurfaceType.HYPERBOLIC_CYLINDER;
        if (lambda1 != lambda2) return SurfaceType.ELLIPTICAL_CYLINDER;
        return SurfaceType.CIRCULAR_CYLINDER;
    }
}
