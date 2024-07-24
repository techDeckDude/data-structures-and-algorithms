package dsa.search;
import dsa.store.graph.*;

public class QuickUnionFindPathCompressionAndRank {
    public static void main(String[] args) throws Exception {
        DisjointSet disjointSet = new DisjointSet();
        DisjointSet.PathCompressionAndRank uf = disjointSet.new PathCompressionAndRank(10);
        // 1-2-5-6-7 3-8-9 4
        uf.union(1, 2);
        uf.union(2, 5);
        uf.union(5, 6);
        uf.union(6, 7);
        uf.union(3, 8);
        uf.union(8, 9);
        System.out.println(uf.connected(1, 5)); // true
        System.out.println(uf.connected(5, 7)); // true
        System.out.println(uf.connected(4, 9)); // false
        // 1-2-5-6-7 3-8-9-4
        uf.union(9, 4);
        System.out.println(uf.connected(4, 9)); // true
    }

}
