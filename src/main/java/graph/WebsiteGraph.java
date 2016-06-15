package graph;

import java.util.*;

public class WebsiteGraph {
  private Set<SiteNode> siteNodeSet;

  private SiteNode mainPage;

  public WebsiteGraph(String mainPageURL) {
    siteNodeSet = new HashSet<>();
    mainPage = new SiteNode(mainPageURL);
    siteNodeSet.add(mainPage);
  }

  public void changeMainPage(String mainPage) {
    this.mainPage = new SiteNode(mainPage);
    siteNodeSet = new HashSet<>();
    siteNodeSet.add(this.mainPage);
  }

  public boolean addPageToWebsiteGraph(String addedPageURL) {
    SiteNode addedSiteNode = new SiteNode(addedPageURL);

    if (siteNodeSet.contains(addedSiteNode)) {
      return false;
    }

    siteNodeSet.add(addedSiteNode);

    return true;
  }

  public String getWebsiteHierarchyOfPages() {
    StringBuilder outputWebsiteHierarchyStringBuilder = new StringBuilder();

    outputWebsiteHierarchyStringBuilder.append("For mainPage: ")
      .append(mainPage)
      .append(" list of available subsites:\n");

    for (SiteNode siteNode : siteNodeSet) {
      outputWebsiteHierarchyStringBuilder.append(siteNode).append("\n");
    }

    return outputWebsiteHierarchyStringBuilder.toString();
  }

  public void setSubsitesAccessibleFromGiven(String givenSubsite, List<String> subsitesAccessibleFromGiven) {
    for (SiteNode siteNode : siteNodeSet) {
      if (Objects.equals(siteNode.getSiteURL(), givenSubsite)) {
        siteNode.addSitesAccessibleFromCurrent(subsitesAccessibleFromGiven);
        return;
      }
    }
  }

  public boolean isWebsiteGraphContainsSubsite(String checkedSubsiteURL) {
    SiteNode siteNode = new SiteNode(checkedSubsiteURL);

    return siteNodeSet.contains(siteNode);
  }

  public int getNumberOfAvailableSubsites() {
    return siteNodeSet.size();
  }

  public String getSitesAccessibleFromMainPage() {
    StringBuilder stringBuilder = new StringBuilder();

    for (String subsiteURL : mainPage.getSiteAccessibleFromSiteList()) {
      stringBuilder.append(subsiteURL).append("\n");
    }

    return stringBuilder.toString();
  }
}
