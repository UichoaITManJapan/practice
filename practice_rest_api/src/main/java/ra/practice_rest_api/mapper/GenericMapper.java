package ra.practice_rest_api.mapper;

public interface GenericMapper<E,R,S> {
    //chuyển từ Request sang entity
    E mapperToEntity(R r);
    // chuyển từ Entity sang Response
    S mapperToResponse(E e);
}
