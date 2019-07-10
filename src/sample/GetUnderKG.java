package sample;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;


public class GetUnderKG {
    public static void main(String[] args) {

        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<String> list3 = new ArrayList<>();


//        list1 = getUnderKGdata(list1);
        list2 = getNaverdata(list2);
        list3 = getITnewsdata(list3);
//        list1 = getUnderKGurldata(list1);
        System.out.println(list1);
        System.out.println(list2);
        System.out.println(list3);

    }


    public static List<String> getITnewsdata(List<String> list) {
        try{
            Document doc = Jsoup.connect("http://www.itnews.or.kr/?cat=1162").get();

            Elements result = doc.select("[class=td-module-meta-info]").select("[class=entry-title td-module-title]").select("a");
            list = result.eachText();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public static List<String> getNaverdata(List<String> list) {
        try{
            Document doc = Jsoup.connect("https://blog.naver.com/PostList.nhn?blogId=tech-plus&categoryNo=1&parentCategoryNo=1&skinType=&skinId=&from=menu&userSelectMenu=true").get();

            Elements result = doc.select("[class=title ell]");
            list = result.eachText();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public static List<String> getUnderKGdata(List<String> list){
        try{
            Document doc = Jsoup.connect("http://underkg.co.kr/news").get();

            Elements result = doc.select("[class=title]").select("a");
            list = result.eachText();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public static List<String> getUnderKGurldata() {
        List<String> hreflist = null;
        try {
            Document doc = Jsoup.connect("http://underkg.co.kr/news").get();

            Elements result = doc.select("[class=title]").select("a");
//            System.out.println(result.outerHtml());
            String str = result.outerHtml();

            int i = 0;
            int j = 0;
            int tempIndex;
            hreflist = new ArrayList<>();
            while (true) {

                tempIndex = str.indexOf("href", i);
//                System.out.println(str.indexOf("href", i));
                hreflist.add(str.substring(tempIndex + 6, tempIndex + 39));
                i = str.indexOf("href", i) + 1;

                j++;
                if (str.indexOf("href", i) < 0) {
                    break;
                }

            }
//            System.out.println(hreflist);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return hreflist;
    }

}
