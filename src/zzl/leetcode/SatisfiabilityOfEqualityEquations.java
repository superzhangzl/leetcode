package zzl.leetcode;

import org.junit.Assert;
import zzl.base.annotation.Level;
import zzl.base.templates.UnionFind;
import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

import static zzl.base.enums.Difficulty.MEDIUM;

/**
 * 等式方程的可满足性
 *
 * @author zzl
 * @date 2021-04-18
 * @link {https://leetcode-cn.com/problems/satisfiability-of-equality-equations/}
 */
@Level(MEDIUM)
public class SatisfiabilityOfEqualityEquations {
    public static void main(String[] args) {
        String[] equations;
        boolean possible;
        //
        equations = GenerateUtil.generateStringArray("\"a==b\",\"b!=a\"");
        possible = new SatisfiabilityOfEqualityEquations().equationsPossible(equations);
        Assert.assertEquals(possible, false);
        //
        equations = GenerateUtil.generateStringArray("\"b==a\",\"a==b\"");
        possible = new SatisfiabilityOfEqualityEquations().equationsPossible(equations);
        Assert.assertEquals(possible, true);

    }

    public boolean equationsPossible(String[] equations) {
        UnionFind uf = new UnionFind(26);
        // 先让"=="的字符连通起来
        for (String equation : equations) {
            if (equation.charAt(1) == '=') {
                char x = equation.charAt(0);
                char y = equation.charAt(3);
                uf.union(x - 'a', y - 'a');
            }
        }
        // 再检查"!="的字符，是否会打破原来已经连通的状态，如果打破了（即之前已经相连起来了），说明表达式不成立
        for (String equation : equations) {
            if (equation.charAt(1) == '!') {
                char x = equation.charAt(0);
                char y = equation.charAt(3);
                if (uf.connected(x - 'a', y - 'a')) {
                    return false;
                }
            }
        }
        return true;
    }
}
