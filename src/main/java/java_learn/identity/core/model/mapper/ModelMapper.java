package java_learn.identity.core.model.mapper;

import java.util.List;

public interface ModelMapper<M, D> {
  D toDto(M model);

  M toModel(D dto);

  List<D> toDto(List<M> modelList);

  List<M> toModel(List<D> dtoList);
}
