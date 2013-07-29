import java.util.ArrayList;
import java.util.List;

import me.donnior.fava.FList;
import me.donnior.fava.Function;
import me.donnior.fava.Predicate;
import me.donnior.fava.util.FLists;


public class Benchmark {

    /**
     * @param args
     */
    public static void main(String[] args) {
        final int count = 2000000;
        List<Integer> list = new ArrayList<Integer>(count);
        for(int i=0; i<count; i++){
            list.add(i);
        }
        long start = System.currentTimeMillis();
        List<String> result = usingFava(list);
        System.out.println("using fava : " + (System.currentTimeMillis() - start) );
        
        long start2 = System.currentTimeMillis();
        List<String> result2 = usingNormal(list);
        System.out.println("using normal : " + (System.currentTimeMillis() - start2) );
        
//        System.out.println(result.size() == result2.size());
//        System.out.println(result.get(0).equals(result2.get(0)));
    }

    private static List<String> usingNormal(List<Integer> list) {
        List<String> result  = new ArrayList<String>();
        for(Integer i : list){
            if(i % 2 == 1 && i % 5 == 0){
//                if(i % 5 == 0) {
                    result.add("integer "+ i);
//                }
            }
        }
        return result;
    }

    private static List<String> usingFava(List<Integer> list) {
        FList<Integer> fList = FLists.create(list);
        return fList.findAll(new Predicate<Integer>() {
            
            public boolean apply(Integer e) {
                return e % 2 == 1 && e % 5 == 0;
            }
//        }).select(new Predicate<Integer>() {
//            
//            public boolean apply(Integer e) {
//                return e % 5 == 0;
//            }
        }).map(new Function<Integer, String>() {

            public String apply(Integer e) {
                return "integer "+ e;
            }
        });
    }

}
