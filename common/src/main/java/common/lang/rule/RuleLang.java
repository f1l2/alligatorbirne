package common.lang.rule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.util.StringUtils;

import common.lang.query.QueryLang;
import common.lang.rule.model.Reaction;
import common.lang.rule.model.Window;

public class RuleLang {

    /**
     * Enum Keywords
     */
    public static enum KEYWORD {
        TRIGGERS("triggers"),

        TIME("win:time");

        private String keyword;

        KEYWORD(String keyword) {
            this.setKeyword(keyword);
        }

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public static KEYWORD findByKeyword(String keyword) {
            for (KEYWORD item : KEYWORD.values()) {
                if (item.getKeyword().equals(keyword)) {
                    return item;
                }
            }
            return null;
        }
    }

    protected String name;

    protected Set<String> negations = new HashSet<String>();

    protected String nativeRule;

    protected Boolean isActivated = false;

    protected List<String> queryNames = new ArrayList<String>();

    protected List<QueryLang> queries = new ArrayList<QueryLang>();

    protected List<Reaction> reactions = new ArrayList<Reaction>();

    protected Window window;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNativeRule() {
        return nativeRule;
    }

    public void setNativeRule(String nativeRule) {
        this.nativeRule = nativeRule;
    }

    public List<QueryLang> getQueries() {
        return queries;
    }

    public void setQueries(List<QueryLang> queries) {
        this.queries = queries;
    }

    public void addQuery(QueryLang query) {
        this.queries.add(query);
    }

    public List<String> getQueryNames() {
        return queryNames;
    }

    public void addQueryName(String queryName) {
        this.queryNames.add(queryName);
    }

    public List<Reaction> getReactions() {
        return reactions;
    }

    public void setReactions(List<Reaction> reactions) {
        this.reactions = reactions;
    }

    public void addNegation(String query) {
        if (!StringUtils.isEmpty(query)) {
            this.negations.add(query);
        }
    }

    public Set<String> getNegations() {
        return this.negations;
    }

    public Window getWindow() {
        return window;
    }

    public void setWindow(Window window) {
        this.window = window;
    }

    public Boolean getIsActivated() {
        return isActivated;
    }

    public void setIsActivated(Boolean isActivated) {
        this.isActivated = isActivated;
    }
}
