package com.amz;

import com.google.common.collect.Sets;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @see "https://leetcode.com/problems/word-ladder-ii/"
 */
public class WordLadderII {

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        final Set<List<String>> ans = new HashSet<>();
        if (!wordList.contains(endWord)) return new ArrayList<>();
        Map<String, Map<String, Boolean>> allowedTransitions = new HashMap<>();
        Map<String, List<String>> neighbours = new HashMap<>();
        Map<String, Set<String>> forwardPaths = new HashMap<>();
        Map<String, Set<String>> backwardPaths = new HashMap<>();
        Set<String> words = new HashSet<>(wordList);

        PriorityQueue<Object[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> (int) a[0]));
        PriorityQueue<Object[]> pqB = new PriorityQueue<>(Comparator.comparingInt(a -> (int) a[0]));
        pq.offer(new Object[] {1, beginWord, ""});
        pqB.offer(new Object[] {1, endWord, endWord});
        long ansLength = Integer.MAX_VALUE;

        while (!pq.isEmpty() && !pqB.isEmpty()) {
            Object[] item = pq.poll();
            int priority = (int) item[0];
            String begin = (String) item[1];
            String path = (String) item[2];
            forwardPaths.computeIfAbsent(begin, k -> new HashSet<>()).add(path);

            Object[] itemB = pqB.poll();
            int priorityB = (int) itemB[0];
            String end = (String) itemB[1];
            String pathB = (String) itemB[2];
            backwardPaths.computeIfAbsent(end, k -> new HashSet<>()).add(pathB);

            if (begin.equals(endWord)) {
                List<String> actual = combine(path, endWord);
                ansLength = actual.size();
                ans.add(actual);
            } else {
                if (backwardPaths.containsKey(begin)) {
                    for (String p : backwardPaths.get(begin)) {
                        List<String> actual = combine(path, p);
                        ansLength = Math.min(actual.size(), ansLength);
                        ans.add(actual);
                    }
                }
                if (forwardPaths.containsKey(end)) {
                    for (String p: forwardPaths.get(end)) {
                        List<String> actual = combine(p, pathB);
                        ansLength = Math.min(actual.size(), ansLength);
                        ans.add(actual);
                    }
                }
            }
            if (priority + priorityB <= ansLength) {
                for (String other : neighbours.computeIfAbsent(begin, k -> findNeighbors(k, words))) {
                    if (!path.contains(other) && !begin.equals(other)) {
                        Map<String, Boolean> edges = allowedTransitions.computeIfAbsent(begin, k -> new HashMap<>());
                        boolean isAllowed = edges.computeIfAbsent(other, (k2) -> diff(begin, other) == 1);
                        allowedTransitions.putIfAbsent(other, new HashMap<>());
                        allowedTransitions.get(other).putIfAbsent(begin, isAllowed);
                        if (isAllowed) {
                            pq.offer(new Object[]{priority + 1, other, path + "," + begin});
                        }
                    }
                }
                for (String other : neighbours.computeIfAbsent(end, k -> findNeighbors(k, words))) {
                    if (!pathB.contains(other)) {
                        Map<String, Boolean> edges = allowedTransitions.computeIfAbsent(end, k -> new HashMap<>());
                        boolean isAllowed = edges.computeIfAbsent(other, (k2) -> diff(end, other) == 1);
                        allowedTransitions.putIfAbsent(other, new HashMap<>());
                        allowedTransitions.get(other).putIfAbsent(begin, isAllowed);
                        if (isAllowed) {
                            pqB.offer(new Object[]{priorityB + 1, other, other + "," + pathB});
                        }
                    }
                }
            }
        }
        final long len = ansLength;
        return ans.stream().filter(x -> x.size() == len).collect(Collectors.toList());
    }

    private List<String> findNeighbors(String word, Set<String> wordList) {
        List<String> neighbors = new ArrayList<String>();
        char[] charList = word.toCharArray();

        for (int i = 0; i < word.length(); i++) {
            char oldChar = charList[i];

            // replace the i-th character with all letters from a to z except the original character
            for (char c = 'a'; c <= 'z'; c++) {
                charList[i] = c;

                // skip if the character is same as original or if the word is not present in the wordList
                if (c == oldChar || !wordList.contains(String.valueOf(charList))) {
                    continue;
                }
                neighbors.add(String.valueOf(charList));
            }
            charList[i] = oldChar;
        }
        return neighbors;
    }

    private List<String> combine(String prefix, String suffix) {
        String path;
        boolean prefixEndComma = prefix.endsWith(",");
        boolean suffixBeginComma = suffix.startsWith(",");
        if (prefixEndComma && suffixBeginComma) path = prefix + suffix.substring(1);
        else if (prefixEndComma || suffixBeginComma) path = prefix + suffix;
        else path = prefix + "," + suffix;
        return Arrays.asList(path.substring(1).split(","));
    }

    public List<List<String>> findLadders2(String beginWord, String endWord, List<String> wordList) {
        final List<List<String>> ans = new ArrayList<>();
        Map<String, Set<String>> transitions = buildTransitions(beginWord, wordList);
        makeLadder(beginWord, endWord, transitions, new HashSet<>(), new ArrayList<>(), ans);
        return ans;
    }

    private void makeLadder(String beginWord, String endWord, Map<String, Set<String>> transitions, Set<String> seen, List<String> current, List<List<String>> ans) {
        current.add(beginWord);
        if (beginWord.equals(endWord)) {
            int currentSize = ans.isEmpty() ? Integer.MAX_VALUE : ans.get(0).size();
            if (currentSize > current.size()) {
                ans.removeIf(x -> x.size() > current.size());
            }
            if (current.size() <= currentSize) {
                ans.add(new ArrayList<>(current));
            }
        }
        for (String next : transitions.getOrDefault(beginWord, new HashSet<>())) {
            if (!seen.contains(next)) {
                seen.add(next);
                makeLadder(next, endWord, transitions, seen, current, ans);
                seen.remove(next);
                current.remove(current.size() - 1);
            }
        }
    }

    private Map<String, Set<String>> buildTransitions(String beginWord, List<String> wordList) {
        Map<String, Set<String>> transitions = new HashMap<>();
        Set<String> initial = new HashSet<>();
        for (String word : wordList) {
            if (diff(beginWord, word) == 1) initial.add(word);
            transitions.putIfAbsent(word, new HashSet<>());
            for (String other : wordList) {
                if (diff(word, other) == 1) {
                    transitions.putIfAbsent(other, new HashSet<>());
                    transitions.get(word).add(other);
                    transitions.get(other).add(word);
                }
            }
        }
        transitions.put(beginWord, initial);
        return transitions;
    }

    private int diff(String word, String other) {
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != other.charAt(i)) count++;
            if (count > 1) break;
        }
        return count;
    }


    @Test
    public void check1() {
        List<String> list = Lists.newArrayList("hot","dot","dog","lot","log","cog");
        final Set<List<String>> expected = Sets.newHashSet(
                Lists.newArrayList("hit","hot","dot","dog","cog"),
                Lists.newArrayList("hit","hot","lot","log","cog")
        );
        Assertions.assertEquals(expected, new HashSet<>(findLadders("hit", "cog", list)));
    }

    @Test
    public void check2() {
        List<String> list = Lists.newArrayList("hot","dot","dog","lot","log");
        final Set<List<String>> expected = Sets.newHashSet();
        Assertions.assertEquals(expected, new HashSet<>(findLadders("hit", "cog", list)));
    }

    @Test
    public void check3() {
        List<String> list = Lists.newArrayList("miss","dusk","kiss","musk","tusk","diss","disk","sang","ties","muss");
        final Set<List<String>> expected = Sets.newHashSet(
                Lists.newArrayList("kiss","diss","disk","dusk","tusk"),
                Lists.newArrayList("kiss","miss","muss","musk","tusk")
        );
        Assertions.assertEquals(expected, new HashSet<>(findLadders("kiss", "tusk", list)));
    }

    @Test
    public void check4() {
        List<String> list = Lists.newArrayList("si","go","se","cm","so","ph","mt","db","mb","sb","kr","ln","tm","le","av","sm","ar","ci","ca","br","ti","ba","to","ra","fa","yo","ow","sn","ya","cr","po","fe","ho","ma","re","or","rn","au","ur","rh","sr","tc","lt","lo","as","fr","nb","yb","if","pb","ge","th","pm","rb","sh","co","ga","li","ha","hz","no","bi","di","hi","qa","pi","os","uh","wm","an","me","mo","na","la","st","er","sc","ne","mn","mi","am","ex","pt","io","be","fm","ta","tb","ni","mr","pa","he","lr","sq","ye");
        final List<List<String>> paths = findLadders("qa", "sq", list);
        Assertions.assertEquals(51, paths.size());
        paths.forEach(p -> Assertions.assertEquals(5, p.size()));
    }

    @Test
    public void check5() {
        List<String> list = Lists.newArrayList("kid","tag","pup","ail","tun","woo","erg","luz","brr","gay","sip","kay","per","val","mes","ohs","now","boa","cet","pal","bar","die","war","hay","eco","pub","lob","rue","fry","lit","rex","jan","cot","bid","ali","pay","col","gum","ger","row","won","dan","rum","fad","tut","sag","yip","sui","ark","has","zip","fez","own","ump","dis","ads","max","jaw","out","btu","ana","gap","cry","led","abe","box","ore","pig","fie","toy","fat","cal","lie","noh","sew","ono","tam","flu","mgm","ply","awe","pry","tit","tie","yet","too","tax","jim","san","pan","map","ski","ova","wed","non","wac","nut","why","bye","lye","oct","old","fin","feb","chi","sap","owl","log","tod","dot","bow","fob","for","joe","ivy","fan","age","fax","hip","jib","mel","hus","sob","ifs","tab","ara","dab","jag","jar","arm","lot","tom","sax","tex","yum","pei","wen","wry","ire","irk","far","mew","wit","doe","gas","rte","ian","pot","ask","wag","hag","amy","nag","ron","soy","gin","don","tug","fay","vic","boo","nam","ave","buy","sop","but","orb","fen","paw","his","sub","bob","yea","oft","inn","rod","yam","pew","web","hod","hun","gyp","wei","wis","rob","gad","pie","mon","dog","bib","rub","ere","dig","era","cat","fox","bee","mod","day","apr","vie","nev","jam","pam","new","aye","ani","and","ibm","yap","can","pyx","tar","kin","fog","hum","pip","cup","dye","lyx","jog","nun","par","wan","fey","bus","oak","bad","ats","set","qom","vat","eat","pus","rev","axe","ion","six","ila","lao","mom","mas","pro","few","opt","poe","art","ash","oar","cap","lop","may","shy","rid","bat","sum","rim","fee","bmw","sky","maj","hue","thy","ava","rap","den","fla","auk","cox","ibo","hey","saw","vim","sec","ltd","you","its","tat","dew","eva","tog","ram","let","see","zit","maw","nix","ate","gig","rep","owe","ind","hog","eve","sam","zoo","any","dow","cod","bed","vet","ham","sis","hex","via","fir","nod","mao","aug","mum","hoe","bah","hal","keg","hew","zed","tow","gog","ass","dem","who","bet","gos","son","ear","spy","kit","boy","due","sen","oaf","mix","hep","fur","ada","bin","nil","mia","ewe","hit","fix","sad","rib","eye","hop","haw","wax","mid","tad","ken","wad","rye","pap","bog","gut","ito","woe","our","ado","sin","mad","ray","hon","roy","dip","hen","iva","lug","asp","hui","yak","bay","poi","yep","bun","try","lad","elm","nat","wyo","gym","dug","toe","dee","wig","sly","rip","geo","cog","pas","zen","odd","nan","lay","pod","fit","hem","joy","bum","rio","yon","dec","leg","put","sue","dim","pet","yaw","nub","bit","bur","sid","sun","oil","red","doc","moe","caw","eel","dix","cub","end","gem","off","yew","hug","pop","tub","sgt","lid","pun","ton","sol","din","yup","jab","pea","bug","gag","mil","jig","hub","low","did","tin","get","gte","sox","lei","mig","fig","lon","use","ban","flo","nov","jut","bag","mir","sty","lap","two","ins","con","ant","net","tux","ode","stu","mug","cad","nap","gun","fop","tot","sow","sal","sic","ted","wot","del","imp","cob","way","ann","tan","mci","job","wet","ism","err","him","all","pad","hah","hie","aim");
        final List<List<String>> paths = findLadders("cet", "ism", list);
        Assertions.assertEquals(2, paths.size());
        paths.forEach(p -> Assertions.assertEquals(11, p.size()));
    }

    @Test
    public void check6() {
        List<String> list = Lists.newArrayList("a","b","c");
        final Set<List<String>> expected = Sets.newHashSet();
        expected.add(Lists.newArrayList("a","c"));
        Assertions.assertEquals(expected, new HashSet<>(findLadders("a", "c", list)));
    }

    @Test
    public void check7() {
        List<String> list = Lists.newArrayList("hot","dot","dog");
        final Set<List<String>> expected = Sets.newHashSet();
        expected.add(Lists.newArrayList("hot","dot"));
        Assertions.assertEquals(expected, new HashSet<>(findLadders("hot", "dot", list)));
    }

    @Test
    public void check8() {
        List<String> list = Lists.newArrayList("ion","rev","che","ind","lie","wis","oct","ham","jag","ray","nun","ref","wig","jul","ken","mit","eel","paw","per","ola","pat","old","maj","ell","irk","ivy","beg","fan","rap","sun","yak","sat","fit","tom","fin","bug","can","hes","col","pep","tug","ump","arc","fee","lee","ohs","eli","nay","raw","lot","mat","egg","cat","pol","fat","joe","pis","dot","jaw","hat","roe","ada","mac");
        final Set<List<String>> expected = Sets.newHashSet();
        expected.add(Lists.newArrayList("cat","fat","fit","fin"));
        expected.add(Lists.newArrayList("cat","fat","fan","fin"));
        expected.add(Lists.newArrayList("cat","can","fan","fin"));
        Assertions.assertEquals(expected, new HashSet<>(findLadders("cat", "fin", list)));
    }

    @Test
    public void check9() {
        List<String> list = Lists.newArrayList("aaaaa","caaaa","cbaaa","daaaa","dbaaa","eaaaa","ebaaa","faaaa","fbaaa","gaaaa","gbaaa","haaaa","hbaaa","iaaaa","ibaaa","jaaaa","jbaaa","kaaaa","kbaaa","laaaa","lbaaa","maaaa","mbaaa","naaaa","nbaaa","oaaaa","obaaa","paaaa","pbaaa","bbaaa","bbcaa","bbcba","bbdaa","bbdba","bbeaa","bbeba","bbfaa","bbfba","bbgaa","bbgba","bbhaa","bbhba","bbiaa","bbiba","bbjaa","bbjba","bbkaa","bbkba","bblaa","bblba","bbmaa","bbmba","bbnaa","bbnba","bboaa","bboba","bbpaa","bbpba","bbbba","abbba","acbba","dbbba","dcbba","ebbba","ecbba","fbbba","fcbba","gbbba","gcbba","hbbba","hcbba","ibbba","icbba","jbbba","jcbba","kbbba","kcbba","lbbba","lcbba","mbbba","mcbba","nbbba","ncbba","obbba","ocbba","pbbba","pcbba","ccbba","ccaba","ccaca","ccdba","ccdca","cceba","cceca","ccfba","ccfca","ccgba","ccgca","cchba","cchca","cciba","ccica","ccjba","ccjca","cckba","cckca","cclba","cclca","ccmba","ccmca","ccnba","ccnca","ccoba","ccoca","ccpba","ccpca","cccca","accca","adcca","bccca","bdcca","eccca","edcca","fccca","fdcca","gccca","gdcca","hccca","hdcca","iccca","idcca","jccca","jdcca","kccca","kdcca","lccca","ldcca","mccca","mdcca","nccca","ndcca","occca","odcca","pccca","pdcca","ddcca","ddaca","ddada","ddbca","ddbda","ddeca","ddeda","ddfca","ddfda","ddgca","ddgda","ddhca","ddhda","ddica","ddida","ddjca","ddjda","ddkca","ddkda","ddlca","ddlda","ddmca","ddmda","ddnca","ddnda","ddoca","ddoda","ddpca","ddpda","dddda","addda","aedda","bddda","bedda","cddda","cedda","fddda","fedda","gddda","gedda","hddda","hedda","iddda","iedda","jddda","jedda","kddda","kedda","lddda","ledda","mddda","medda","nddda","nedda","oddda","oedda","pddda","pedda","eedda","eeada","eeaea","eebda","eebea","eecda","eecea","eefda","eefea","eegda","eegea","eehda","eehea","eeida","eeiea","eejda","eejea","eekda","eekea","eelda","eelea","eemda","eemea","eenda","eenea","eeoda","eeoea","eepda","eepea","eeeea","ggggg","agggg","ahggg","bgggg","bhggg","cgggg","chggg","dgggg","dhggg","egggg","ehggg","fgggg","fhggg","igggg","ihggg","jgggg","jhggg","kgggg","khggg","lgggg","lhggg","mgggg","mhggg","ngggg","nhggg","ogggg","ohggg","pgggg","phggg","hhggg","hhagg","hhahg","hhbgg","hhbhg","hhcgg","hhchg","hhdgg","hhdhg","hhegg","hhehg","hhfgg","hhfhg","hhigg","hhihg","hhjgg","hhjhg","hhkgg","hhkhg","hhlgg","hhlhg","hhmgg","hhmhg","hhngg","hhnhg","hhogg","hhohg","hhpgg","hhphg","hhhhg","ahhhg","aihhg","bhhhg","bihhg","chhhg","cihhg","dhhhg","dihhg","ehhhg","eihhg","fhhhg","fihhg","ghhhg","gihhg","jhhhg","jihhg","khhhg","kihhg","lhhhg","lihhg","mhhhg","mihhg","nhhhg","nihhg","ohhhg","oihhg","phhhg","pihhg","iihhg","iiahg","iiaig","iibhg","iibig","iichg","iicig","iidhg","iidig","iiehg","iieig","iifhg","iifig","iighg","iigig","iijhg","iijig","iikhg","iikig","iilhg","iilig","iimhg","iimig","iinhg","iinig","iiohg","iioig","iiphg","iipig","iiiig","aiiig","ajiig","biiig","bjiig","ciiig","cjiig","diiig","djiig","eiiig","ejiig","fiiig","fjiig","giiig","gjiig","hiiig","hjiig","kiiig","kjiig","liiig","ljiig","miiig","mjiig","niiig","njiig","oiiig","ojiig","piiig","pjiig","jjiig","jjaig","jjajg","jjbig","jjbjg","jjcig","jjcjg","jjdig","jjdjg","jjeig","jjejg","jjfig","jjfjg","jjgig","jjgjg","jjhig","jjhjg","jjkig","jjkjg","jjlig","jjljg","jjmig","jjmjg","jjnig","jjnjg","jjoig","jjojg","jjpig","jjpjg","jjjjg","ajjjg","akjjg","bjjjg","bkjjg","cjjjg","ckjjg","djjjg","dkjjg","ejjjg","ekjjg","fjjjg","fkjjg","gjjjg","gkjjg","hjjjg","hkjjg","ijjjg","ikjjg","ljjjg","lkjjg","mjjjg","mkjjg","njjjg","nkjjg","ojjjg","okjjg","pjjjg","pkjjg","kkjjg","kkajg","kkakg","kkbjg","kkbkg","kkcjg","kkckg","kkdjg","kkdkg","kkejg","kkekg","kkfjg","kkfkg","kkgjg","kkgkg","kkhjg","kkhkg","kkijg","kkikg","kkljg","kklkg","kkmjg","kkmkg","kknjg","kknkg","kkojg","kkokg","kkpjg","kkpkg","kkkkg","ggggx","gggxx","ggxxx","gxxxx","xxxxx","xxxxy","xxxyy","xxyyy","xyyyy","yyyyy","yyyyw","yyyww","yywww","ywwww","wwwww","wwvww","wvvww","vvvww","vvvwz","avvwz","aavwz","aaawz","aaaaz");
        final List<List<String>> paths = findLadders("aaaaa", "ggggg", list);
        Assertions.assertEquals(2, paths.size());
        paths.forEach(p -> Assertions.assertEquals(11, p.size()));
    }

}
