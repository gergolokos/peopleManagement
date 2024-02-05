package lg.pplmanagement.api.converter;

/**
 * @param <T> Api representation's type
 * @param <U> Domain (model) representation's type
 */
public abstract class ApiConverter<T, U> {

    public abstract U convertToModel(final T api);

    public abstract T convertToApi(final U model);
}
