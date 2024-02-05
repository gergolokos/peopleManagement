package lg.pplmanagement.repository.converter;

/**
 * @param <V> Repository (entity) representation's type
 * @param <U> Domain (model) representation's type
 */
public abstract class RepositoryConverter<V, U> {
    public abstract U convertToModel(final V entity);

    public abstract V convertToEntity(final U model);

}

