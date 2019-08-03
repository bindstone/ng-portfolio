package com.bindstone.portfolio.mapper;

/**
 * JPA/DTO Mapper
 *
 * @param <JPA> JPA Object
 * @param <DTO> DTO Object
 */
public interface DtoMapper<JPA, DTO> {

    /**
     * JPA to DTO Mapper
     *
     * @param object JPA Object
     * @return DTO Object
     */
    DTO toDto(JPA object);

    /**
     * JPA to DTO Mapper
     *
     * @param objectDto DTO Object
     * @return JPA Object
     */
    JPA fromDto(DTO objectDto);

}
