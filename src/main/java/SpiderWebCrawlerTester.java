public class SpiderWebCrawlerTester {
  private static void testSpiderWebCrawler(String mainPageURL) {
    String mainPageURLAlt = "http://www.netinstructions.com";

    SpiderWebCrawler spiderWebCrawler = new SpiderWebCrawler(mainPageURL);

    System.out.println("Acquired links from website: " + mainPageURL);
    System.out.println(spiderWebCrawler.getWebsiteHierarchy());
    System.out.println();

    System.out.println("Size of created Website Graph: " + spiderWebCrawler.getSizeOfCreatedGraph());
    System.out.println();

    System.out.println("Links from main page: ");
    System.out.println(spiderWebCrawler.getLinksFromMainPage());
  }

  public static void main(String[] args) {
    testSpiderWebCrawler(args[0]);
  }
}
