package common.lang.rule;

import java.util.ArrayList;
import java.util.List;

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

    protected String nativeRule;

    protected Boolean isActivated = false;

    protected List<String> queryNames;

    protected List<QueryLang> queries;

    protected List<Reaction> reactions;

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
        if (null == this.queries) {
            this.queries = new ArrayList<QueryLang>();
        }
        this.queries.add(query);
    }

    public List<String> getQueryNames() {
        return queryNames;
    }

    public void setQueryNames(List<String> queryNames) {
        this.queryNames = queryNames;
    }

    public void addQueryName(String queryName) {
        if (null == this.queryNames) {
            this.queryNames = new ArrayList<String>();
        }
        this.queryNames.add(queryName);
    }

    public List<Reaction> getReactions() {

        if (null == reactions) {
            reactions = new ArrayList<Reaction>();
        }

        return reactions;
    }

    public void setReactions(List<Reaction> reactions) {
        this.reactions = reactions;
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
