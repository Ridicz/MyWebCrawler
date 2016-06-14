import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class SpiderProcessor {
  private static final String USER_AGENT =
    "Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6";

  private static final int OK_STATUS_CODE = 200;

  private final Logger logger;

  private List<String> acquiredLinksList;

  private String mainPage;

  public SpiderProcessor(String mainPage) {
    acquiredLinksList = new LinkedList<>();
    logger = LoggerFactory.getLogger(SpiderProcessor.class);
    this.mainPage = mainPage;
  }

  public void crawl(String nextUrl) {
    try {
      Connection connection = Jsoup.connect(nextUrl).userAgent(USER_AGENT);

      Document htmlDocument = connection.get();

      if (connection.response().statusCode() == OK_STATUS_CODE) {
        logger.info("Processing subsite: " + nextUrl);
      }

      if (!connection.response().contentType().contains("text/html")) {
        logger.error("Received non HTML object to process!");
        return;
      }

      Elements linksOnSubsite = htmlDocument.select("a[href]");

      logger.info("Found " + linksOnSubsite.size() + " links to other sites.");

      for (Element element : linksOnSubsite) {
        String currentURL = element.absUrl("href");

        if (checkIfSubsiteBelongsToDomain(currentURL)) {
          acquiredLinksList.add(currentURL);
        }
      }

    } catch (IOException ioe) {
      logger.error("Could not process HTTP request for: " + nextUrl);
    }
  }

  private boolean checkIfSubsiteBelongsToDomain(String subsiteURL) {
    return subsiteURL.startsWith(mainPage);
  }

  public boolean searchForKeyWord(String keyWord) {
    return false;
  }

  public List<String> getAcquiredLinksList() {
    return acquiredLinksList;
  }
}
