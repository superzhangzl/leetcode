package zzl.base.annotation;

import zzl.base.enums.Difficulty;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
public @interface Level {
    Difficulty value();

}
