package event.processing.rule;

import java.util.ArrayList;
import java.util.List;

import event.processing.rule.model.Reaction;

public class Rule {

    /**
     * Enum Keywords
     */
    public static enum KEYWORD {
        TRIGGERS("TRIGGERS");

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
    }

    private Boolean isActivated = false;

    private String query;

    private List<Reaction> reactions;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
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

    public Boolean getIsActivated() {
        return isActivated;
    }

    public void setIsActivated(Boolean isActivated) {
        this.isActivated = isActivated;
    }

}
