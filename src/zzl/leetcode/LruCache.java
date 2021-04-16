package zzl.leetcode;

import org.junit.Assert;

import java.util.*;

/**
 * LRU 缓存机制
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/lru-cache/}
 */
public class LruCache {
    public static void main(String[] args) {
        int capacity;
        capacity = 2;
        LRUCache lRUCache = new LRUCache(capacity);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        Assert.assertEquals(lRUCache.get(1).intValue(), 1);    // 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        Assert.assertEquals(lRUCache.get(2).intValue(), -1);    // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        Assert.assertEquals(lRUCache.get(1).intValue(), -1);    // 返回 -1 (未找到)
        Assert.assertEquals(lRUCache.get(3).intValue(), 3);    // 返回 3
        Assert.assertEquals(lRUCache.get(4).intValue(), 4);    // 返回 4
    }

    static class LRUCache extends LinkedHashMap<Integer, Integer> {
        private final int MAX_CACHE_SIZE;

        public LRUCache(int cacheSize) {
            // accessOrder为true时，为先进先出
            super(cacheSize, 0.75f, true);
            MAX_CACHE_SIZE = cacheSize;
        }

        /**
         * 原方法默认返回false不执行删除操作，
         * 重载该方法，并提供删除的判断逻辑
         *
         * @param eldest
         * @return
         * @see LinkedHashMap#removeEldestEntry(Map.Entry)
         */
        @Override
        protected boolean removeEldestEntry(Map.Entry eldest) {
            return size() > MAX_CACHE_SIZE;
        }

        @Override
        public Integer get(Object key) {
            return super.getOrDefault(key, -1);
        }
    }

}
