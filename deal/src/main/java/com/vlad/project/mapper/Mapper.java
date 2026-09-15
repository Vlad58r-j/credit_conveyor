package com.vlad.project.mapper;

public interface Mapper<F, T>{

    T map(F dto);

}
