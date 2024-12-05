package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @see "https://leetcode.com/problems/keys-and-rooms/description/?envType=study-plan-v2&envId=leetcode-75"
 */
public class KeysAndRoomsTest {

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        rooms.get(0).forEach(r -> visit(rooms, r, visited));
        return visited.size() == rooms.size();
    }

    private void visit(final List<List<Integer>> rooms, final int room, final Set<Integer> visited) {
        if (visited.contains(room)) return;
        visited.add(room);
        rooms.get(room).forEach(r -> visit(rooms, r, visited));
    }

    @Test
    void t1() {
        Assertions.assertTrue(canVisitAllRooms(
                List.of(
                        List.of(1),
                        List.of(2),
                        List.of(3),
                        List.of()
                )
        ));
    }

    @Test
    void t2() {
        Assertions.assertFalse(canVisitAllRooms(
                List.of(
                        List.of(1, 3),
                        List.of(3, 0, 1),
                        List.of(2),
                        List.of(0)
                )
        ));
    }
}
