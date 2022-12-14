package com.labs.additional.service.surface.calculation.equation.cubic;

import java.util.Optional;

public class CubicRoots {
    private final Double root1;
    private final Optional<Double> root2, root3;

    public CubicRoots(Double root1) {
        this.root1 = root1;
        this.root2 = Optional.empty();
        this.root3 = Optional.empty();
    }

    public CubicRoots(Double root1, Double root2, Double root3) {
        this.root1 = root1;
        this.root2 = Optional.of(root2);
        this.root3 = Optional.of(root3);
    }

    public Double getRoot1() {
        return root1;
    }

    public Optional<Double> getRoot2() {
        return root2;
    }

    public Optional<Double> getRoot3() {
        return root3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CubicRoots that = (CubicRoots) o;

        if (!root1.equals(that.root1)) return false;
        if (!root2.equals(that.root2)) return false;
        return root3.equals(that.root3);
    }

    @Override
    public int hashCode() {
        int result = root1.hashCode();
        result = 31 * result + root2.hashCode();
        result = 31 * result + root3.hashCode();
        return result;
    }
}
