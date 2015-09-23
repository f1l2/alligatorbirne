package common.transformer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Manuel Filz
 *
 */
public abstract class Transformer<LOCAL, REMOTE> {

    final static Logger logger = LoggerFactory.getLogger(Transformer.class);

    public abstract LOCAL toLocal(REMOTE remote);

    public abstract REMOTE toRemote(LOCAL local);

    public Collection<LOCAL> toLocal(List<REMOTE> remotes) {

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

    public static <E> List<E> makeCollection(Iterable<E> iter) {
        List<E> list = new ArrayList<E>();
        for (E item : iter) {
            list.add(item);
        }
        return list;
    }

}
