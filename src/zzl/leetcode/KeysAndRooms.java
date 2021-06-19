package zzl.leetcode;

import org.junit.Assert;
import zzl.base.annotation.Level;
import zzl.util.GenerateUtil;

import java.util.*;

import static zzl.base.enums.Difficulty.MEDIUM;

/**
 * 钥匙和房间
 *
 * @author zzl
 * @date 2021-06-19
 * @link {https://leetcode-cn.com/problems/keys-and-rooms/}
 */
@Level(MEDIUM)
public class KeysAndRooms {
    public static void main(String[] args) {
        List<List<Integer>> rooms;
        boolean canVisitAllRooms;
        //
        rooms = GenerateUtil.generateBinaryIntList(" [[1],[2],[3],[]]");
        canVisitAllRooms = new KeysAndRooms().canVisitAllRooms(rooms);
        Assert.assertTrue(canVisitAllRooms);
        //
        rooms = GenerateUtil.generateBinaryIntList("[[1,3],[3,0,1],[2],[0]]");
        canVisitAllRooms = new KeysAndRooms().canVisitAllRooms(rooms);
        Assert.assertFalse(canVisitAllRooms);
    }


    /**
     * 广度优先遍历
     *
     * @param rooms
     * @return
     */
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int roomSize = rooms.size();
        Deque<Integer> queue = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        queue.add(0);
        while (!queue.isEmpty()) {
            int curRoom = queue.poll();
            visited.add(0);
            for (Integer keysInRoom : rooms.get(curRoom)) {
                if (!visited.contains(keysInRoom)) {
                    visited.add(keysInRoom);
                    queue.add(keysInRoom);
                }
            }
        }
        return visited.size() == roomSize;
    }
}
