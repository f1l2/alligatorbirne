package common.transformer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Used to transform objects mainly between DTO (data transfer objects) and DLO (data local objects).
 * 
 * 
 * @author Manuel Filz
 *
 */
public abstract class Transformer<LOCAL, REMOTE> {

    final static Logger logger = LoggerFactory.getLogger(Transformer.class);

    protected static final String nullString = "null";

    public abstract LOCAL toLocal(REMOTE remote);

    public abstract REMOTE toRemote(LOCAL local);

    public Collection<LOCAL> toLocal(List<REMOTE> remotes) {

        List<LOCAL> locals = new ArrayList<LOCAL>();

        for (REMOTE remote : remotes) {
            locals.add(toLocal(remote));
        }
        return locals;

    }

    public Collection<LOCAL> toLocal(Set<REMOTE> remotes) {

        List<LOCAL> locals = new ArrayList<LOCAL>();

        for (REMOTE remote : remotes) {
            locals.add(toLocal(remote));
        }
        return locals;

    }

    public List<REMOTE> toRemote(List<LOCAL> locals) {

        List<REMOTE> remotes = new ArrayList<REMOTE>();

        for (LOCAL local : locals) {
            remotes.add(toRemote(local));
        }
        return remotes;

    }

    public List<REMOTE> toRemote(Set<LOCAL> locals) {

        List<REMOTE> remotes = new ArrayList<REMOTE>();

        for (LOCAL local : locals) {
            remotes.add(toRemote(local));
        }
        return remotes;

    }

    public static <E> List<E> makeCollection(Iterable<E> iter) {
        List<E> list = new ArrayList<E>();
        for (E item : iter) {
            list.add(item);
        }
        return list;
    }

}
