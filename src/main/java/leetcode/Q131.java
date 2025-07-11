package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * master分支
 */

public class Q131  {
    List<List<String>> ret   = new ArrayList<>();
    List<String> res = new ArrayList<>();
    int n;
    boolean[][] f;

    public List<List<String>> partition(String s) {
        n = s.length();
        f = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(f[i], true);
        }

        // f[i][j] 表示子串 s[i][j] 是否为回文串
        // 采用双指针的形式，动态规划思想
        // 状态转移方程：f[i][j] = (s.charAt(i) == s.charAt(j)) && f[i + 1][j - 1];
        // 边界判定：？为什么 i 要从 n-1 开始，而不是从 0 开始遍历
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; j++) {
                f[i][j] = (s.charAt(i) == s.charAt(j)) && f[i + 1][j - 1];
            }
        }
        
        // 通过上述处理，问题变为回溯
        dfs(s, 0);
        return ret;
    }

    public void dfs(String s, int i) {
        // 搜索完 s, 回退
        if (i == n) {
            ret.add(new ArrayList<>(res));
            return;
        }

        // 搜索下一个回文子串
        for (int j = i; j < n; ++j) {
            if (f[i][j]) {
                // substring函数不包含 endIndex 位置的字母，因此需要多截取一位
                res.add(s.substring(i, j + 1));
                // 搜索
                dfs(s, j + 1);
                // 回溯，删去最后添加的子串
                res.remove(res.size() - 1);
            }
        }

    }

}
