package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @see "https://leetcode.com/problems/evaluate-division/description/?envType=study-plan-v2&envId=leetcode-75"
 */
public class EvaluateDivisionTest {

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        var result = new double[queries.size()];
        var vertices = new HashMap<String, Vertice>();
        for (int i = 0; i < values.length; i++) {
            var equation = equations.get(i);
            var numerator = equation.get(0);
            var denominator = equation.get(1);
            var vn = vertices.computeIfAbsent(numerator, k -> new Vertice(numerator));
            var vd = vertices.computeIfAbsent(denominator, k -> new Vertice(denominator));
            vn.addEdge(new Edge(vd, values[i]));
            vd.addEdge(new Edge(vn, 1.0 / values[i]));
        }
        for (int i = 0; i < queries.size(); i++) {
            final List<String> query = queries.get(i);
            final var numerator = vertices.get(query.get(0));
            final var denominator = vertices.get(query.get(1));
            if (numerator == null || denominator == null) {
                result[i] = -1;
            } else {
                result[i] = traverse(numerator, 1.0, denominator, new HashSet<>());
            }
        }
        return result;
    }

    private double traverse(final Vertice start, final double cost, final Vertice end, final Set<Vertice> visited) {
        if (start == null) return -1;
        if (visited.contains(start)) return -1;
        visited.add(start);
        if (start.v.equals(end.v)) {
            return cost;
        }
        for (Edge e : start.edges) {
            var result = traverse(e.dest, cost * e.cost, end, visited);
            if (result != -1) return result;
        }
        return -1;
    }

    class Edge {
        Vertice dest;
        double cost;

        public Edge(final Vertice dest, final double cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }

    class Vertice {
        String v;
        List<Edge> edges = new ArrayList<>();

        public Vertice(final String v) {
            this.v = v;
        }

        void addEdge(Edge e) {
            edges.add(e);
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (!(o instanceof final Vertice vertice)) return false;
            return Objects.equals(v, vertice.v);
        }

        @Override
        public String toString() {
            return v;
        }

        @Override
        public int hashCode() {
            return Objects.hash(v);
        }
    }

    @Test
    void t1() {
        Assertions.assertArrayEquals(new double[] {6.00000,0.50000,-1.00000,1.00000,-1.00000},
                calcEquation(List.of(List.of("a", "b"), List.of("b", "c")),
                        new double[] {2.0,3.0},
                        List.of(List.of("a", "c"), List.of("b", "a"), List.of("a", "e"), List.of("a", "a"), List.of("x", "x"))
                )
        );
    }

    @Test
    void t2() {
        Assertions.assertArrayEquals(new double[] {3.75000,0.40000,5.00000,0.20000},
                calcEquation(List.of(List.of("a", "b"), List.of("b", "c"), List.of("bc", "cd")),
                        new double[] {1.5,2.5,5.0},
                        List.of(List.of("a", "c"), List.of("c", "b"), List.of("bc", "cd"), List.of("cd", "bc"))
                )
        );
    }

    @Test
    void t3() {
        Assertions.assertArrayEquals(new double[] {0.50000,2.00000,-1.00000,-1.00000},
                calcEquation(List.of(List.of("a", "b")),
                        new double[] {0.5},
                        List.of(List.of("a", "b"), List.of("b", "a"), List.of("a", "c"), List.of("x", "y"))
                )
        );
    }
}
