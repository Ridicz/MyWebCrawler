import graph.WebsiteGraph;

import java.util.List;
import java.util.Stack;

public class SpiderWebCrawler {
  private Stack<String> pagesToVisitStack;
  private WebsiteGraph websiteGraph;
  private String mainPage;

  public SpiderWebCrawler(String mainPage) {
    pagesToVisitStack = new Stack<>();
    websiteGraph = new WebsiteGraph(mainPage);
    this.mainPage = mainPage;
  }

  public void changeMainPage(String mainPage) {
    websiteGraph.changeMainPage(mainPage);
  }

  public String getWebsiteHierarchy() {
    exploreWebsiteFromCurrentMainPage();

    return websiteGraph.getWebsiteHierarchyOfPages();
  }

  private void exploreWebsiteFromCurrentMainPage() {
    pagesToVisitStack.add(mainPage);

    SpiderProcessor spiderProcessor;

    while (!pagesToVisitStack.isEmpty()) {
      spiderProcessor = new SpiderProcessor(mainPage);

      String actuallyProcessedPage = pagesToVisitStack.pop();

      spiderProcessor.crawl(actuallyProcessedPage);

      List<String> acquiredLinksFromURL = spiderProcessor.getAcquiredLinksList();

      acquiredLinksFromURL.stream().filter(subsiteURL -> !websiteGraph.isWebsiteGraphContainsSubsite(subsiteURL)).forEach(this::addSubsiteToCompute);
    }
  }

  private void addSubsiteToCompute(String subsiteURL) {
    pagesToVisitStack.add(subsiteURL);
    websiteGraph.addPageToWebsiteGraph(subsiteURL);
  }

  public int getSizeOfCreatedGraph() {
    return websiteGraph.getNumberOfAvailableSubsites();
  }
}
