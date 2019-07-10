package sample;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class GetUnderKGUrl {

    public static void main(String[] args) {
//        getITnewsurldata();
//        try {
//            java.awt.Desktop.getDesktop().browse(new URI("http://www.naver.com"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
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

    public static List<String> getNaverurldata(){
        List<String> hreflist = null;
        try{
            Document doc = Jsoup.connect("https://blog.naver.com/PostList.nhn?blogId=tech-plus&categoryNo=1&parentCategoryNo=1&skinType=&skinId=&from=menu&userSelectMenu=true").get();

            Elements result = doc.select("[class=item]").select("a");
            String str = result.attr("href");
            hreflist = new ArrayList<>();
//            System.out.println(result.outerHtml());
            int j=0;
            for(Element ele :  result){
                hreflist.add(ele.attr("href"));
                j++;
            }

            //result.outerHtml().replace("amp","");
            //System.out.println("https://blog.naver.com"+str);
//            String tempStr = null;
//
//            int i = 0;
//            int j = 0;
//            int tempIndex;
//            hreflist = new ArrayList<>();
//
//            while(true){
//                tempIndex = str.indexOf("href",i);
//                hreflist.add(str.substring(tempIndex + 6, tempIndex + 110));
//                i = str.indexOf("href", i) + 1;
//
//
//                j++;
//                if (str.indexOf("href", i) < 0) {
//                    break;
//                }
//            }
//            System.out.println(hreflist);

        }catch (Exception e){
            e.printStackTrace();
        }
        return hreflist;
    }

    public static List<String> getITnewsurldata(){
        List<String> hreflist = null;
        try{
            Document doc = Jsoup.connect("http://www.itnews.or.kr/?cat=1162").get();

            Elements result = doc.select("[class=td-module-meta-info]").select("[class=entry-title td-module-title]").select("a");
            String str = result.attr("href");
            hreflist = new ArrayList<>();
//            System.out.println(result.outerHtml());
            int j=0;
            for(Element ele :  result){
                hreflist.add(ele.attr("href"));
                j++;
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return hreflist;
    }
}
