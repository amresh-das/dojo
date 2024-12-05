package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @see "https://leetcode.com/problems/sentence-screen-fitting/"
 */
public class SentenceScreenFitting {
    public int wordsTyping(String[] words, int rows, int cols) {
        int sentenceLength = Arrays.stream(words).map(String::length).reduce(0, Integer::sum) + words.length - 1;
        int row = rows, col = cols;
        int count = 0;
        int idx = 0;
        while (row > 0) {
            if (words[idx].length() > col) return 0;
            int n = col / sentenceLength;
            if (n > 0) {
                while (col - n * sentenceLength < n - 1) {
                    n--;
                }
                col -= n * sentenceLength + (n - 1);
                count += n;
                if (n > 0 && col > 0) col--;
            }
            while (col - words[idx].length() >= 0) {
                col -= words[idx].length();
                if (++idx == words.length) {
                    idx = 0;
                    count++;
                }
                col--;
            }
            row--;
            col = cols;
        }
        return count;
    }

    @Test
    public void check1() {
        Assertions.assertEquals(1, wordsTyping(Utils.toStringArray("[\"hello\",\"world\"]"), 2, 8));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(2, wordsTyping(Utils.toStringArray("[\"a\",\"bcd\",\"e\"]"), 3, 6));
    }

    @Test
    public void check3() {
        Assertions.assertEquals(1, wordsTyping(Utils.toStringArray("[\"i\",\"had\",\"apple\",\"pie\"]"), 4, 5));
    }

    @Test
    public void check4() {
        Assertions.assertEquals(2, wordsTyping(Utils.toStringArray("[\"a\",\"bcd\",\"e\"]"), 3, 6));
    }

    @Test
    public void check5() {
        Assertions.assertEquals(10, wordsTyping(Utils.toStringArray("[\"f\",\"p\",\"a\"]"), 8, 7));
    }

    @Test
    public void check6() {
        Assertions.assertEquals(10, wordsTyping(Utils.toStringArray("[\"f\",\"p\",\"a\"]"), 8, 7));
    }

    @Test
    public void check7() {
        Assertions.assertEquals(133300000, wordsTyping(Utils.toStringArray("[\"a\",\"b\"]"), 20000, 20000));
    }

    @Test
    public void check8() {
        Assertions.assertEquals(4, wordsTyping(Utils.toStringArray("[\"h\"]"), 2, 3));
    }

    @Test
    public void check9() {
        String words = "[\"sdcryxmeru\",\"mybompgk\",\"orqxcs\",\"zaclfpaynj\",\"eyvbg\",\"nnlgawwnll\",\"rjeuw\",\"bicrgi\",\"bduijxi\",\"pqhcvredwg\",\"mimafalo\",\"qnkwhljp\",\"svol\",\"libzh\",\"kittf\",\"yrmnkcls\",\"yr\",\"zwi\",\"eexde\",\"jjnewbw\",\"jctpr\",\"tkzs\",\"ipqzpg\",\"jjm\",\"t\",\"h\",\"sew\",\"drbm\",\"fwlqi\",\"rvupujcri\",\"l\",\"nhiy\",\"eu\",\"xappthq\",\"smhzlkci\",\"pnyjqj\",\"tfei\",\"hvcjei\",\"bk\",\"owzvradvgz\",\"ekjlri\",\"mdex\",\"itqqs\",\"mmsqtzizwa\",\"c\",\"mhhfwcndp\",\"w\",\"szwjvnzfos\",\"jnsgvkyetx\",\"glngaxuh\",\"jmwk\",\"ca\",\"ucee\",\"mfqoxzmsa\",\"agzq\",\"cq\",\"xv\",\"ysvyh\",\"iqdaui\",\"unay\",\"cqx\",\"vgzbxhv\",\"bmc\",\"axouywtu\",\"bqjawvcpd\",\"hq\",\"gmtrrfzd\",\"tytnig\",\"wlowagrxsf\",\"xnxsba\",\"us\",\"xgdyno\",\"pyeh\",\"xzzhjtotsf\",\"bvvyfzo\",\"spdojvolrh\",\"rxe\",\"iwsznu\",\"vu\",\"vdgfukmjbj\",\"vw\",\"qkbnu\",\"mresigfpho\",\"awvibdxn\",\"xazqiedtwf\",\"rlxdyrtryb\",\"uanmajo\",\"kudcyedndg\",\"xrml\",\"ykziwb\",\"a\",\"ka\",\"sr\",\"rkshgye\",\"m\",\"pdoi\",\"zlqjut\",\"ftjrv\",\"ocvctao\"]";
        Assertions.assertEquals(458163, wordsTyping(Utils.toStringArray(words), 15382, 19573));
    }

    @Test
    public void check10() {
        String words = "[\"try\",\"to\",\"be\",\"better\"]";
        Assertions.assertEquals(5293333, wordsTyping(Utils.toStringArray(words), 10000, 9001));
    }
}
