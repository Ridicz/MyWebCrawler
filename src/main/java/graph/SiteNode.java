package graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

class SiteNode implements Comparable<SiteNode> {
  private List<String> siteAccessibleFromSiteList;
  private String siteURL;
  private int lengthFromMainPage;

  SiteNode(String siteURL) {
    siteAccessibleFromSiteList = new LinkedList<>();
    this.siteURL = siteURL;
  }

//  public void addSiteAccessibleFromCurrent(SiteNode addedSiteNode) {
//    siteAccessibleFromSiteList.add(addedSiteNode);
//  }

  public void addSitesAccessibleFromCurrent(List<String> addedSubsites) {
    //siteAccessibleFromSiteList.add(addedSiteNode);

    siteAccessibleFromSiteList = addedSubsites;
  }

//  public List<SiteNode> getSiteAccessibleFromSiteList() {
//    return siteAccessibleFromSiteList;
//  }

  public void setLengthFromMainPage(int lengthFromMainPage) {
    this.lengthFromMainPage = lengthFromMainPage;
  }

  public int getLengthFromMainPage() {
    return lengthFromMainPage;
  }

  public String getSiteURL() {
    return siteURL;
  }

  @Override
  public String toString() {
    return "Site URL: " + siteURL;
  }

  @Override
  public int compareTo(SiteNode otherSiteNode) {
    if (this.siteURL.compareTo(otherSiteNode.siteURL) == 0) {
      return 0;
    } else if (this.siteURL.compareTo(otherSiteNode.siteURL) > 1) {
      return 1;
    } else {
      return -1;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this.siteURL);
  }

  @Override
  public boolean equals(Object otherObject) {
    if (otherObject == null) {
      return false;
    }

    if (otherObject.getClass() != this.getClass()) {
      return false;
    }

    SiteNode otherSiteNode = (SiteNode) otherObject;

    return otherSiteNode.siteURL.equals(this.siteURL);
  }
}
