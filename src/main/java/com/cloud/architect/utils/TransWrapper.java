package com.cloud.architect.utils;

import com.cloud.architect.utils.dto.ListResultDTO;
import com.cloud.architect.utils.dto.PageResultDTO;
import com.cloud.architect.utils.dto.ResultDTO;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

/*
  SuppressWarnings注解主要用在取消一些编译器产生的警告对代码左侧行列的遮挡，有时候这会挡住我们断点调试时打的断点。
  unused 抑制没被使用过的代码的警告
 */
@SuppressWarnings("unused")
public abstract class TransWrapper extends ModelMapper {

    public TransWrapper() {
        this.getConfiguration().setPropertyCondition(Conditions.isNotNull());
    }

    /////////////////////////////////////////////////////////////////////////////////////
    // dto  -->  model
    /////////////////////////////////////////////////////////////////////////////////////
    public final <D, M> M toModel(D dto, Class<M> destinationType) {
        return this.map(dto, destinationType);
    }

    public final <D, M> List<M> toListModel(@NotNull final List<D> dtoList, Class<M> destinationType) {
        return dtoList.stream().map(dto -> this.toModel(dto, destinationType)).collect(Collectors.toList());
    }

    /////////////////////////////////////////////////////////////////////////////////////
    // model/modelPage/modelList  -->  dto/dtoPage/dtoList
    /////////////////////////////////////////////////////////////////////////////////////
    public final <M, D> D toDTO(M model, Class<D> destinationType) {
        return this.map(model, destinationType);
    }

    public final <M, D> List<D> toListDTO(@NotNull final List<M> list, Class<D> destinationType) {
        return list.stream().map(model -> this.toDTO(model, destinationType)).collect(Collectors.toList());
    }

    public final <M, D> ResultDTO<D> toResultDTO(M model, Class<D> destinationType) {
        return ResultDTO.success((model == null) ? null : this.toDTO(model, destinationType));
    }

    public final <M, D> ListResultDTO<D> toListResultDTO(@NotNull final List<M> list, Class<D> destinationType) {
        return ListResultDTO.success(this.toListDTO(list, destinationType));
    }

    public final <M, D> Page<D> toPageDTO(final Page<M> modelPage, final Pageable pageable, Class<D> destinationType) {
        return new PageImpl<>(this.toListDTO(modelPage.getContent(), destinationType), pageable, modelPage.getTotalElements());
    }

    public final <M, D> PageResultDTO<D> toPageResultDTO(final Page<M> modelPage, final Pageable pageable, Class<D> destinationType) {
        return PageResultDTO.success(this.toPageDTO(modelPage, pageable, destinationType));
    }

}
