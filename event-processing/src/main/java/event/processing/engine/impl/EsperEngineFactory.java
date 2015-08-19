package event.processing.engine.impl;

import event.processing.engine.Engine;
import event.processing.engine.EngineFactory;
import event.processing.engine.EngineListener;
import event.processing.engine.QueryTransformer;

public class EsperEngineFactory extends EngineFactory {

    protected static EsperEngineFactory INSTANCE = null;

    protected static EsperEngine ENGINE_INSTANCE = null;

    protected static EsperEngineListener ENGINE_LISTENER_INSTANCE = null;

    protected static EsperQueryTransformer QUERY_TRANSFORMER_INSTANCE = null;

    public static EsperEngineFactory getEsperEngineFactory() {
        if (null == INSTANCE) {
            synchronized (MUTEX) {
                if (null == INSTANCE) {
                    INSTANCE = new EsperEngineFactory();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    protected Engine createEngine() {
        return new EsperEngine();
    }

    @Override
    public Engine getEngine() {
        if (null == ENGINE_INSTANCE) {
            synchronized (MUTEX) {
                if (null == ENGINE_INSTANCE) {
                    ENGINE_INSTANCE = (EsperEngine) createEngine();
                }
            }
        }
        return ENGINE_INSTANCE;
    }

    @Override
    protected EngineListener createEgineListener() {
        return new EsperEngineListener();
    }

    @Override
    public EngineListener getEngineListener() {
        if (null == ENGINE_LISTENER_INSTANCE) {
            synchronized (MUTEX) {
                if (null == ENGINE_LISTENER_INSTANCE) {
                    ENGINE_LISTENER_INSTANCE = (EsperEngineListener) createEgineListener();
                }
            }
        }
        return ENGINE_LISTENER_INSTANCE;
    }

    @Override
    protected QueryTransformer createQueryTransformer() {
        return new EsperQueryTransformer();
    }

    @Override
    public QueryTransformer getQueryTransformer() {
        if (null == QUERY_TRANSFORMER_INSTANCE) {
            synchronized (MUTEX) {
                if (null == QUERY_TRANSFORMER_INSTANCE) {
                    QUERY_TRANSFORMER_INSTANCE = (EsperQueryTransformer) createQueryTransformer();
                }
            }
        }
        return QUERY_TRANSFORMER_INSTANCE;
    }

}
