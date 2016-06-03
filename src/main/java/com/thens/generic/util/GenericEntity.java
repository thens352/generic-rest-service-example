package com.thens.generic.util;

import java.io.Serializable;

/**
 * Created by Enes KURU on 12/5/2015.
 */
public interface GenericEntity<DTO> extends Serializable{
    DTO toDTO();
}
