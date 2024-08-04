package com.sourav.companysearch.companysearch.model;

import java.util.List;
import java.util.Map;

public class OfficerResponse {
    private String etag;
    private Map<String, String> links;
    private String kind;
    private int itemsPerPage;
    private List<Officer> items;

  // Getters and setters (or use Lombok @Data)
  public String getEtag() {
    return etag;
}

public void setEtag(String etag) {
    this.etag = etag;
}

public Map<String, String> getLinks() {
    return links;
}

public void setLinks(Map<String, String> links) {
    this.links = links;
}

public String getKind() {
    return kind;
}

public void setKind(String kind) {
    this.kind = kind;

}

public int getItemsPerPage() {
    return itemsPerPage;
}

public void setItemsPerPage(int itemsPerPage) {
    this.itemsPerPage = itemsPerPage;
}

public List<Officer> getItems() {
    return items;
}

public void setItems(List<Officer> items) {
    this.items = items;
}
}