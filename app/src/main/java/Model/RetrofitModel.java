package Model;

import java.util.ArrayList;
import java.util.List;

public class RetrofitModel {
    public String next_page_token;

    public ArrayList<Results> results;

    public String[] html_attributions;

    public String status;

    public ArrayList<Results> getResults() {
        return results;
    }

    public void setResults(ArrayList<Results> results) {
        this.results = results;
    }
}