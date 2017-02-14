package rb.ks.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import rb.ks.exceptions.PlanBuilderException;
import rb.ks.model.antlr4.Query;

import java.util.*;

public class PlanModel {
    Map<String, Query> queries = new LinkedHashMap<>();

    @JsonCreator
    public PlanModel(@JsonProperty("queries") Map<String, String> queries) {
        queries.forEach((name, queryString) -> {
            Query query = null; //TODO: Implement query parser!
            this.queries.put(name, query);
        });
    }

    @JsonProperty
    public Map<String, Query> getQueries() {
        return queries;
    }

    public void validate() throws PlanBuilderException {
    }

    public String printExecutionPlan() {
        return "";
    }
}
